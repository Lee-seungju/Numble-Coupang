package coupang.challenge.repository;

import coupang.challenge.data.Coupon;
import coupang.challenge.form.CouponForm;

import java.util.List;
import java.util.Optional;

public interface CouponRepository {
    void save(Coupon coupon);
    Coupon setCoupon(CouponForm couponForm);
    void useMerge(Coupon coupon);
    Optional<Coupon> findByNumber(String number);
    List<Coupon> findByMemberId(Long memberId);
    boolean alreadyHaveSame(Long memberId, String number);
    boolean isUsed(Long Id);
    boolean existCoupon(String number);
}
