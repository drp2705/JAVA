package com.smilecare.model;

import java.io.Serializable;

public class Appointment implements Serializable{
	private String patientName;
	private String date;
	private ServiceType service;
	
	public Appointment (String patientName, String date, ServiceType service) {
		this.patientName=patientName;
		this.date=date;
		this.service=service;
	}

	public String getPatientName() {
		return patientName;
	}

	public String getDate() {
		return date;
	}

	public ServiceType getService() {
		return service;
	}
	
}
