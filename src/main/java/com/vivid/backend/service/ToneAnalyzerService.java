package com.vivid.backend.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import com.vivid.backend.exceptions.MicroserviceRequestFailedException;


public class ToneAnalyzerService {

	public static String analyzeTones(String description) {
    try {
      URL url = new URL("https://tone-analyzer-microservice.herokuapp.com/tones");
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("POST");

      connection.setDoOutput(true);

      OutputStream out = connection.getOutputStream();

      byte[] input = description.getBytes(StandardCharsets.UTF_8);

      out.write(input, 0, input.length);

      try(BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))){
        StringBuilder response = new StringBuilder();
        String responseLine = null;
        while ((responseLine = reader.readLine()) != null){
          response.append(responseLine.trim());
        }
        return response.toString();
      }

    } catch (MalformedURLException e) {
      throw new MicroserviceRequestFailedException(e.getMessage());
    } catch (IOException e) {
      throw new MicroserviceRequestFailedException(e.getMessage());
    }
	}
  
}
