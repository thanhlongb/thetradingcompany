package util;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServiceUtil {
    private static final int RECORD_PER_PAGE = 5;

    public static void addTimeRestriction(Criteria criteria, Long from, Long to) {
        if (from != null) {
            criteria.add(Restrictions.gt("creationTime", new Date(from)));
        }
        if (to != null) {
            criteria.add(Restrictions.lt("creationTime", new Date(to)));
        }
    }

    public static void addPaginationRestriction(Criteria criteria, Integer page, Integer limit) {
        int recordPerPage = (limit == null) ? RECORD_PER_PAGE : limit;
        if (page != null && page >= 0) {
            criteria.setFirstResult(page * recordPerPage);
            criteria.setMaxResults(page * recordPerPage + recordPerPage);
        }
    }

    public static void addCustomerRestriction(Criteria criteria, Integer customerID) {
        if (customerID != null) {
            criteria.add(Restrictions.eq("customer.id", customerID));
        }
    }

    public static void addStaffRestriction(Criteria criteria, Integer staffID) {
        if (staffID != null) {
            criteria.add(Restrictions.eq("staff.id", staffID));
        }
    }

    public static void preventDuplicateRecord(Criteria criteria) {
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
    }

    public static String timestampToDate(Long timestamp) {
        DateFormat dateFormat = new SimpleDateFormat("MMMM dd yyyy");
        return dateFormat.format(new Date(timestamp));
    }
}
