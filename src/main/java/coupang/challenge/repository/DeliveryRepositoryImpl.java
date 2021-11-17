package coupang.challenge.repository;

import coupang.challenge.data.Delivery;
import coupang.challenge.data.Member;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

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
