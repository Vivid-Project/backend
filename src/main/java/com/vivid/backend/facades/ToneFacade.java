package com.vivid.backend.facades;

import java.util.List;
import java.util.Map;

import com.vivid.backend.helpers.ToneBuilderHelper;
import com.vivid.backend.model.Dream;
import com.vivid.backend.model.Tone;
import com.vivid.backend.repository.ToneRepository;
import com.vivid.backend.service.ToneAnalyzerService;

import org.springframework.boot.json.BasicJsonParser;
import org.springframework.boot.json.JsonParseException;
public class ToneFacade {
  private static final BasicJsonParser basicJsonParser = new BasicJsonParser();

  private ToneFacade() {

  }

  public static void getTones(Dream dream, ToneRepository toneRepository) {
    String data = ToneAnalyzerService.analyzeTonesHttpClient(dream.getDescription());
    try {

      Map<String, Object> objectData = basicJsonParser.parseMap(data);
      List<Tone> tones = ToneBuilderHelper.buildTones(objectData, dream);

      toneRepository.saveAll(tones);

    } catch (JsonParseException e) {
    }

  }
}
