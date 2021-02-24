package com.vivid.backend.filters;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

public class UserFilters {
  public static final FilterProvider USER_DEFAULT_FILTER = userDefaultFilter();

  private UserFilters() {
  }

  private static FilterProvider userDefaultFilter() {
    SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter.serializeAllExcept("token", "dreams",
        "themes");

    return new SimpleFilterProvider().addFilter("userFilter", simpleBeanPropertyFilter);
  }
}
