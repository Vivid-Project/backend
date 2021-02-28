package com.vivid.backend.filters;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

public class DreamFilters {
  public static final FilterProvider DREAM_DEFAULT_FILTER = dreamDefaultFilter();
  public static final FilterProvider DREAM_INCLUDE_USER_FILTER = dreamIncludeUserFilter();

  private DreamFilters() {
  }

  private static FilterProvider dreamIncludeUserFilter() {
    SimpleBeanPropertyFilter simpleBeanPropertyFilterDream = SimpleBeanPropertyFilter.serializeAllExcept("tones");
    SimpleBeanPropertyFilter simpleBeanPropertyFilterUser = SimpleBeanPropertyFilter.serializeAllExcept("token",
        "dreams", "themes");

    return new SimpleFilterProvider().addFilter("dreamFilter", simpleBeanPropertyFilterDream).addFilter("userFilter",
        simpleBeanPropertyFilterUser);
  }

  private static FilterProvider dreamDefaultFilter() {
    SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter.serializeAllExcept("user", "tones");

    return new SimpleFilterProvider().addFilter("dreamFilter", simpleBeanPropertyFilter);

  }
}
