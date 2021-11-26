package coupang.challenge.service;

import coupang.challenge.data.Product;
import coupang.challenge.form.SearchForm;
import coupang.challenge.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<SearchForm> searchProduct(String title) {
        List<Product> byTitle = productRepository.findByTitle(title);
        List<SearchForm> searchForms = new ArrayList<>();
        for(int i=0; i<byTitle.size(); i++) {
            searchForms.add(setSearchForm(byTitle.get(i)));
        }
        return searchForms;
    }

    @Override
    public List<SearchForm> sortLowPrice(List<SearchForm> searchForms) {
        int len = searchForms.size() - 1;
        for(int i=0; i<len; i++) {
            for(int j=0; j<len-i; j++) {
                if (searchForms.get(j).getPrice() > searchForms.get(j+1).getPrice()) {
                    SearchForm tmp = searchForms.get(j);
                    searchForms.set(j, searchForms.get(j+1));
                    searchForms.set(j+1, tmp);
                }
            }
        }
        return searchForms;
    }

    @Override
    public List<SearchForm> sortBigPrice(List<SearchForm> searchForms) {
        int len = searchForms.size() - 1;
        for(int i=0; i<len; i++) {
            for(int j=0; j<len-i; j++) {
                if (searchForms.get(j).getPrice() < searchForms.get(j+1).getPrice()) {
                    SearchForm tmp = searchForms.get(j);
                    searchForms.set(j, searchForms.get(j+1));
                    searchForms.set(j+1, tmp);
                }
            }
        }
        return searchForms;
    }

    @Override
    public List<SearchForm> sortRateNum(List<SearchForm> searchForms) {
        int len = searchForms.size() - 1;
        for(int i=0; i<len; i++) {
            for(int j=0; j<len-i; j++) {
                if (searchForms.get(j).getRate_number() < searchForms.get(j+1).getRate_number()) {
                    SearchForm tmp = searchForms.get(j);
                    searchForms.set(j, searchForms.get(j+1));
                    searchForms.set(j+1, tmp);
                }
            }
        }
        return searchForms;
    }

    @Override
    public List<SearchForm> sortLatest(List<SearchForm> searchForms) {
        int len = searchForms.size() - 1;
        for(int i=0; i<len; i++) {
            for(int j=0; j<len-i; j++) {
                if (searchForms.get(j).getId() < searchForms.get(j+1).getId()) {
                    SearchForm tmp = searchForms.get(j);
                    searchForms.set(j, searchForms.get(j+1));
                    searchForms.set(j+1, tmp);
                }
            }
        }
        return searchForms;
    }

    private SearchForm setSearchForm(Product product) {
        SearchForm searchForm = new SearchForm();
        searchForm.setId(product.getId());
        searchForm.setPrice(product.getPrice());
        searchForm.setRate(product.getRate());
        searchForm.setRate_number(product.getRate_number());
        searchForm.setRocket_shopping(product.isRocket_shopping());
        searchForm.setThumbnail_url(product.getProductThumbnails().get(0).getThumbnail_url());
        searchForm.setTitle(product.getTitle());
        return searchForm;
    }
}
