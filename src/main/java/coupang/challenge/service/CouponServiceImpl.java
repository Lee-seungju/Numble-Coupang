package coupang.challenge.service;

import coupang.challenge.data.Coupon;
import coupang.challenge.form.CouponForm;
import coupang.challenge.repository.CouponRepository;
import coupang.challenge.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Transactional
public class CouponServiceImpl implements CouponService{

    private final CouponRepository couponRepository;

    public CouponServiceImpl(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    @Override
    public void addCoupon(CouponForm couponForm) {
        couponRepository.save(couponRepository.setCoupon(couponForm));
    }

    @Override
    public boolean addCouponToMember(String couponNumber, Long memberId) {
        if (!(couponRepository.existCoupon(couponNumber)))
            return false;
        Optional<Coupon> coupon = couponRepository.findByNumber(couponNumber);
        if (coupon.get().getMember_id() != null)
            return false;
        coupon.get().setMember_id(memberId);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, coupon.get().getUse_day());
        coupon.get().setValidity(new java.text.SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(calendar.getTime()));
        couponRepository.useMerge(coupon.get());
        return true;
    }

    @Override
    public List<Coupon> searchCoupon(Long memberId) {
        List<Coupon> coupons = couponRepository.findByMemberId(memberId);
        checkValidity(coupons);
        return coupons;
    }

    private void checkValidity(List<Coupon> coupons) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        Date date = new Date();
        for (Coupon coupon : coupons) {
            try {
                if (coupon.getValidity() != null && date.compareTo(sdf.parse(coupon.getValidity())) > 0)
                    coupon.setUse_coupon(true);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
}
