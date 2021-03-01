package com.vivid.backend.filters;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

public class UserFilters {
  public static final FilterProvider USER_DEFAULT_FILTER = userDefaultFilter();
  public static final FilterProvider USER_AUTHORIZATION_FILTER = userAuthorizationFilter();

  private UserFilters() {
  }

  private static FilterProvider userAuthorizationFilter() {
    SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter.serializeAllExcept("dreams", "themes",
        "passwordDigest");

    return new SimpleFilterProvider().addFilter("userFilter", simpleBeanPropertyFilter);
  }

  private static FilterProvider userDefaultFilter() {
    SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter.serializeAllExcept("token", "dreams",
        "themes", "passwordDigest");

    return new SimpleFilterProvider().addFilter("userFilter", simpleBeanPropertyFilter);
  }
}
