package cs3500.pa05.controller;

import cs3500.pa05.model.CompleteStatus;
import cs3500.pa05.model.DayTask;
import java.util.List;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 */
public class CreateTaskPopup extends AbstractPopup<DayTask> {

  @FXML
  private TextField completeStatusField;


  @Override
  public void displayPopup(List<DayTask> listTask, Stage stage) {
    this.loader = new FXMLLoader(getClass().getClassLoader()
        .getResource("createTask.fxml"));
    this.loader.setController(this);
  }

  @Override
  protected DayTask createNew() {
    //TODO
    return null;
  }

  @Override
  protected void clearAll() {
    //TODO
    return;
  }
}
