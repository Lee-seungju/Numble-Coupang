package coupang.challenge.service;

import coupang.challenge.data.Delivery;
import coupang.challenge.data.Member;
import coupang.challenge.form.DeliveryForm;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface DeliveryService {
    Delivery insertData(Delivery delivery, DeliveryForm deliveryForm, HttpSession session);
    void addOrChange(Object delivery, DeliveryForm deliveryForm, HttpSession session);
    List<Delivery> sortMainDelivery(HttpSession session);
    Optional<Delivery> findOne(Long deliveryId);
    List<Delivery> findList(Long memberId);
    Delivery makeNull(HttpSession session);
}
