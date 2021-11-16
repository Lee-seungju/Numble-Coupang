package coupang.challenge.service;


import coupang.challenge.data.Member;
import coupang.challenge.form.MemberForm;
import coupang.challenge.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;

@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberServiceImpl memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void 회원가입() {
        //given
        MemberForm member = new MemberForm();
        member.setUsername("spring");
        member.setEmail("spring@spring.com");
        member.setPassword("sss");
        member.setPhoneNumber("0101234567");

        //when
        boolean check = memberService.join(member);

        //then
        Member findMember = memberRepository.findByEmail(member.getEmail()).get();
        Assertions.assertThat(member.getUsername()).isEqualTo(findMember.getUsername());
        Assertions.assertThat(check).isEqualTo(true);
    }

    @Test
    public void 중복_회원_예외() {
        //given
        MemberForm member1 = new MemberForm();
        member1.setUsername("spring1");
        member1.setEmail("spring@spring.com");
        member1.setPassword("sss");
        member1.setPhoneNumber("0101234567");

        MemberForm member2 = new MemberForm();
        member2.setUsername("spring2");
        member2.setEmail("spring@spring.com");
        member2.setPassword("sss");
        member2.setPhoneNumber("01012345671");

        //when
        memberService.join(member1);
        IllegalStateException e = org.junit.jupiter.api.Assertions.assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        //then
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

    @Test
    public void 이름_널_체크() {
        //given
        MemberForm name_null = new MemberForm();
        name_null.setEmail("spring@spring.com");
        name_null.setPassword("sss");
        name_null.setPhoneNumber("0101234567");

        //when
        PersistenceException e1 = org.junit.jupiter.api.Assertions.assertThrows(PersistenceException.class, () -> memberService.join(name_null));

        //then
        Assertions.assertThat(e1.getMessage()).isEqualTo("org.hibernate.exception.ConstraintViolationException: could not execute statement");
    }

    @Test
    public void 이메일_널_체크() {
        //given
        MemberForm email_null = new MemberForm();
        email_null.setUsername("spring1");
        email_null.setPassword("sss");
        email_null.setPhoneNumber("0101234567");

        //when
        PersistenceException e = org.junit.jupiter.api.Assertions.assertThrows(PersistenceException.class, () -> memberService.join(email_null));

        //then
        Assertions.assertThat(e.getMessage()).isEqualTo("org.hibernate.exception.ConstraintViolationException: could not execute statement");
    }

    @Test
    public void 패스워드_널_체크() {
        //given
        MemberForm pass_null = new MemberForm();
        pass_null.setUsername("spring1");
        pass_null.setEmail("spring1@spring.com");
        pass_null.setPhoneNumber("0101234567");

        //when
        PersistenceException e = org.junit.jupiter.api.Assertions.assertThrows(PersistenceException.class, () -> memberService.join(pass_null));

        //then
        Assertions.assertThat(e.getMessage()).isEqualTo("org.hibernate.exception.ConstraintViolationException: could not execute statement");
    }

    @Test
    public void 번호_널_체크() {
        //given
        MemberForm phone_null = new MemberForm();
        phone_null.setUsername("spring1");
        phone_null.setEmail("spring2@spring.com");
        phone_null.setPassword("sss");

        //when
        PersistenceException e = org.junit.jupiter.api.Assertions.assertThrows(PersistenceException.class, () -> memberService.join(phone_null));

        //then
        Assertions.assertThat(e.getMessage()).isEqualTo("org.hibernate.exception.ConstraintViolationException: could not execute statement");
    }
}
