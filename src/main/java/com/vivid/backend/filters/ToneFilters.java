package com.vivid.backend.filters;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

public class ToneFilters {

  public static final FilterProvider TONE_DEFAULT_FILTER = toneDefaultFilter();

  private ToneFilters() {
  }

  private static FilterProvider toneDefaultFilter() {
    SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter.serializeAllExcept("dreams");

    return new SimpleFilterProvider().addFilter("toneFilter", simpleBeanPropertyFilter);
  }

}
