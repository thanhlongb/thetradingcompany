package service;

import model.Product;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.ServiceUtil;

import java.util.List;

@Transactional
@Service
public class ProductService {
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Product getProduct(int id) {
        return (Product) sessionFactory.getCurrentSession().get(Product.class, id);
    }

    public List<Product> getAllProducts(Integer page, Integer limit){
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Product.class);
        ServiceUtil.addPaginationRestriction(criteria, page, limit);
        ServiceUtil.preventDuplicateRecord(criteria);
        return criteria.list();
    }

    public Product updateProduct(Product product){
        sessionFactory.getCurrentSession().update(product);
        return product;
    }

    public Product deleteProduct(Product product){
        sessionFactory.getCurrentSession().delete(product);
        return product;
    }

    public Product saveProduct(Product product){
        sessionFactory.getCurrentSession().save(product);
        return product;
    }
}
