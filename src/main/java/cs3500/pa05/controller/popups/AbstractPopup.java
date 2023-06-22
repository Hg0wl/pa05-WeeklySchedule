package cs3500.pa05.controller.popups;

import java.util.List;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * An abstract popup class with similar text fields and buttons between subclasses
 */
public abstract class AbstractPopup<T> extends Popup {

  /**
   * loader for fxml file
   */
  protected FXMLLoader loader;

  /**
   * text field that gets the name from the popup
   */
  @FXML
  protected TextField nameField;

  /**
   * text field that gets the description from the popup
   */
  @FXML
  protected TextField descriptionField;

  /**
   * text field that gets the day from the popup
   */
  @FXML
  protected TextField dayField;


  /**
   * button to go back to week view
   */
  @FXML
  protected Button back;

  /**
   * button to submit the popups info
   */
  @FXML
  protected Button enter;

  /**
   * combo box containing the categories available
   */
  @FXML
  protected ComboBox<String> categoryField;

  /**
   * list of categories
   */
  protected List<String> categories;


  /**
   * Initializes the FXMLLoader to the correct file path
   *
   * @param list of T which will be updated by button presses in the popup
   * @param stage stage that is going to be displayed
   * @param cats list of categories
   */
  public abstract void displayPopup(List<T> list, Stage stage, List<String> cats);

  /**
   * Attempts to create an object T out of the information provided in the fields.
   *
   * @return a new object T
   */
  abstract protected T createNew();

  /**
   * Clears all the fields
   */
  protected abstract void clearAll();

  protected void autoTagDetect() {
    String input = this.nameField.getText();
    if (input.startsWith("#")) {
      String[] content = input.split(" ");
      String category = content[0].substring(1);
      int lengthOfFirstWord = category.length() + 1;
      if (this.categories.contains(category) && !category.equals("")) {
        this.categoryField.setValue(category);
        this.nameField.setText(input.substring(lengthOfFirstWord));
      }
    }
  }
}
