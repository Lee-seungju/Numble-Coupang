package coupang.challenge.repository;

import coupang.challenge.data.Delivery;
import coupang.challenge.data.Member;
import coupang.challenge.form.LoginForm;

import java.util.List;
import java.util.Optional;

public interface DeliveryRepository {
    void save(Delivery delivery);
//    Optional<Delivery> viewDelivery(DeliveryForm deliveryForm);
    List<Delivery> findById(Long memberId);
    List<Delivery> findAll();
}
