package coupang.challenge.controller;

import coupang.challenge.data.Coupon;
import coupang.challenge.data.Member;
import coupang.challenge.data.Message;
import coupang.challenge.form.CouponViewForm;
import coupang.challenge.form.DeliveryForm;
import coupang.challenge.service.CouponService;
import coupang.challenge.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {

    private final DeliveryService deliveryService;
    private final CouponService couponService;

    @Autowired
    public UserController(DeliveryService deliveryService, CouponService couponService) {
        this.deliveryService = deliveryService;
        this.couponService = couponService;
    }

    @GetMapping("user")
    public String createUser() {
        return "user/userForm";
    }

    @GetMapping("/user/showDList")
    public ModelAndView moveDList(HttpSession session) {
        ModelAndView mav = new ModelAndView("user/deliveryList");
        if (session.getAttribute("member") == null)
            return mav;
        mav.addObject("delivery", deliveryService.sortMainDelivery(((Member)session.getAttribute("member")).getId()));
        return mav;
    }

    @GetMapping("user/createOrEditAddress")
    public String moveDCreate(HttpSession session) {
        deliveryService.makeNull(session);
        return "user/deliveryForm";
    }

    @PostMapping("user/deliveryForm")
    public String AddDelivery(HttpSession session, DeliveryForm deliveryForm) {
        deliveryService.addOrChange(session.getAttribute("delivery"), deliveryForm,
                ((Member)session.getAttribute("member")).getId());
        session.removeAttribute("delivery");
        return "redirect:/user/showDList";
    }

    @GetMapping("/user/rocketWow")
    public String moveWow() { return "user/rocketMembership"; }

    @GetMapping("/user/editMyInfo")
    public String movePersonalInfo() { return "user/accessPersonalInfo"; }

    @GetMapping("user/canEdit")
    public String moveEditInfo() { return "user/editPersonalInfo"; }

    @PostMapping("/user/wantChange")
    public String changeDeliveryInfo(HttpServletRequest httpServletRequest, HttpSession session) {
        String deliveryId = httpServletRequest.getParameter("id");
        deliveryService.setSessionById(session, Long.parseLong(deliveryId));
        return "redirect:/user/createOrEditAddress";
    }

    @GetMapping("/user/coupon")
    public ModelAndView moveCouponPage(HttpSession session) {
        ModelAndView mav = new ModelAndView("user/couponForm");
        if (session.getAttribute("member") == null)
            return mav;
        List<Coupon> coupons = couponService.searchCoupon(((Member)session.getAttribute("member")).getId());
        mav.addObject("coupon", coupons);
        return mav;
    }

    @PostMapping("/user/addCoupon")
    public ModelAndView addCoupon(HttpServletRequest httpServletRequest, HttpSession session) {
        ModelAndView mav = new ModelAndView("Message");
        String couponNum = httpServletRequest.getParameter("coupon_num");
        if (couponService.addCouponToMember(couponNum, ((Member)session.getAttribute("member")).getId()) == false)
            mav.addObject("data", new Message("쿠폰 번호가 맞지 않거나 이미 등록된 쿠폰입니다.", "/user/coupon"));
        else
            mav.addObject("data", new Message("쿠폰을 등록하였습니다.", "/user/coupon"));
        return mav;
    }
}
