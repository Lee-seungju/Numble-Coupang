package coupang.challenge.controller;

import coupang.challenge.data.Delivery;
import coupang.challenge.data.Member;
import coupang.challenge.form.DeliveryForm;
import coupang.challenge.service.DeliveryService;
import coupang.challenge.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {

    private DeliveryService deliveryService;

    @Autowired
    public UserController(DeliveryService deliveryService, MemberService memberService) {
        this.deliveryService = deliveryService;
    }

    @GetMapping("user")
    public String createUser() {
        return "user/userForm";
    }

    @GetMapping("/user/showDList")
    public ModelAndView moveDList(HttpSession session) {
        ModelAndView mav = new ModelAndView("user/deliveryList");;
        if (session.getAttribute("member") == null)
            return mav;
        List<Delivery> delivery = deliveryService.findList(((Member)session.getAttribute("member")).getId());
        mav.addObject("delivery", delivery);
        return mav;
    }

    @GetMapping("user/createAddress")
    public String moveDCreate() { return "user/deliveryForm"; }

    @PostMapping("user/deliveryForm")
    public String AddDelivery(HttpSession session, DeliveryForm deliveryForm) {
        deliveryService.add(deliveryForm, session);
        return "redirect:/user/showDList";
    }

    @GetMapping("user/deliverySelect")
    public String moveDS() { return "user/deliverySelect"; }

    @GetMapping("/user/rocketWow")
    public String moveWow() { return "user/rocketMembership"; }

    @GetMapping("/user/editMyInfo")
    public String movePersonalInfo() { return "user/accessPersonalInfo"; }

    @GetMapping("user/canEdit")
    public String moveEditInfo() { return "user/editPersonalInfo"; }
}
