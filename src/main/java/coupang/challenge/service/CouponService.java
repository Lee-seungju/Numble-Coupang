package coupang.challenge.service;

import coupang.challenge.data.Coupon;
import coupang.challenge.form.CouponForm;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface CouponService {
    void addCoupon(CouponForm couponForm);
    boolean addCouponToMember(String couponNumber, Long memberId);
    List<Coupon> searchCoupon(Long memberId);
}
