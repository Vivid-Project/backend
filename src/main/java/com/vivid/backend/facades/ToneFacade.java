package com.vivid.backend.facades;

import com.vivid.backend.model.Dream;
import com.vivid.backend.service.ToneAnalyzerService;

import org.springframework.boot.json.BasicJsonParser;

public class ToneFacade {
  private static final BasicJsonParser basicJsonParser = new BasicJsonParser();

  public static void getTones(Dream dream) {
    String data = ToneAnalyzerService.analyzeTonesHttpClient(dream.getDescription());
    System.out.println(data);
  }
}
