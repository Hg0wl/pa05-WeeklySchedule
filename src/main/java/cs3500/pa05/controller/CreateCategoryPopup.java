package cs3500.pa05.controller;

import java.io.IOException;
import java.util.List;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * Represents a popup to create a new category
 */
public class CreateCategoryPopup extends Popup {
  FXMLLoader loader;

  @FXML
  private Button back;
  @FXML
  private Button enter;
  @FXML
  private TextField catField;

  private List<String> categories;

  /**
   * Displays the popup for creating a new category
   *
   * @param cats the list of categories given to this popup
   * @param stage the stage on which this popup will be displayed
   */
  public void displayPopup(List<String> cats, Stage stage) {
    loader = new FXMLLoader(getClass().getClassLoader().getResource("createCategory.fxml"));
    this.loader.setController(this);

    try {
      stage.setScene(this.loader.load());
    } catch (IOException e) {
      throw new RuntimeException("Unable to load the create category GUI");
    }

    this.catField.setPromptText("Enter a single word to be the new Category");
    this.back.setOnAction(e -> handleBackButton());
    this.enter.setOnAction(e -> handleEnterButton(cats));

    this.show(stage);
  }

  /**
   * handles the back button, brings you back to your previous page
   */
  private void handleBackButton() {
    this.hide();
  }

  /**
   * handles the enter button, checks if the input is valid and creates a category if it is valid
   *
   * @param cats the category being created
   */
  private void handleEnterButton(List<String> cats) {
    String newCat = this.catField.getCharacters().toString();
    if (newCat.contains(" ") || newCat.isEmpty()) {
      this.catField.clear();
    } else {
      cats.add(newCat);
      this.hide();
    }
  }

}
