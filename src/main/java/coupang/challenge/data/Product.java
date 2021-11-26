package coupang.challenge.data;

import org.apache.logging.log4j.util.ProcessIdUtil;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String company;
    private Long price;
    private boolean goldbox;
    private boolean rocket_shopping;
    private float rate;
    private int rate_number;
    private boolean clothe_product;
    private String detailpage_url;
    private String type;

    @OneToMany(mappedBy = "product")
    List<ProductThumbnail> productThumbnails = new ArrayList<>();

    public List<ProductThumbnail> getProductThumbnails() {
        return productThumbnails;
    }

    public void setProductThumbnails(List<ProductThumbnail> productThumbnails) {
        this.productThumbnails = productThumbnails;
    }

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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public boolean isGoldbox() {
        return goldbox;
    }

    public void setGoldbox(boolean goldbox) {
        this.goldbox = goldbox;
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

    public boolean isClothe_product() {
        return clothe_product;
    }

    public void setClothe_product(boolean clothe_product) {
        this.clothe_product = clothe_product;
    }

    public String getDetailpage_url() {
        return detailpage_url;
    }

    public void setDetailpage_url(String detailpage_url) {
        this.detailpage_url = detailpage_url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

