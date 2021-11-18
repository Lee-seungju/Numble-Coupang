package coupang.challenge;

import coupang.challenge.repository.*;
import coupang.challenge.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class Springconfig {

    private final DataSource dataSource;
    private final EntityManager em;

    @Autowired
    public Springconfig(DataSource dataSource, EntityManager em) {
        this.dataSource = dataSource;
        this.em = em;
    }

    @Bean
    public MemberServiceImpl memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemberRepositoryImpl(em);
    }

    @Bean
    public DeliveryRepository deliveryRepository() { return new DeliveryRepositoryImpl(em); }

    @Bean
    public DeliveryService deliveryService() { return new DeliveryServiceImpl(deliveryRepository(), memberRepository()); }

    @Bean
    public CouponRepository couponRepository() { return new CouponRepositoryImpl(em); }

    @Bean
    public CouponService couponService() { return new CouponServiceImpl(couponRepository(), memberRepository()); }
}
