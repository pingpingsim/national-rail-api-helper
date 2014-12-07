package com.national.rail.api.helper.util;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.kxml2.kdom.Element;

public class SoapHelper {
	private static final String HEADER_NAMESPACE = "http://thalesgroup.com/RTTI/2010-11-01/ldb/commontypes";

	public static Element[] createSoapHeader(String token) {
		Element[] headers = new Element[1];
		Element AuthHeader = new Element();
		AuthHeader.setNamespace(HEADER_NAMESPACE);
		AuthHeader.setName("AccessToken");

		Element element = new Element();
		element.setNamespace(HEADER_NAMESPACE);
		element.setName("TokenValue");
		element.addChild(Element.TEXT, token);
		AuthHeader.addChild(Element.ELEMENT, element);
		headers[0] = AuthHeader;
		return headers;
	}

	public static SoapSerializationEnvelope getSoapSerializationEnvelope(
			SoapObject request) {
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.dotNet = true;
		envelope.setOutputSoapObject(request);

		return envelope;
	}

	public static HttpTransportSE getHttpTransportSE(String mainRequestUrl) {
		HttpTransportSE ht = new HttpTransportSE(mainRequestUrl);
		ht.debug = true;
		ht.setXmlVersionTag("<!--?xml version=\"1.0\" encoding= \"UTF-8\" ?-->");

		return ht;
	}
}
