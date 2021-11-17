package coupang.challenge.repository;

import coupang.challenge.form.LoginForm;
import coupang.challenge.data.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    void save(Member member);
    Optional<Member> viewMember(LoginForm member);
    void useMerge(Member member);
    Optional<Member> findById(Long memberId);
    Optional<Member> findByEmail(String email);
    List<Member> findAll();
}
