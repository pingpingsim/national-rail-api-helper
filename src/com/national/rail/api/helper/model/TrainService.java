package com.national.rail.api.helper.model;

public class TrainService {
	private Location origin;
	private Location destination;
	private String std;
	private String etd;
	private String platform;
	private String operator;
	private String operatorCode;
	private String serviceID;

	public TrainService() {
	}

	public TrainService(Location origin, Location destination, String std,
			String etd, String platform, String operator, String operatorCode,
			String serviceID) {
		this.origin = origin;
		this.destination = destination;
		this.std = std;
		this.etd = etd;
		this.platform = platform;
		this.operator = operator;
		this.operatorCode = operatorCode;
		this.serviceID = serviceID;
	}

	public Location getOrigin() {
		return origin;
	}

	public void setOrigin(Location origin) {
		this.origin = origin;
	}

	public Location getDestination() {
		return destination;
	}

	public void setDestination(Location destination) {
		this.destination = destination;
	}

	public String getStd() {
		return std;
	}

	public void setStd(String std) {
		this.std = std;
	}

	public String getEtd() {
		return etd;
	}

	public void setEtd(String etd) {
		this.etd = etd;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getOperatorCode() {
		return operatorCode;
	}

	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}

	public String getServiceID() {
		return serviceID;
	}

	public void setServiceID(String serviceID) {
		this.serviceID = serviceID;
	}
}
