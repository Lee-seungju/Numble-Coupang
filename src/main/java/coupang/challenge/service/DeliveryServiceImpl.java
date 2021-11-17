package coupang.challenge.service;

import coupang.challenge.data.Delivery;
import coupang.challenge.data.Member;
import coupang.challenge.form.DeliveryForm;
import coupang.challenge.repository.DeliveryRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Transactional
public class DeliveryServiceImpl implements DeliveryService {

    private DeliveryRepository deliveryRepository;

    public DeliveryServiceImpl(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    @Override
    public Delivery insertData(Delivery delivery, DeliveryForm deliveryForm, HttpSession session) {
        delivery.setMember_id(((Member)session.getAttribute("member")).getId());
        delivery.setName(deliveryForm.getName());
        delivery.setPhone_number(deliveryForm.getPhone_number());
        delivery.setAddress(deliveryForm.getAddress());
        delivery.setWho_take(deliveryForm.getWho_take());
        delivery.setMain(deliveryForm.isMain());
        checkMain(delivery, delivery.getMember_id(), delivery.getId());
        return delivery;
    }

    @Override
    public void addOrChange(Object d, DeliveryForm deliveryForm, HttpSession session) {
        Delivery delivery = insertData((Delivery)d, deliveryForm, session);
        if (delivery.getId() == null) {
            System.out.println("aaaaaa" + delivery.getId());
            System.out.println(delivery.getName());
            System.out.println(delivery.getMember_id());
            deliveryRepository.save(delivery);
        }
        else {
            System.out.println("bbbbbb");
            deliveryRepository.useMerge(delivery);
        }
    }

    @Override
    public List<Delivery> sortMainDelivery(HttpSession session) {
        Stream<Delivery> mainDelivery = (findList(((Member)session.getAttribute("member")).getId()))
                .stream().filter(d -> d.isMain()==true);
        Stream<Delivery> notMain = (findList(((Member)session.getAttribute("member")).getId()))
                .stream().filter(d -> d.isMain()==false);
        return Stream.concat(mainDelivery, notMain).collect(Collectors.toList());
    }

    @Override
    public Optional<Delivery> findOne(Long deliveryId) {
        return deliveryRepository.findOne(deliveryId);
    }

    private void checkMain(Delivery delivery, Long memberId, Long id) {
        List<Delivery> deliveries = deliveryRepository.findById(memberId);
        if (delivery.isMain() == true) {
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
    public List<Delivery> findList(Long memberId) {
        return deliveryRepository.findById(memberId);
    }

    @Override
    public Delivery makeNull(HttpSession session) {
        Delivery delivery = new Delivery();
        if (session.getAttribute("member") != null)
            delivery.setMember_id(((Member)session.getAttribute("member")).getId());
        return delivery;
    }
}
