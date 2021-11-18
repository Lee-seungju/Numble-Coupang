package coupang.challenge.service;

import coupang.challenge.data.Delivery;
import coupang.challenge.form.DeliveryForm;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface DeliveryService {
    void addOrChange(Object delivery, DeliveryForm deliveryForm, HttpSession session);
    List<Delivery> sortMainDelivery(HttpSession session);
    void makeNull(HttpSession session);
    void setSessionById(HttpSession session, Long id);
}
