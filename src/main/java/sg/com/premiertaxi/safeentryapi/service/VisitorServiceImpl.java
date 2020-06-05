package sg.com.premiertaxi.safeentryapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.com.premiertaxi.safeentryapi.dao.VisitorDAO;
import sg.com.premiertaxi.safeentryapi.model.Visitor;

@Service
public class VisitorServiceImpl implements VisitorService {
	
	@Autowired
	private VisitorDAO visitorDAO;

	@Transactional
	@Override
	public List<Visitor> get(String date_from, 
							String date_to, 
							String mobile_no, 
							String visitor_name,
							String visitor_nric,
							String vehicle_no,
							String visitor_temp
							) {
		//
		return visitorDAO.get(date_from, 
							date_to, 
							mobile_no, 
							visitor_name, 
							visitor_nric,
							vehicle_no,
							visitor_temp
							);
	}

	@Transactional
	@Override
	public Visitor get(int id) {
		// method from visitorDAOImpl with id
		return visitorDAO.get(id);
	}

	@Transactional
	@Override
	public void save(Visitor visitor) {
		// method from visitorDAOImpl
		visitorDAO.save(visitor);
		
	}

	@Transactional
	@Override
	public void delete(int id) {
		// method from visitorDAOImpl
		visitorDAO.delete(id);
		
	}

}
