package com.vivid.backend.helpers;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.vivid.backend.model.Dream;
import com.vivid.backend.model.Tone;

public class ToneBuilderHelper {

  public static List<Tone> buildTones(Map<String, Object> toneData, Dream dream) {
    List<Tone> tones = ((Map<String, Object>) toneData.get("tone_strength")).entrySet().stream().map(entry -> {
      Tone tone = new Tone(entry.getKey(), ((Long) entry.getValue()), dream);
      return tone;
    }).collect(Collectors.toList());

    return tones;

  }

}
