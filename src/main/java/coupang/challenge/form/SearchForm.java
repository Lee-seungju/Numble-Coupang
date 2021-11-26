package coupang.challenge.form;

public class SearchForm {

    private Long id;
    private String title;
    private Long price;
    private boolean rocket_shopping;
    private float rate;
    private int rate_number;
    private String thumbnail_url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public boolean isRocket_shopping() {
        return rocket_shopping;
    }

    public void setRocket_shopping(boolean rocket_shopping) {
        this.rocket_shopping = rocket_shopping;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public int getRate_number() {
        return rate_number;
    }

    public void setRate_number(int rate_number) {
        this.rate_number = rate_number;
    }

    public String getThumbnail_url() {
        return thumbnail_url;
    }

    public void setThumbnail_url(String thumbnail_url) {
        this.thumbnail_url = thumbnail_url;
    }
}
