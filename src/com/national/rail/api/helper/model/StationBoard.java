package com.national.rail.api.helper.model;

import java.util.ArrayList;
import java.util.List;

public class StationBoard {
	private String generatedAt;
	private String locationName;
	private String crs;
	private String platformAvailable;
	private List<TrainService> trainServices;

	public String getGeneratedAt() {
		return generatedAt;
	}

	public void setGeneratedAt(String generatedAt) {
		this.generatedAt = generatedAt;
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

	public String getPlatformAvailable() {
		return platformAvailable;
	}

	public void setPlatformAvailable(String platformAvailable) {
		this.platformAvailable = platformAvailable;
	}

	public List<TrainService> getTrainServices() {
		return trainServices;
	}

	public void setTrainServices(List<TrainService> trainServices) {
		this.trainServices = trainServices;
	}

	public void addTrainService(TrainService service) {
		if (trainServices == null)
			trainServices = new ArrayList<TrainService>();

		trainServices.add(service);
	}
}