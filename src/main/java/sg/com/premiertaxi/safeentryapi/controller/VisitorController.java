package sg.com.premiertaxi.safeentryapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sg.com.premiertaxi.safeentryapi.model.Visitor;
import sg.com.premiertaxi.safeentryapi.service.VisitorService;


//http://localhost:4200/
//*
@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class VisitorController {
	
	@Autowired
	private VisitorService visitorService;

	//@GetMapping("/visitor")
	@RequestMapping(value = "/visitor", method = RequestMethod.GET)
	public List<Visitor> get(@RequestParam("from") Optional<String> from, 
							@RequestParam("to") Optional<String> to, 
							@RequestParam("mobile") Optional<String> mobile,
							@RequestParam("name") Optional<String> name,
							@RequestParam("nric") Optional<String> nric,
							@RequestParam("vehicle") Optional<String> vehicle,
							@RequestParam("temp") Optional<String> temp
							) {
		
		// variables here
		String date_from = from.isPresent() ? from.get() : "default";
		String date_to = to.isPresent() ? to.get() : "default";
		String mobile_no = mobile.isPresent() ? mobile.get() : "default";
		String visitor_name = name.isPresent() ? name.get() : "default";
		String visitor_nric = nric.isPresent() ? nric.get() : "default";
		String vehicle_no = vehicle.isPresent() ? vehicle.get() : "default";
		String visitor_temp = temp.isPresent() ? temp.get() : "default";
		
		
		// this is the response
		return visitorService.get(date_from, 
								date_to, 
								mobile_no, 
								visitor_name, 
								visitor_nric,
								vehicle_no,
								visitor_temp
								);
	}
	
	@PostMapping("/visitor")
	public Visitor save(@RequestBody Visitor visitorObj) {
		visitorService.save(visitorObj);
		// this is the response
		return visitorObj;
	}
	
	@GetMapping("/visitor/{id}")
	public Visitor get(@PathVariable int id) {
		
		Visitor visitorObj = visitorService.get(id);
		
		//
		if (visitorObj == null) {
			throw new RuntimeException("id: " + id + " not found.");
		}
		// this is the response
		return visitorObj;
	}
	
	@DeleteMapping("/visitor/{id}")
	public String delete(@PathVariable int id) {
		
		visitorService.delete(id);
		// this is the response
		return "Deleted id: " + id;
	}
	
	@PutMapping("/visitor")
	public Visitor update(@RequestBody Visitor visitorObj) {
		visitorService.save(visitorObj);
		// this is the response
		return visitorObj;
	}
}
