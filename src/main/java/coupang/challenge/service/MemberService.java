package coupang.challenge.service;

import coupang.challenge.form.LoginForm;
import coupang.challenge.data.Member;
import coupang.challenge.form.MemberForm;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public interface MemberService {
    void login(LoginForm form, HttpSession session);
    void logout(HttpSession session);
    boolean join(MemberForm member);
    boolean checkPass(String pass, Object member);
    Member changeMembership(Object m);
    List<Member> findMembers();
    Optional<Member> findOne(Long memberId);
}
