package coupang.challenge.service;

import coupang.challenge.data.Delivery;
import coupang.challenge.form.DeliveryForm;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface DeliveryService {
    void addOrChange(Object delivery, DeliveryForm deliveryForm, Long memberId);
    List<Delivery> sortMainDelivery(Long memberId);
    void makeNull(HttpSession session);
    void setSessionById(HttpSession session, Long id);
}
