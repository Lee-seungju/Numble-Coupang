package coupang.challenge.service;

import coupang.challenge.data.Product;
import coupang.challenge.form.SearchForm;

import java.util.List;

public interface ProductService {
    List<SearchForm>   searchProduct(String title);
    List<SearchForm>    sortLowPrice(List<SearchForm> searchForms);
    List<SearchForm>    sortBigPrice(List<SearchForm> searchForms);
    List<SearchForm>    sortRateNum(List<SearchForm> searchForms);
    List<SearchForm>    sortLatest(List<SearchForm> searchForms);
}
