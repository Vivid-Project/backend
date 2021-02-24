package com.vivid.backend.filters;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

public class ThemeFilters {
  public static final FilterProvider THEME_DEFAULT_FILTER = themeDefaultFilter();

  private ThemeFilters() {
    
  }

  private static FilterProvider themeDefaultFilter() {
    SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter.serializeAllExcept("user", "dreams");

    return new SimpleFilterProvider().addFilter("themeFilter", simpleBeanPropertyFilter);
  }
}
