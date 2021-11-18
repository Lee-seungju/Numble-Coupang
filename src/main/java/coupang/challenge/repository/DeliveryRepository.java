package coupang.challenge.repository;

import coupang.challenge.data.Delivery;
import coupang.challenge.data.Member;
import coupang.challenge.form.DeliveryForm;
import coupang.challenge.form.LoginForm;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public interface DeliveryRepository {
    void save(Delivery delivery);
    void useMerge(Delivery delivery);
    void insertData(Object delivery, DeliveryForm deliveryForm, Long id);
    Delivery makeNull(HttpSession session);
    List<Delivery> findById(Long memberId);
    Optional<Delivery> findOne(Long deliveryId);
    List<Delivery> findAll();
}
