package cs3500.pa05;

import cs3500.pa05.controller.ControlJournal;
import cs3500.pa05.controller.EventLabel;
import cs3500.pa05.model.DayEvent;
import cs3500.pa05.model.DaysOfWeek;
import javafx.application.Application;

/**
 * Starting class of the project
 */
public class Driver {
  /**   
   * Entry point for the gradle project
   *
   * @param args command line arguments for the program
   */
  public static void main(String[] args) {


    Application.launch(ControlJournal.class, args);

  }
}
