package coupang.challenge.controller;

import coupang.challenge.data.Member;
import coupang.challenge.data.Message;
import coupang.challenge.form.LoginForm;
import coupang.challenge.form.MemberForm;
import coupang.challenge.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 회원가입 버튼을 누르면, createMemberForm으로 이동시켜준다.
    @GetMapping("/member/new")
    public String moveJoinForm() { return "member/createMemberForm"; }

    @PostMapping("/member/new")
    public ModelAndView join(MemberForm form) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Message");
        boolean checkEmail = memberService.join(form);
        if (checkEmail == false)
            mav.addObject("data", new Message("이미 가입된 아이디입니다.", "/member/new"));
        else
            mav.addObject("data", new Message("회원가입이 완료되었습니다.", "/"));
        return mav;
    }

    @GetMapping("/member/login")
    public String moveLoginForm() { return "/member/loginForm"; }

    @PostMapping("/member/login")
    public ModelAndView login(LoginForm form, HttpSession session) {
        memberService.login(form, session);
        ModelAndView mav = new ModelAndView();
        if (session.getAttribute("member") == null) {
            mav.setViewName("Message");
            mav.addObject("data", new Message("아이디나 비밀번호가 맞지 않습니다.", "/member/login"));
        } else {
            mav.setViewName("redirect:/");
        }
        return mav;
    }

    @GetMapping("/member/logout")
    public ModelAndView logout(HttpSession session) {
        memberService.logout(session);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/");
        return mav;
    }

    @GetMapping("/member/joinWow")
    public String joinWow(HttpSession session) {
        session.setAttribute("member",
                memberService.changeMembership(session.getAttribute("member")));
        return "redirect:/user/rocketWow";
    }

    @PostMapping("/member/check")
    public ModelAndView checkPass(HttpServletRequest httpServletRequest, HttpSession session) {
        ModelAndView mav = new ModelAndView();
        if (memberService.checkPass(httpServletRequest, ((Member)session.getAttribute("member")).getPassword()) == true)
            mav.setViewName("user/editPersonalInfo");
        else {
            mav.setViewName("Message");
            mav.addObject("data", new Message("비밀번호가 맞지 않습니다.", "../user/editMyInfo"));
        }
        return mav;
    }

    @PostMapping("/member/passCheck")
    public ModelAndView checkPassAndEdit(HttpServletRequest httpServletRequest, HttpSession session) {
        ModelAndView mav = new ModelAndView("Message");
        if (memberService.checkPass(httpServletRequest, ((Member)session.getAttribute("member")).getPassword()) == true) {
            memberService.changePass(httpServletRequest, (Member)session.getAttribute("member"));
            mav.addObject("data", new Message("비밀번호가 변경되었습니다.", "../user/canEdit"));
        } else {
            mav.addObject("data", new Message("현재 비밀번호가 맞지 않습니다.", "../user/canEdit"));
        }
        return mav;
    }

    @PostMapping("/member/changeEmail")
    public String changeEmail(HttpServletRequest httpServletRequest, HttpSession session) {
        memberService.changeEmail(httpServletRequest, (Member)session.getAttribute("member"));
        return "redirect:/user/canEdit";
    }

    @PostMapping("/member/changeUsername")
    public String changeUsername(HttpServletRequest httpServletRequest, HttpSession session) {
        memberService.changeUsername(httpServletRequest, (Member)session.getAttribute("member"));
        return "redirect:/user/canEdit";
    }

    @PostMapping("/member/changePhone")
    public String changePhone(HttpServletRequest httpServletRequest, HttpSession session) {
        memberService.changePhone(httpServletRequest, (Member)session.getAttribute("member"));
        return "redirect:/user/canEdit";
    }
}
