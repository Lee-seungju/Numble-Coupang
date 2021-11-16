package coupang.challenge;

import coupang.challenge.repository.DeliveryRepository;
import coupang.challenge.repository.DeliveryRepositoryImpl;
import coupang.challenge.repository.MemberRepositoryImpl;
import coupang.challenge.repository.MemberRepository;
import coupang.challenge.service.DeliveryService;
import coupang.challenge.service.DeliveryServiceImpl;
import coupang.challenge.service.MemberServiceImpl;
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
    public DeliveryService deliveryService() { return new DeliveryServiceImpl(deliveryRepository()); }
}
