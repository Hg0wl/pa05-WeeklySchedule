package cs3500.pa05.controller;

import cs3500.pa05.model.DayEvent;
import cs3500.pa05.model.DaysOfWeek;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * A popup object for creating new create event windows
 */
public class CreateEventPopup extends AbstractPopup<DayEvent> {

  @FXML
  private TextField startTimeField;
  @FXML
  private TextField durationMinField;

  private DayEvent event;


  /**
   * Loads the popup FXML file and programmatically sets actions for the buttons and text fields
   *
   * @param listEvent of T which will be updated by button presses in the popup
   * @param stage the stage object on which this popup should be placed
   */
  public void displayPopup(List<DayEvent> listEvent, Stage stage, List<String> categories) {
    // loads the FXML file for the event creation popup
    this.loader = new FXMLLoader(getClass().getClassLoader()
        .getResource("createEvent.fxml"));
    this.loader.setController(this);
    try {
      stage.setScene(this.loader.load());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    // sets the prompt text for each of the text fields
    this.nameField.setPromptText("*required* Enter a name for the Event");
    this.descriptionField.setPromptText("*optional* Enter a description for the Event");
    this.dayField.setPromptText("*required* Enter a day for the Event to be on");
    this.startTimeField
        .setPromptText("*required* Enter a time for "
            + "the Event to start in the form HH:MM (24 hr time)");
    this.durationMinField.setPromptText("*required* Enter the duration of the Event in mins");
    this.categoryField.setPromptText("*optional* Enter a category for the Event");

    this.enter.setOnAction(e -> handleEnterButton(listEvent));
    this.back.setOnAction(e -> handleBackButton());

    // shows the popup on the stage
    this.show(stage);
  }

  /**
   * Handles when the enter button is pressed
   *
   * @param listEvent takes a list of events
   */
  private void handleEnterButton(List<DayEvent> listEvent) {
    DayEvent newEvent = this.createNew();

    if (Objects.nonNull(newEvent)) {
      listEvent.add(newEvent);
      this.hide();
    }
  }

  /**
   * Handles when the enter button is pressed
   */
  private void handleBackButton() {
    this.hide();
  }

  /**
   * Used to create a new DayEvent object. Returns null if arguments are invalid.
   *
   * @return either a null or a new DayEvent object
   */
  @Override
  protected DayEvent createNew() {
    String name = this.nameField.getCharacters().toString();
    String description = this.descriptionField.getCharacters().toString();
    String startTime = this.startTimeField.getCharacters().toString();
    String duration = this.durationMinField.getCharacters().toString();
    String category = this.categoryField.getCharacters().toString();

    // if any validation test returns false,
    try {
      DaysOfWeek day = DaysOfWeek.getDayValue(this.dayField.getCharacters().toString());
      if (name.isEmpty() || !checkStartTime(startTime) || !checkDuration(startTime, duration)) {
        throw new IllegalArgumentException("Invalid Inputs");
      } else {
        return new DayEvent(name, description, day, startTime, duration, category);
      }
    } catch (IllegalArgumentException e) {
      //System.out.println("invalid - clearing all");
      this.clearAll();
      return null;
    }
  }

  /**
   * clears all the text field
   */
  @Override
  protected void clearAll() {
    this.nameField.clear();
    this.descriptionField.clear();
    this.categoryField.clear();
    this.dayField.clear();
    this.startTimeField.clear();
    this.durationMinField.clear();
  }

  /**
   * checks if a start time is in the valid hh:mm 24-hour clock format
   *
   * @param time input time
   * @return boolean true or false depending on if the time is valid
   */
  private boolean checkStartTime(String time) {
    int hours;
    int mins;
    if (time.length() == 5) {
      if (!time.substring(2,3).equals(":")) {
        return false;
      }
      int index = time.indexOf(":");

      try {
        hours = Integer.parseInt(time.substring(0, index));
        mins = Integer.parseInt(time.substring(index + 1));
      } catch (Exception exc) {
        return false;
      }

      return hours < 25 && hours >= 0 && mins < 60 && mins >= 0;
    }
    return false;
  }

  /**
   * checks if the duration inputted is valid
   *
   * @param start start time value in hh:mm format
   * @param duration inputted duration that is being checked
   * @return boolean true if valid
   */
  private boolean checkDuration(String start, String duration) {
    int dur;
    int hours;
    int mins;

    try {
      int index = start.indexOf(":");
      dur = Integer.parseInt(duration);
      hours = Integer.parseInt(start.substring(0, index));
      mins = Integer.parseInt(start.substring(index + 1));
    } catch (Exception exc) {
      return false;
    }

    // 1440 is number of minutes in the day
    int totalMinLeft = 1440 - (hours * 60 + mins);

    return dur <= totalMinLeft;
  }

}
