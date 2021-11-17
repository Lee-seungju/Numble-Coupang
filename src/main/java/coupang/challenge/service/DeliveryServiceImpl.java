package coupang.challenge.service;

import coupang.challenge.data.Delivery;
import coupang.challenge.data.Member;
import coupang.challenge.form.DeliveryForm;
import coupang.challenge.repository.DeliveryRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Transactional
public class DeliveryServiceImpl implements DeliveryService {

    private DeliveryRepository deliveryRepository;

    public DeliveryServiceImpl(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    @Override
    public void add(DeliveryForm deliveryForm, HttpSession httpSession) {
        Delivery delivery = new Delivery();
        delivery.setMember_id(((Member)httpSession.getAttribute("member")).getId());
        delivery.setName(deliveryForm.getName());
        delivery.setPhone_number(deliveryForm.getPhone_number());
        delivery.setAddress(deliveryForm.getAddress());
        delivery.setWho_take(deliveryForm.getWho_take());
        delivery.setMain(deliveryForm.isMain());
        checkMain(delivery, delivery.getMember_id(), delivery.getId());

        deliveryRepository.save(delivery);
    }

    @Override
    public List<Delivery> sortMainDelivery(HttpSession session) {
        Stream<Delivery> mainDelivery = (findList(((Member)session.getAttribute("member")).getId()))
                .stream().filter(d -> d.isMain()==true);
        Stream<Delivery> notMain = (findList(((Member)session.getAttribute("member")).getId()))
                .stream().filter(d -> d.isMain()==false);
        return Stream.concat(mainDelivery, notMain).collect(Collectors.toList());
    }

    private void checkMain(Delivery delivery, Long memberId, Long id) {
        List<Delivery> deliveries = deliveryRepository.findById(memberId);
        if (delivery.isMain() == true) {
            for (int i=0; i<deliveries.size(); i++) {
                if (deliveries.get(i).getId() != id)
                    deliveries.get(i).setMain(false);
            }
        }
        else {
            delivery.setMain(true);
            for (int i=0; i<deliveries.size(); i++) {
                if (deliveries.get(i).getId() != id && deliveries.get(i).isMain() == true)
                    delivery.setMain(false);
            }
        }
    }

    @Override
    public List<Delivery> findList(Long memberId) {
        return deliveryRepository.findById(memberId);
    }
}
