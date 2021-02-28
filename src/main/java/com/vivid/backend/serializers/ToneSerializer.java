package com.vivid.backend.serializers;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.vivid.backend.model.Tone;

public class ToneSerializer extends StdSerializer<Set<Tone>> {

  protected ToneSerializer() {
    super(new ObjectMapper().getTypeFactory().constructCollectionType(List.class, Tone.class));
  }

  protected ToneSerializer(Class<Set<Tone>> t) {
    super(t);
    // TODO Auto-generated constructor stub
  }

  public ToneSerializer(CollectionType toneListType) {
    super(toneListType);
  }

/**
   *
   */
  private static final long serialVersionUID = 1L;

  // public static MappingJacksonValue serializeToneList(Set<Tone> tones) {
  // String output = "{\n" + "\"tone_strength\": {\n";

  // for (Tone tone : tones) {
  // output += "\"" + tone.getTone() + "\": " + tone.getMagnitude() + ",\n";
  // }

  // output += "},\n" + "\"unique_tones\": \"";

  // for (Tone tone : tones) {
  // output += tone.getTone();
  // }

  // output += "\"\n}";

  // return new MappingJacksonValue(output);

  // }

  @Override
  public void serialize(Set<Tone> tones, JsonGenerator jgen, SerializerProvider provider) throws IOException {

    jgen.writeStartObject();

    jgen.writeObjectFieldStart("tone_strength");

    ((Set<Tone>) tones).forEach(tone -> {

      try {
        jgen.writeNumberField(tone.getTone(), tone.getMagnitude());
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

    });

    jgen.writeEndObject();

    List<String> toneValues = tones.stream().map(tone -> tone.getTone()).collect(Collectors.toList());

    String toneString = String.join(", ", toneValues);

    jgen.writeStringField("unique_tones", toneString);

    jgen.writeEndObject();

  }

}
