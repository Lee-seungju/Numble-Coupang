package coupang.challenge.service;

import coupang.challenge.data.Coupon;
import coupang.challenge.form.CouponForm;
import coupang.challenge.repository.CouponRepository;
import coupang.challenge.repository.MemberRepository;

import javax.servlet.http.HttpSession;
import java.util.List;

public class CouponServiceImpl implements CouponService{

    private CouponRepository couponRepository;
    private MemberRepository memberRepository;

    public CouponServiceImpl(CouponRepository couponRepository, MemberRepository memberRepository) {
        this.couponRepository = couponRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public void addCoupon(CouponForm couponForm) {
        couponRepository.save(couponRepository.setCoupon(couponForm));
    }

    @Override
    public boolean addCouponToMember(String couponNumber, HttpSession session) {
        if (couponRepository.existCoupon(couponNumber) == false)
            return false;
        Coupon coupon = couponRepository.findByNumber(couponNumber).get();
        if (coupon.getMember_id() != null)
            return false;
        coupon.setMember_id(memberRepository.getMemberFromSession(session).getId());
        couponRepository.useMerge(coupon);
        return true;
    }

    @Override
    public List<Coupon> searchCoupon(HttpSession session) {
        return couponRepository.findByMemberId(memberRepository.getMemberFromSession(session).getId());
    }
}
