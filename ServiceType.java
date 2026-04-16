package com.smilecare.model;

public enum ServiceType {
	CLEANING("Cleaning", 150.00),
	FILLING("Cavity Filling", 250.00),
	ROOT_CANAL("Root Canal", 850.00);
	
	private final String name;
	private final double baseCost;
	
	private ServiceType(String name, double baseCost) {
		// TODO Auto-generated constructor stub
		this.name=name;
		this.baseCost=baseCost;
	}

	public String getName() {
		return name;
	}

	public double getBaseCost() {
		return baseCost;
	}
	
	@Override
	public String toString() { return name + " ($" + baseCost + ")"; }
	
}
