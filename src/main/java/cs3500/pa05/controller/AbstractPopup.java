package cs3500.pa05.controller;

import java.util.List;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * An abstract popup class with similar text fields and buttons between subclasses
 */
public abstract class AbstractPopup<T> extends Popup {

  protected FXMLLoader loader;

  @FXML
  protected TextField nameField;
  @FXML
  protected TextField descriptionField;
  @FXML
  protected TextField dayField;
  @FXML
  protected Button back;
  @FXML
  protected Button enter;
  @FXML
  protected TextField categoryField;


  /**
   * Initializes the FXMLLoader to the correct file path
   *
   * @param list of T which will be updated by button presses in the popup
   */
  abstract public void displayPopup(List<T> list, Stage stage);

  /**
   * Attempts to create an object T out of the information provided in the fields.
   *
   * @return a new object T
   */
  abstract protected T createNew();

  /**
   * Clears all the fields
   */
  abstract protected void clearAll();


}
