package cs3500.pa05.controller;

import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controls the validation and verification of entering the password
 */
public class PasswordController {

  private final String password;
  private Stage stage;

  // Scene builder created objects
  @FXML
  private Button enter;
  @FXML
  private TextField passTextField;

  private final List<String> listPasswords;

  /**
   * Creates a new password controller with the actual password of the file
   *
   * @param password the string representation of the actual password
   * @param returnPasswords the list of passwords to which the password will be appended if needed
   */
  public PasswordController(String password, List<String> returnPasswords) {
    this.password = password;
    this.listPasswords = returnPasswords;
  }

  /**
   * Initializes the action of the submit button
   */
  public void run() {
    this.initEvents();

  }

  private void initEvents() {
    this.enter.setOnAction(e -> handleSubmit());
  }

  private void handleSubmit() {
    String passAttempt = this.passTextField.getCharacters().toString();

    if (this.password.equals("")) {
      this.listPasswords.add(passAttempt);
      stage.hide();
    }

    if (!(passAttempt.equals(this.password))) {
      this.passTextField.clear();
    } else {
      stage.hide();
    }
  }

  /**
   * Sets this controllers stage to the given stage object
   *
   * @param stage object on which this controller will display the view
   */
  public void setStage(Stage stage) {
    this.stage = stage;
  }


}
