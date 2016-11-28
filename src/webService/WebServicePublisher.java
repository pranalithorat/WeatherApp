package webService;

import javax.xml.ws.Endpoint;

public class WebServicePublisher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		   Endpoint.publish("http://localhost:9999/ws/weather", new WebServiceImpl());
	}

}
