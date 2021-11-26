package coupang.challenge.form;

public class CouponViewForm {

    private Long id;
    private String name;
    private int discount;
    private int if_buy;
    private String validity;
    private boolean use_coupon;

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
}
