package com.national.rail.api.helper.request;

import java.io.IOException;
import java.net.SocketTimeoutException;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.util.Log;

import com.national.rail.api.helper.model.Location;
import com.national.rail.api.helper.model.StationBoard;
import com.national.rail.api.helper.model.TrainService;
import com.national.rail.api.helper.util.SoapHelper;

public class StationBoardRequest {
	private static final String MAIN_REQUEST_URL = "https://lite.realtime.nationalrail.co.uk/OpenLDBWS/ldb6.asmx";
	private static final String SOAP_ACTION = "http://thalesgroup.com/RTTI/2012-01-13/ldb/GetDepartureBoard";
	private static final String NAMESPACE = "http://thalesgroup.com/RTTI/2014-02-20/ldb/";

	public StationBoard getResults(String crs, String token,
			STATION_BOARD_REQUEST stationBoardRequest) {

		SoapObject request = new SoapObject(NAMESPACE,
				stationBoardRequest.getMethodName());
		request.addProperty("crs", crs);
		request.addProperty("filterType", "to");
		request.addProperty("timeOffset", "0");
		request.addProperty("timeWindow", "120");

		SoapSerializationEnvelope envelope = SoapHelper
				.getSoapSerializationEnvelope(request);
		envelope.headerOut = SoapHelper.createSoapHeader(token);
		HttpTransportSE ht = SoapHelper.getHttpTransportSE(MAIN_REQUEST_URL);

		try {
			ht.call(SOAP_ACTION, envelope);
			Log.e("Soap error, request ", ht.requestDump);
			Log.e("Soap error, response ", ht.responseDump);
			SoapObject response = (SoapObject) envelope.getResponse();
			return populateTrainService(response);

		} catch (SocketTimeoutException t) {
			t.printStackTrace();
		} catch (IOException i) {
			i.printStackTrace();
		} catch (Exception q) {
			q.printStackTrace();
		}
		return null;
	}

	private StationBoard populateTrainService(SoapObject response) {
		StationBoard stationBoard = new StationBoard();
		Location originLocation = null;
		Location destLocation = null;

		String generatedAt = (response.hasProperty("generatedAt") ? response
				.getPropertyAsString("generatedAt") : null);
		String locationName = (response.hasProperty("locationName") ? response
				.getPropertyAsString("locationName") : null);
		String crs = (response.hasProperty("crs") ? response
				.getPropertyAsString("crs") : null);
		String platformAvailable = (response.hasProperty("platformAvailable") ? response
				.getPropertyAsString("platformAvailable") : null);
		SoapObject trainServices = (SoapObject) response
				.getProperty("trainServices");

		// populate main details for station board model
		stationBoard.setGeneratedAt(generatedAt);
		stationBoard.setLocationName(locationName);
		stationBoard.setCrs(crs);
		stationBoard.setPlatformAvailable(platformAvailable);

		for (int i = 0; i < trainServices.getPropertyCount(); i++) {
			Object property = trainServices.getProperty(i);
			if (property instanceof SoapObject) {
				SoapObject trainService = (SoapObject) property;

				String std = (trainService.hasProperty("std") ? trainService
						.getPropertyAsString("std") : null);
				String etd = (trainService.hasProperty("etd") ? trainService
						.getPropertyAsString("etd") : null);
				String platform = (trainService.hasProperty("platform") ? trainService
						.getPropertyAsString("platform") : null);
				String operator = (trainService.hasProperty("operator") ? trainService
						.getPropertyAsString("operator") : null);
				String operatorCode = (trainService.hasProperty("operatorCode") ? trainService
						.getPropertyAsString("operatorCode") : null);
				String serviceID = (trainService.hasProperty("serviceID") ? trainService
						.getPropertyAsString("serviceID") : null);

				SoapObject origin = (trainService.hasProperty("origin") ? (SoapObject) trainService
						.getProperty("origin") : null);
				if (origin != null) {
					SoapObject soapOriginLocation = (SoapObject) origin
							.getProperty("location");
					String originLocationName = (soapOriginLocation
							.hasProperty("locationName") ? soapOriginLocation
							.getPropertyAsString("locationName") : null);
					String originCrs = (soapOriginLocation.hasProperty("crs") ? soapOriginLocation
							.getPropertyAsString("crs") : null);
					String originVia = (soapOriginLocation.hasProperty("via") ? soapOriginLocation
							.getPropertyAsString("via") : null);
					String originFutureChangeTo = (soapOriginLocation
							.hasProperty("futureChangeTo") ? soapOriginLocation
							.getPropertyAsString("futureChangeTo") : null);

					originLocation = new Location(originLocationName,
							originCrs, originVia, originFutureChangeTo);
				}

				SoapObject destination = (trainService
						.hasProperty("destination") ? (SoapObject) trainService
						.getProperty("destination") : null);
				if (destination != null) {
					SoapObject soapDestLocation = (SoapObject) destination
							.getProperty("location");
					String destLocationName = (soapDestLocation
							.hasProperty("locationName") ? soapDestLocation
							.getPropertyAsString("locationName") : null);
					String destCrs = (soapDestLocation.hasProperty("crs") ? soapDestLocation
							.getPropertyAsString("crs") : null);
					String destVia = (soapDestLocation.hasProperty("via") ? soapDestLocation
							.getPropertyAsString("via") : null);
					String destFutureChangeTo = (soapDestLocation
							.hasProperty("futureChangeTo") ? soapDestLocation
							.getPropertyAsString("futureChangeTo") : null);

					destLocation = new Location(destLocationName, destCrs,
							destVia, destFutureChangeTo);
				}
				// add train service item to station board model
				stationBoard.addTrainService(new TrainService(originLocation,
						destLocation, std, etd, platform, operator,
						operatorCode, serviceID));
			}
		}
		return stationBoard;
	}

	public enum STATION_BOARD_REQUEST {
		DEPARTURE("GetDepartureBoardRequest"), ARRIVAL("GetArrivalBoardRequest"), ARRIVAL_DEPARTURE(
				"GetArrivalDepartureBoardRequest");

		private String methodName;

		/**
		 * @return the status
		 */
		public String getMethodName() {
			return methodName;
		}

		/**
		 * @param status
		 *            the status to set
		 */
		public void setMethodName(String methodName) {
			this.methodName = methodName;
		}

		private STATION_BOARD_REQUEST(String methodName) {
			this.methodName = methodName;
		}
	}
}
