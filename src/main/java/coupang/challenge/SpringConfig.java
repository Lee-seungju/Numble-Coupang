package coupang.challenge;

import coupang.challenge.repository.*;
import coupang.challenge.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) { this.em = em; }

    @Bean
    public MemberRepository memberRepository() { return new MemberRepositoryImpl(em); }

    @Bean
    public DeliveryRepository deliveryRepository() { return new DeliveryRepositoryImpl(em); }

    @Bean
    public CouponRepository couponRepository() { return new CouponRepositoryImpl(em); }

    @Bean
    public ProductRepository productRepository() { return new ProductRepositoryImpl(em); }

    @Bean
    public MemberServiceImpl memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public DeliveryService deliveryService() { return new DeliveryServiceImpl(deliveryRepository()); }

    @Bean
    public CouponService couponService() { return new CouponServiceImpl(couponRepository()); }

    @Bean
    public ProductService productService() { return new ProductServiceImpl(productRepository()); }
}
