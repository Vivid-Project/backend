package com.vivid.backend.mixins;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vivid.backend.model.Tone;
import com.vivid.backend.serializers.ToneSerializer;

public interface DreamMixIn {

  @JsonSerialize(using = ToneSerializer.class)
  List<Tone> getTones();

}
