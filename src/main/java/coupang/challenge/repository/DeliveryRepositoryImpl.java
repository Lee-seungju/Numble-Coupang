package coupang.challenge.repository;

import coupang.challenge.data.Delivery;
import coupang.challenge.data.Member;

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
    public List<Delivery> findById(Long memberId) {
        return em.createQuery("select d from Delivery d where d.member_id=:memberId", Delivery.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }

    @Override
    public List<Delivery> findAll() {
        return em.createQuery("select m from Delivery m", Delivery.class)
                .getResultList();
    }
}
