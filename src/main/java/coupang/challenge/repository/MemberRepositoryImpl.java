package coupang.challenge.repository;

import coupang.challenge.form.LoginForm;
import coupang.challenge.data.Member;
import coupang.challenge.form.MemberForm;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public class MemberRepositoryImpl implements MemberRepository{

    private final EntityManager em;

    public MemberRepositoryImpl(EntityManager em) { this.em = em; }

    @Override
    public void save(Member member) {
        em.persist(member);
    }

    @Override
    public Optional<Member> viewMember(LoginForm member) {
        Optional<Member> m = findByEmail(member.getEmail());
        if (m.isPresent() && !(m.get().getPassword().equals(member.getPassword())))
            m = Optional.empty();
        return m;
    }

    @Override
    public void useMerge(Member member) {
        em.merge(member);
    }

    @Override
    public Optional<Member> findById(Long memberId) {
        Member member = em.find(Member.class, memberId);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        List<Member> result = em.createQuery("select m from Member m where m.email = :email", Member.class)
                .setParameter("email", email)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    @Override
    public void makeNew(MemberForm form) {
        Member member = new Member();
        member.setEmail(form.getEmail());
        member.setPassword(form.getPassword());
        member.setUsername(form.getUsername());
        member.setPhone_number(form.getPhoneNumber());
        save(member);
    }
}
