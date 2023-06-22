package cs3500.pa05.view;

import cs3500.pa05.controller.ControlScene;
import cs3500.pa05.controller.OpenController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

/**
 * The GUI view for opening a .bujo view or creating a new one
 */
public class ViewOpenFile implements ViewScene {

  private final FXMLLoader loader;

  /**
   * Creates a new ViewWeekImplementation
   *
   * @param controller which is the controller that controls the week views
   */
  public ViewOpenFile(ControlScene controller) {
    this.loader = new FXMLLoader();

    this.loader.setLocation(getClass().getClassLoader().getResource("openFile.fxml"));
    this.loader.setController(controller);
  }

  /**
   * Returns the scene created by the fxml file for opening a file or creating a new week
   *
   * @return Scene that holds the GUI for opening or creating a .bujo file
   * @throws IllegalStateException if the loader is undable to load the FXML file
   */
  public Scene load() throws IllegalStateException {
    try {
      return this.loader.load();
    } catch (IOException e) {
      throw new IllegalStateException("Unable to load layout");
    }
  }
}
