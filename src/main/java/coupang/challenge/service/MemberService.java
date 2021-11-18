package coupang.challenge.service;

import coupang.challenge.form.LoginForm;
import coupang.challenge.data.Member;
import coupang.challenge.form.MemberForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public interface MemberService {
    void login(LoginForm form, HttpSession session);
    void logout(HttpSession session);
    boolean join(MemberForm member);
    boolean checkPass(HttpServletRequest httpServletRequest, HttpSession session);
    Member changeMembership(Object m);
    List<Member> findMembers();
    Optional<Member> findOne(Long memberId);
    void changePass(HttpServletRequest httpServletRequest, HttpSession session);
    void changeEmail(HttpServletRequest httpServletRequest, HttpSession session);
    void changeUsername(HttpServletRequest httpServletRequest, HttpSession session);
    void changePhone(HttpServletRequest httpServletRequest, HttpSession session);
}
