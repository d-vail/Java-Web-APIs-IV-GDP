package com.lambdaschool.gdp;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Log creates a message for use in log queue.
 */
public class Log implements Serializable {
  private final String TEXT;
  private final String FDATE;

  /**
   * Constructor
   *
   * @param text  Message content
   */
  public Log(String text) {
    Date date = new Date();
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");

    this.TEXT = text;
    this.FDATE = dateFormat.format(date);
  }
}
