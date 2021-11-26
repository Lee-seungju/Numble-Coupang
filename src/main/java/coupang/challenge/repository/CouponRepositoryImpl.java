package coupang.challenge.repository;

import coupang.challenge.data.Coupon;
import coupang.challenge.form.CouponForm;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CouponRepositoryImpl implements CouponRepository{

    private final EntityManager em;

    public CouponRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(Coupon coupon) {
        em.persist(coupon);
    }

    @Override
    public Coupon setCoupon(CouponForm couponForm) {
        Coupon coupon = new Coupon();
        coupon.setNumber(couponForm.getNumber());
        coupon.setName(couponForm.getName());
        coupon.setIf_buy(couponForm.getIf_buy());
        coupon.setUse_day(couponForm.getUse_day());
        coupon.setDiscount(couponForm.getDiscount());
        return coupon;
    }

    @Override
    public void useMerge(Coupon coupon) {
        em.merge(coupon);
    }

    @Override
    public Optional<Coupon> findByNumber(String number) {
        List<Coupon> result = em.createQuery("select m from Coupon m where m.number = :number", Coupon.class)
                .setParameter("number", number)
                .getResultList();
        return  result.stream().findAny();
    }

    @Override
    public List<Coupon> findByMemberId(Long memberId) {
        return em.createQuery("select m from Coupon m where m.member_id = :member_id", Coupon.class)
                .setParameter("member_id", memberId)
                .getResultList();
    }

    @Override
    public boolean alreadyHaveSame(Long memberId, String number) {
        List<Coupon> result = findByMemberId(memberId);
        String couponName = (findByNumber(number).get()).getName();
        for(int i=0; i<result.size(); i++) {
            if (result.get(i).getName() == couponName)
                return true;
        }
        return false;
    }

    @Override
    public boolean isUsed(Long Id) {
        List<Coupon> result = em.createQuery("select m from Coupon m where m.id = :id", Coupon.class)
                .setParameter("id", Id)
                .getResultList();
        if (((Coupon)result).isUse_coupon() == false)
            return false;
        return true;
    }

    @Override
    public boolean existCoupon(String number) {
        List<Coupon> result = em.createQuery("select m from Coupon m where m.number = :number", Coupon.class)
                .setParameter("number", number)
                .getResultList();
        if (result.isEmpty())
            return false;
        return true;
    }
}
