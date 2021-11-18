package coupang.challenge.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int discount;
    private int if_buy;
    private int use_day;
    private String number;
    private String validity;
    private boolean use_coupon;
    private Long member_id;

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public boolean isUse_coupon() {
        return use_coupon;
    }

    public void setUse_coupon(boolean use_coupon) {
        this.use_coupon = use_coupon;
    }

    public Long getMember_id() {
        return member_id;
    }

    public void setMember_id(Long member_id) {
        this.member_id = member_id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getIf_buy() {
        return if_buy;
    }

    public void setIf_buy(int if_buy) {
        this.if_buy = if_buy;
    }

    public int getUse_day() {
        return use_day;
    }

    public void setUse_day(int use_day) {
        this.use_day = use_day;
    }
}
