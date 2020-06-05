package sg.com.premiertaxi.safeentryapi.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import sg.com.premiertaxi.safeentryapi.model.Visitor;

@Repository
public class VisitorDAOImpl implements VisitorDAO {
	
	// variables here
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyMMdd");

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<Visitor> get(String date_from, 
							String date_to, 
							String mobile_no, 
							String visitor_name,
							String visitor_nric,
							String vehicle_no,
							String visitor_temp
							) {
		
		// initialize here
		List<Visitor> list = new ArrayList<Visitor>();
		Date range_from = null;
		Date range_to = null;
		
		// test here
		System.out.println("this is test");
		
		// 
		try {
			// parse here
			range_from =  DATE_FORMAT.parse(date_from);
			range_to =  DATE_FORMAT.parse(date_to);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		// test here
//		System.out.println("mobile_no is: " + mobile_no);
		
		// check if valid range date here
		if(range_from!=null ) {
			// select here
			list = this.filterDateRouter(range_from, range_to);
			return list;
		}
		
		// check if nric is set
		if (visitor_nric != "default") {
			// select here
			list = this.filterStringType("nric", visitor_nric);
			return list;
		}
		
		// check if name is set
		if (visitor_name != "default") {
			// select here
			list = this.filterStringType("name", visitor_name);
			return list;
		}
		
		// check if mobile is set
		if(mobile_no != "default") {
			
			// select here
			list = this.filterStringType("mobile_no", mobile_no);
			return list;
		}
		
		// check if vehicle is set
		if(vehicle_no != "default") {
			
			// select here
			list = this.filterStringType("vehicle_no", vehicle_no);
			return list;
		}
		
		// check if temp is set
		if(visitor_temp != "default") {
			
			// 
			System.out.println("visitor_temp is: " + visitor_temp);
			
			// parse here
			double visitor_temp_parse = Double.parseDouble(visitor_temp);
			
			// select here
			list = this.filterNumberType("temp", visitor_temp_parse);
			return list;
		}
		
		// list all here
		list = this.listAll();
		return list;
	}
	
	public List<Visitor> listAll() {
		
		// initialize here
		List<Visitor> list = new ArrayList<Visitor>();
		
		// select all here
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Visitor> query = currentSession.createQuery("from Visitor", Visitor.class);
		list = new ArrayList<Visitor>(query.getResultList());
		return list;
	}
	
	public List<Visitor> filterStringType(String name, String value) {
		
		// initialize here
		List<Visitor> list = new ArrayList<Visitor>();
		
		// select all here
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Visitor> query = currentSession.createQuery("from Visitor visitor where visitor." + name + " like :value", Visitor.class);
		query.setParameter("value", "%"+value+"%");
		list = new ArrayList<Visitor>(query.getResultList());
		return list;
	}
	
	public List<Visitor> filterNumberType(String name, double value) {
		
		// initialize here
		List<Visitor> list = new ArrayList<Visitor>();
		
		// 
		System.out.println("name is: " + name);
		System.out.println("value is: " + value);
		
		// select all here
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Visitor> query = currentSession.createQuery("from Visitor visitor where visitor." + name + " = :value", Visitor.class);
		query.setParameter("value", value);
		list = new ArrayList<Visitor>(query.getResultList());
		return list;
	}
	
	public List<Visitor> filterDateRouter(Date range_from, Date range_to) {
		
		// initialize here
		List<Visitor> list = new ArrayList<Visitor>();
		
		// check if valid range date here
		if(range_from!=null  && range_to != null) {
			// date range here
			list = this.filterRangeDate(range_from, range_to);
		}else if(range_from!=null  && range_to == null) {
			// date/ by day here
			list = this.filterDay(range_from);
		}
		
		return list;
	}
	
	public List<Visitor> filterRangeDate(Date range_from, Date range_to) {
		
		// initialize here
		List<Visitor> list = new ArrayList<Visitor>();
		
		// select all here
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Visitor> query = currentSession.createQuery("from Visitor visitor where visitor.created_dt between :date_from and ADDDATE(:date_to, 1)", Visitor.class);
		query.setParameter("date_from", range_from);
		query.setParameter("date_to", range_to);
		list = new ArrayList<Visitor>(query.getResultList());
		return list;
	}
	
	public List<Visitor> filterDay(Date date) {
		
		// initialize here
		List<Visitor> list = new ArrayList<Visitor>();
		
		// select all here
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Visitor> query = currentSession.createQuery("from Visitor visitor where visitor.created_dt between :date and ADDDATE(:date, 1)", Visitor.class);
		query.setParameter("date", date);
		list = new ArrayList<Visitor>(query.getResultList());
		return list;
	}
	
	@Override
	public Visitor get(int id) {
		// select id here
		Session currentSession = entityManager.unwrap(Session.class);
		Visitor visitorObj = currentSession.get(Visitor.class, id);
		return visitorObj;
	}

	@Override
	public void save(Visitor visitor) {
		// insert here
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(visitor);
		
	}

	@Override
	public void delete(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		Visitor visitorObj = currentSession.get(Visitor.class, id);
		currentSession.delete(visitorObj);
		
	}

}
