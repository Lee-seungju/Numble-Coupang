package coupang.challenge.form;

public class DeliveryForm {

    private String name;
    private String address;
    private String phone_number;
    private String who_take;
    private boolean main;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getWho_take() {
        return who_take;
    }

    public void setWho_take(String who_take) {
        this.who_take = who_take;
    }

    public boolean isMain() {
        return main;
    }

    public void setMain(boolean main) {
        this.main = main;
    }
}
