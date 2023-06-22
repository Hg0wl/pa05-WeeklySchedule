package cs3500.pa05;

import cs3500.pa05.controller.ControlJournal;
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

    try {
      Application.launch(ControlJournal.class, args);
    } catch (Exception e) {
      System.err.println("An error was encountered while running the application.");
    }


  }
}
