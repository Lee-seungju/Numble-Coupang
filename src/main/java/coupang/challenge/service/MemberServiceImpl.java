package coupang.challenge.service;

import coupang.challenge.form.LoginForm;
import coupang.challenge.data.Member;
import coupang.challenge.form.MemberForm;
import coupang.challenge.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Transactional
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void login(LoginForm form, HttpSession session) {
        memberRepository.viewMember(form)
            .ifPresent((m) -> {
                session.setAttribute("member", m);
            });
    }

    @Override
    public void logout(HttpSession session) {
        session.invalidate();
    }

    /**
     * 회원가입
     */
    @Override
    public boolean join(MemberForm form) {
        if (emailCheck(form.getEmail()) == false)
            return false;
        memberRepository.makeNew(form);
        return true;
    }

    @Override
    public boolean checkPass(HttpServletRequest httpServletRequest, HttpSession session) {
        return httpServletRequest.getParameter("password").
                equals((memberRepository.getMemberFromSession(session)).getPassword());
    }

    @Override
    public Member changeMembership(Object m) {
        Member member = memberRepository.findById(((Member)m).getId()).get();
        member.setRocket_membership(true);
        memberRepository.useMerge(member);
        return member;
    }

    private boolean emailCheck(String email) {
        try {
            validateDuplicateMember(email); // 중복회원 체크
        } catch(IllegalStateException e) {
            return false;
        }
        return true;
    }

    private void validateDuplicateMember(String email) {
        memberRepository.findByEmail(email)
                .ifPresent((m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                }));
    }

    /**
     * 전체 회원 조회
     */
    @Override
    public List<Member> findMembers() { return memberRepository.findAll(); }

    @Override
    public Optional<Member> findOne(Long memberId) { return memberRepository.findById(memberId); }

    @Override
    public void changePass(HttpServletRequest httpServletRequest, HttpSession session) {
        Member member = (Member)session.getAttribute("member");
        member.setPassword(httpServletRequest.getParameter("nPassword"));
        memberRepository.useMerge(member);
    }

    @Override
    public void changeEmail(HttpServletRequest httpServletRequest, HttpSession session) {
        Member member = (Member)session.getAttribute("member");
        member.setEmail(httpServletRequest.getParameter("Email"));
        memberRepository.useMerge(member);
    }

    @Override
    public void changeUsername(HttpServletRequest httpServletRequest, HttpSession session) {
        Member member = memberRepository.getMemberFromSession(session);
        member.setUsername(httpServletRequest.getParameter("Username"));
        memberRepository.useMerge(member);
    }

    @Override
    public void changePhone(HttpServletRequest httpServletRequest, HttpSession session) {
        Member member = memberRepository.getMemberFromSession(session);
        member.setPhone_number(httpServletRequest.getParameter("Phone"));
        memberRepository.useMerge(member);
    }
}
