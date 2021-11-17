package coupang.challenge.service;

import coupang.challenge.data.Delivery;
import coupang.challenge.form.DeliveryForm;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Stream;

public interface DeliveryService {
    void add(DeliveryForm deliveryForm, HttpSession session);
    List<Delivery> sortMainDelivery(HttpSession session);
    List<Delivery> findList(Long memberId);
}
