package coupang.challenge.controller;

import coupang.challenge.data.Product;
import coupang.challenge.data.ProductThumbnail;
import coupang.challenge.form.SearchForm;
import coupang.challenge.service.ProductService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product/searchResult")
    public String moveSearchResult() {
        return "/product/searchResult";
    }

    // 검색 버튼을 누르면 여기로 온다.
    @PostMapping("/product/search")
    public String searchProduct(HttpServletRequest httpServletRequest, HttpSession session) {
        String search = httpServletRequest.getParameter("search");
        List<SearchForm> searchForms = productService.searchProduct(search);
        session.setAttribute("result", searchForms);
//        salePriceAsc(session);
        return "/product/searchResult";
    }

    @GetMapping("/product/salePriceAsc")
    public String salePriceAsc(HttpSession session) {
        List<SearchForm> searchForms = (List<SearchForm>)session.getAttribute("result");
        session.setAttribute("result", productService.sortLowPrice(searchForms));
        return "/product/searchResult";
    }

    @GetMapping("/product/salePriceDesc")
    public String salePriceDesc(HttpSession session) {
        List<SearchForm> searchForms = (List<SearchForm>)session.getAttribute("result");
        session.setAttribute("result", productService.sortBigPrice(searchForms));
        return "product/searchResult";
    }

    @GetMapping("/product/saleCountDesc")
    public String saleCountDesc(HttpSession session) {
        List<SearchForm> searchForms = (List<SearchForm>)session.getAttribute("result");
        session.setAttribute("result", productService.sortRateNum(searchForms));
        return "product/searchResult";
    }

    @GetMapping("/product/latestAsc")
    public String latestAsc(HttpSession session) {
        List<SearchForm> searchForms = (List<SearchForm>)session.getAttribute("result");
        session.setAttribute("result", productService.sortLatest(searchForms));
        return "product/searchResult";
    }

}
