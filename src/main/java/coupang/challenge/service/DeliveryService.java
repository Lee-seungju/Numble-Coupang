package coupang.challenge.service;

import coupang.challenge.data.Delivery;
import coupang.challenge.form.DeliveryForm;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface DeliveryService {
    void add(DeliveryForm deliveryForm, HttpSession session);
    List<Delivery> findList(Long memberId);
}
