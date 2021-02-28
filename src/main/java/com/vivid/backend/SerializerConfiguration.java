package com.vivid.backend;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.vivid.backend.mixins.DreamMixIn;
import com.vivid.backend.model.Dream;
import com.vivid.backend.model.Tone;
import com.vivid.backend.serializers.ToneSerializer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SerializerConfiguration {

  @Bean
  public ObjectMapper objectMapper() {
    ObjectMapper mapper = new ObjectMapper();
    // mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

    // CollectionType toneListType =
    // mapper.getTypeFactory().constructCollectionType(List.class, Tone.class);

    // SimpleModule module = new SimpleModule();
    // module.addSerializer(new ToneSerializer(toneListType));
    // mapper.registerModule(module);

    mapper.addMixInAnnotations(Dream.class, DreamMixIn.class);

    return mapper;

  }

}
