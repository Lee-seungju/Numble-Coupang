package coupang.challenge.service;

import coupang.challenge.data.Delivery;
import coupang.challenge.data.Member;
import coupang.challenge.form.DeliveryForm;
import coupang.challenge.repository.DeliveryRepository;
import coupang.challenge.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Transactional
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepository deliveryRepository;

    public DeliveryServiceImpl(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    @Override
    public void addOrChange(Object delivery, DeliveryForm deliveryForm, Long memberId) {
        deliveryRepository.insertData(delivery, deliveryForm, memberId);
    }

    @Override
    public List<Delivery> sortMainDelivery(Long memberId) {
        Stream<Delivery> mainDelivery = (deliveryRepository.findById(memberId))
                .stream().filter(d -> d.isMain()==true);
        Stream<Delivery> notMain = (deliveryRepository.findById(memberId))
                .stream().filter(d -> d.isMain()==false);
        return Stream.concat(mainDelivery, notMain).collect(Collectors.toList());
    }

    @Override
    public void makeNull(HttpSession session) {
        if (session.getAttribute("delivery") == null)
            session.setAttribute("delivery", deliveryRepository.makeNull(session));
    }

    @Override
    public void setSessionById(HttpSession session, Long id) {
        session.setAttribute("delivery", deliveryRepository.findOne(id).get());
    }
}
