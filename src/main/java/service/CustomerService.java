package service;

import com.google.gson.JsonObject;
import model.Customer;
import model.Invoice;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.ServiceUtil;

import java.util.List;

@Transactional
@Service
public class CustomerService {
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Customer getCustomer(int id) {
        return (Customer) sessionFactory.getCurrentSession().get(Customer.class, id);
    }

    public List<Customer> getAllCustomers(Integer page, Integer limit){
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Customer.class);
        ServiceUtil.addPaginationRestriction(criteria, page, limit);
        ServiceUtil.preventDuplicateRecord(criteria);
        return criteria.list();
    }

    public String getRevenue(int customerID, Long from, Long to) {
        JsonObject response = new JsonObject();
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Invoice.class);
        ServiceUtil.addTimeRestriction(criteria, from, to);
        ServiceUtil.addCustomerRestriction(criteria, customerID);
        criteria.setProjection(Projections.sum("totalValue"));
        ServiceUtil.preventDuplicateRecord(criteria);
        if (criteria.uniqueResult() != null) {
            response.addProperty("revenue", (double) criteria.uniqueResult());
        }
        return response.toString();
    }

    public List<Customer> searchCustomersByName(String name){
        Query query = sessionFactory.getCurrentSession().createQuery("from Customer where name like :name");
        query.setString("name", "%" + name + "%");
        return query.list();
    }

    public List<Customer> searchCustomersByPhone(String phone){
        Query query = sessionFactory.getCurrentSession().createQuery("from Customer where phone like :phone");
        query.setString("phone", "%" + phone + "%");
        return query.list();
    }

    public List<Customer> searchCustomersByAddress(String address){
        Query query = sessionFactory.getCurrentSession().createQuery("from Customer where address like :address");
        query.setString("address", "%" + address + "%");
        return query.list();
    }

    public Customer updateCustomer(Customer customer){
        sessionFactory.getCurrentSession().update(customer);
        return customer;
    }

    public Customer deleteCustomer(Customer customer){
        sessionFactory.getCurrentSession().delete(customer);
        return customer;
    }

    public Customer saveCustomer(Customer customer){
        sessionFactory.getCurrentSession().save(customer);
        return customer;
    }
}
