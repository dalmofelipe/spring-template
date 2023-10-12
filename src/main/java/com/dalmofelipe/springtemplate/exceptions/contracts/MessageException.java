package com.dalmofelipe.springtemplate.exceptions.contracts;

import java.util.Map;

public interface MessageException {
    String getExceptionKey();
    Map<String, Object> getMapDetails();
}
