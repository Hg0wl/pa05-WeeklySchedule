package cs3500.pa05.controller;

import cs3500.pa05.model.CompleteStatus;
import cs3500.pa05.model.DayTask;
import cs3500.pa05.model.DaysOfWeek;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import javafx.beans.binding.ListExpression;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

/**
 * A popup dialog for creating a new task from user input
 */
public class CreateTaskPopup extends AbstractPopup<DayTask> {

  @Override
  public void displayPopup(List<DayTask> listTask, Stage stage, List<String> categories) {
    // load the FXML from the FXML file
    this.loader = new FXMLLoader(getClass().getClassLoader()
        .getResource("createTask.fxml"));
    this.loader.setController(this);

    try {
      stage.setScene(this.loader.load());
    } catch (IOException e) {
      throw new RuntimeException("Unable to load Create Task Popup GUI");
    }

    // Set the prompt text for each text field
    this.nameField.setPromptText("*required* Enter a name for the Task");
    this.descriptionField.setPromptText("*optional* Enter a description for the Task");
    this.dayField.setPromptText("*required* Enter a day for the Task to be on");
    this.categoryField.setItems(FXCollections.observableList(categories));


    this.enter.setOnAction(e -> handleEnterButton(listTask));
    this.back.setOnAction(e -> handleBackButton());

    this.show(stage);
  }

  private void handleEnterButton(List<DayTask> listTask ) {
    DayTask task = this.createNew();
    System.out.println("enter button after creation");

    if (Objects.nonNull(task)) {
      listTask.add(task);
      this.hide();
      System.out.println("should be hidden after valid inputs");
    }
  }

  /**
   * Handles when the enter button is pressed
   */
  private void handleBackButton() {
    this.hide();
    System.out.println("hide");
  }


  @Override
  protected DayTask createNew() {
    String name = this.nameField.getCharacters().toString();
    String description = this.descriptionField.getCharacters().toString();
    String category = this.categoryField.getValue();

    try {
      DaysOfWeek day = DaysOfWeek.getDayValue(this.dayField.getCharacters().toString());
      if (name.isEmpty()) {
        throw new IllegalArgumentException("Invalid Inputs");
      } else {
        return new DayTask(name, description, day, CompleteStatus.INCOMPLETE, category);
      }
    } catch (IllegalArgumentException e) {
      this.clearAll();
      return null;

    }
  }

  @Override
  protected void clearAll() {
    this.nameField.clear();
    this.descriptionField.clear();
    this.dayField.clear();
    this.categoryField.setValue("");
  }
}
