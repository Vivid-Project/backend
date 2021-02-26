package com.vivid.backend.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;

import com.vivid.backend.exceptions.MicroserviceRequestFailedException;

public class ToneAnalyzerService {

  public static String analyzeTonesHttpUrlConnection(String description) {
    try {
      URL url = new URL("https://tone-analyzer-microservice.herokuapp.com/tones/");
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("POST");

      connection.setConnectTimeout(0);

      connection.setDoOutput(true);

      OutputStream out = connection.getOutputStream();

      byte[] input = description.getBytes(StandardCharsets.UTF_8);

      out.write(input, 0, input.length);

      try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
        StringBuilder response = new StringBuilder();
        String responseLine = null;
        while ((responseLine = reader.readLine()) != null) {
          response.append(responseLine.trim());
        }
        return response.toString();
      }

    } catch (Exception e) {
      throw new MicroserviceRequestFailedException(e.toString());
    }
  }

  public static String analyzeTonesHttpClient(String description) {
    HttpClient client = HttpClient.newHttpClient();

    HttpRequest request = HttpRequest.newBuilder(URI.create("https://tone-analyzer-microservice.herokuapp.com/tones/"))
        .header("accept", "application/json").POST(HttpRequest.BodyPublishers.ofString(description)).build();

    HttpResponse response;
    try {
      response = client.send(request, BodyHandlers.ofString());
      System.out.println(response);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      throw new MicroserviceRequestFailedException(e.toString());
    }
    return null;
  }

}
