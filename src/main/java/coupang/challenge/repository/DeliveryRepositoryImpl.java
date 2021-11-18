package coupang.challenge.repository;

import coupang.challenge.data.Delivery;
import coupang.challenge.data.Member;
import coupang.challenge.form.DeliveryForm;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DeliveryRepositoryImpl implements DeliveryRepository{

    private final EntityManager em;

    public DeliveryRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(Delivery delivery) {
        em.persist(delivery);
    }

    @Override
    public void useMerge(Delivery delivery) {
        em.merge(delivery);
    }

    @Override
    public void insertData(Object d, DeliveryForm deliveryForm, Long id) {
        Delivery delivery = setDelivery((Delivery)d, deliveryForm, id);
        checkMain(delivery, delivery.getMember_id(), delivery.getId());
        if (delivery.getId() == null)
            save(delivery);
        else
            useMerge(delivery);
    }

    @Override
    public Delivery makeNull(HttpSession session) {
        Delivery delivery = new Delivery();
        if (session.getAttribute("member") != null)
            delivery.setMember_id(((Member)session.getAttribute("member")).getId());
        return delivery;
    }

    private Delivery setDelivery(Delivery delivery, DeliveryForm deliveryForm, Long id) {
        delivery.setMember_id(id);
        delivery.setName(deliveryForm.getName());
        delivery.setPhone_number(deliveryForm.getPhone_number());
        delivery.setAddress(deliveryForm.getAddress());
        delivery.setWho_take(deliveryForm.getWho_take());
        delivery.setMain(deliveryForm.isMain());
        return delivery;
    }

    private void checkMain(Delivery delivery, Long memberId, Long id) {
        List<Delivery> deliveries = findById(memberId);
        if (delivery.isMain()) {
            for (int i=0; i<deliveries.size(); i++) {
                if (deliveries.get(i).getId() != id)
                    deliveries.get(i).setMain(false);
            }
        }
        else {
            delivery.setMain(true);
            for (int i=0; i<deliveries.size(); i++) {
                if (deliveries.get(i).getId() != id && deliveries.get(i).isMain() == true)
                    delivery.setMain(false);
            }
        }
    }


    @Override
    public List<Delivery> findById(Long memberId) {
        return em.createQuery("select d from Delivery d where d.member_id=:memberId", Delivery.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }

    @Override
    public Optional<Delivery> findOne(Long deliveryId) {
        return Optional.ofNullable(em.find(Delivery.class, deliveryId));
    }

    @Override
    public List<Delivery> findAll() {
        return em.createQuery("select m from Delivery m", Delivery.class)
                .getResultList();
    }
}
