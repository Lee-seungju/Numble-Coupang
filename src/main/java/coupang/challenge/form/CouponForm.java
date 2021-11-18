package coupang.challenge.form;

public class CouponForm {

    private String name;
    private int discount;
    private int if_buy;
    private int use_day;
    private String number;

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
