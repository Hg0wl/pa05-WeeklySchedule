package cs3500.pa05.controller.popups;

import java.io.IOException;
import java.util.List;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * class to create a note popup
 */
public class CreateNotePopup extends Popup {

  FXMLLoader loader;

  @FXML
  private Button back;
  @FXML
  private Button enter;
  @FXML
  private TextArea notesText;

  private List<String> note;

  /**
   * displays the create note pop up
   *
   * @param note a list of strings, look only at index 0
   * @param stage the stage on which this popup will be displayed
   */
  public void displayPopup(List<String> note, Stage stage) {
    this.note = note;
    loader = new FXMLLoader(getClass().getClassLoader().getResource("createNotes.fxml"));
    this.loader.setController(this);

    try {
      stage.setScene(this.loader.load());
    } catch (IOException e) {
      throw new RuntimeException(e.getMessage());
    }

    if (!note.isEmpty()) {
      this.notesText.clear();
      this.notesText.appendText(note.get(0));
    }

    this.back.setOnAction(e -> handleBackButton());
    this.enter.setOnAction(e -> handleEnterButton());

    this.show(stage);

  }

  /**
   * handles the back button
   */
  private void handleBackButton() {
    this.hide();
  }


  /**
   * handles when the enter button is pressed. hides and saves whatever was written
   */
  private void handleEnterButton() {
    String str = this.notesText.getText();
    this.note.clear();
    this.note.add(str);
    this.hide();

  }

}
