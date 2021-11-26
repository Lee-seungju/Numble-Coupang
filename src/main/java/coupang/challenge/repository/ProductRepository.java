package coupang.challenge.repository;

import coupang.challenge.data.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product>   findByTitle(String title);
}
