package sg.com.premiertaxi.safeentryapi.service;

import java.util.List;


import sg.com.premiertaxi.safeentryapi.model.Visitor;

public interface VisitorService {
	
	List<Visitor> get(String date_from, 
					String date_to, 
					String mobile_no, 
					String visitor_name,
					String visitor_nric,
					String vehicle_no,
					String visitor_temp
					);
	
	Visitor get(int id);
	
	void save(Visitor e);
	
	void delete(int id);

}
