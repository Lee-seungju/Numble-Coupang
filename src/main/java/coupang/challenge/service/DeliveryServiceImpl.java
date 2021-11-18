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

    private DeliveryRepository deliveryRepository;
    private MemberRepository memberRepository;

    public DeliveryServiceImpl(DeliveryRepository deliveryRepository, MemberRepository memberRepository) {
        this.deliveryRepository = deliveryRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public void addOrChange(Object delivery, DeliveryForm deliveryForm, HttpSession session) {
        deliveryRepository.insertData(delivery, deliveryForm,
                memberRepository.getMemberFromSession(session).getId());
    }

    @Override
    public List<Delivery> sortMainDelivery(HttpSession session) {
        Stream<Delivery> mainDelivery = (deliveryRepository.findById(memberRepository.getMemberFromSession(session).getId()))
                .stream().filter(d -> d.isMain()==true);
        Stream<Delivery> notMain = (deliveryRepository.findById(memberRepository.getMemberFromSession(session).getId()))
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
