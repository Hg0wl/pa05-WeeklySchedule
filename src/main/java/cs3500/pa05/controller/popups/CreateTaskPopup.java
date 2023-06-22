package cs3500.pa05.controller.popups;

import cs3500.pa05.model.CompleteStatus;
import cs3500.pa05.model.DayTask;
import cs3500.pa05.model.DaysOfWeek;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

/**
 * A popup dialog for creating a new task from user input
 */
public class CreateTaskPopup extends AbstractPopup<DayTask> {

  /**
   * Loads the popup FXML file and programmatically sets actions for the buttons and text fields
   *
   * @param listTask of tasks which will be updated by button presses in the popup
   * @param stage the stage object on which this popup should be placed
   */
  @Override
  public void displayPopup(List<DayTask> listTask, Stage stage, List<String> categories) {
    this.categories = categories;

    // load the FXML from the FXML file
    this.loader = new FXMLLoader(getClass().getClassLoader()
        .getResource("createTask.fxml"));
    this.loader.setController(this);

    try {
      stage.setScene(this.loader.load());
    } catch (IOException e) {
      throw new RuntimeException("Unable to load Create Task Popup GUI");
    }
    this.nameField.setOnKeyTyped(e -> this.autoTagDetect());

    // Set the prompt text for each text field
    this.nameField.setPromptText("* Enter a name for the Task");
    this.descriptionField.setPromptText("Enter a description for the Task");
    //this.descriptionField.
    //this.descriptionField.setTextFill(Color.GREEN);
    this.dayField.setPromptText("* Enter a day for the Task to be on");
    this.categoryField.setItems(FXCollections.observableList(categories));


    this.enter.setOnAction(e -> handleEnterButton(listTask));
    this.back.setOnAction(e -> handleBackButton());

    this.show(stage);
  }

  /**
   * Used to create a new DayTask object. Returns null if arguments are invalid.
   *
   * @return either a null or a new DayTask object
   */
  @Override
  protected DayTask createNew() {
    String name = this.nameField.getCharacters().toString();
    String description = this.descriptionField.getCharacters().toString();
    String category = this.categoryField.getValue();

    try {
      DaysOfWeek day = DaysOfWeek.getDayValue(this.dayField.getCharacters().toString());
      if (name.isEmpty() || Objects.isNull(category)) {
        throw new IllegalArgumentException("Invalid Inputs");
      } else {
        return new DayTask(name, description, day, CompleteStatus.INCOMPLETE, category);
      }
    } catch (IllegalArgumentException e) {
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
    this.dayField.clear();
    this.categoryField.setValue("");
  }

}
