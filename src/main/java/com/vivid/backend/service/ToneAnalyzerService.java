package com.vivid.backend.service;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.vivid.backend.exceptions.MicroserviceRequestFailedException;

public class ToneAnalyzerService {

	public static void analyzeTones(String description) {
    try {
      URL url = new URL("https://tone-analyzer-microservice.herokuapp.com/");
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    } catch (MalformedURLException e) {
      throw new MicroserviceRequestFailedException();
    }
	}
  
}
