package service;

import com.google.gson.JsonObject;
import model.Invoice;
import model.Staff;
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
public class StaffService {
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Staff getStaff(int id) {
        return (Staff) sessionFactory.getCurrentSession().get(Staff.class, id);
    }

    public List<Staff> getAllStaffs(Integer page, Integer limit){
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Staff.class);
        ServiceUtil.addPaginationRestriction(criteria, page, limit);
        ServiceUtil.preventDuplicateRecord(criteria);
        return criteria.list();
    }

    public String getRevenue(int staffID, Long from, Long to) {
        JsonObject response = new JsonObject();
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Invoice.class);
        ServiceUtil.addTimeRestriction(criteria, from, to);
        ServiceUtil.addStaffRestriction(criteria, staffID);
        criteria.setProjection(Projections.sum("totalValue"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        if (criteria.uniqueResult() != null) {
            response.addProperty("revenue", (double) criteria.uniqueResult());
        }
        return response.toString();
    }

    public List<Staff> searchStaffsByName(String name){
        Query query = sessionFactory.getCurrentSession().createQuery("from Staff where name like :name");
        query.setString("name", "%" + name + "%");
        return query.list();
    }

    public Staff updateStaff(Staff staff){
        sessionFactory.getCurrentSession().update(staff);
        return staff;
    }

    public Staff deleteStaff(Staff staff){
        sessionFactory.getCurrentSession().delete(staff);
        return staff;
    }

    public Staff saveStaff(Staff staff){
        sessionFactory.getCurrentSession().save(staff);
        return staff;
    }
}
