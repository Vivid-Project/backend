package com.vivid.backend.facades;

import com.vivid.backend.model.Dream;
import com.vivid.backend.service.ToneAnalyzerService;

public class ToneFacade {
  public static void getTones(Dream dream) {
    ToneAnalyzerService.analyzeTones(dream.getDescription());
  }
}
