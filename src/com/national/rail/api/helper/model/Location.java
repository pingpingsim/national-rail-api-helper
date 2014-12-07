package com.national.rail.api.helper.model;

public class Location {
	private String locationName;
	private String crs;
	private String via;
	private String futureChangeTo;

	public Location() {
	}

	public Location(String locationName, String crs, String via,
			String futureChangeTo) {
		this.locationName = locationName;
		this.crs = crs;
		this.via = via;
		this.futureChangeTo = futureChangeTo;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getCrs() {
		return crs;
	}

	public void setCrs(String crs) {
		this.crs = crs;
	}

	public String getVia() {
		return via;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public String getFutureChangeTo() {
		return futureChangeTo;
	}

	public void setFutureChangeTo(String futureChangeTo) {
		this.futureChangeTo = futureChangeTo;
	}
}
