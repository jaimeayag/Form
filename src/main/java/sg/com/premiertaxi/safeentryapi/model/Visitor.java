package sg.com.premiertaxi.safeentryapi.model;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "tbl_visitor_entry")
public class Visitor {

	// variables here
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private Integer id;
	@Column
	private String name;
	@Column
	private String gender;
	@Column
	private String department;
	@Column
	private Date dob;
	@Column
	@CreationTimestamp
    @JsonFormat(timezone = "GMT+08:00")
	private Timestamp created_dt;
	
	@Column
	private String nric;
	@Column
	private String mobile_no;
	@Column
	private String vehicle_no;
	@Column
	private String safe_entry;
	@Column
	private double temp;
	
	
	public String getNric() {
		return nric;
	}
	public void setNric(String nric) {
		this.nric = nric;
	}
	public String getMobile_no() {
		return mobile_no;
	}
	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}
	public String getVehicle_no() {
		return vehicle_no;
	}
	public void setVehicle_no(String vehicle_no) {
		this.vehicle_no = vehicle_no;
	}
	public String getSafe_entry() {
		return safe_entry;
	}
	public void setSafe_entry(String safe_entry) {
		this.safe_entry = safe_entry;
	}
	public double getTemp() {
		return temp;
	}
	public void setTemp(double temp) {
		this.temp = temp;
	}
	public Timestamp getCreated_dt() {
		return created_dt;
	}
	public void setCreated_dt(Timestamp created_dt) {
		this.created_dt = created_dt;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	
	@Override
	public String toString() {
		return "Visitor [id=" + id + ", name=" + name + ", gender=" + gender + ", department=" + department + ", dob="
				+ dob + ", created_dt=" + created_dt + ", nric=" + nric + ", mobile_no=" + mobile_no + ", vehicle_no="
				+ vehicle_no + ", safe_entry=" + safe_entry + ", temp=" + temp + "]";
	}
	
}
