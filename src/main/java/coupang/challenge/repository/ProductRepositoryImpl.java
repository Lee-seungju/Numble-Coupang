package coupang.challenge.repository;

import coupang.challenge.data.Member;
import coupang.challenge.data.Product;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class ProductRepositoryImpl implements ProductRepository{

    private final EntityManager em;

    public ProductRepositoryImpl(EntityManager em) {
        this.em = em;
    }


    @Override
    public List<Product> findByTitle(String title) {
        return em.createQuery("select m from Product m where m.title like :title ", Product.class)
                .setParameter("title", '%' + title + '%')
                .getResultList();
    }
}
