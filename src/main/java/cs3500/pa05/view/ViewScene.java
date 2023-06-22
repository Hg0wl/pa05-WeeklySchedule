package cs3500.pa05.view;

import javafx.scene.Scene;

/**
 * View Interface for viewing the entire
 */
public interface ViewScene {

  /**
   * Creates the new scene from the FXML file
   *
   * @return a scene created by FXML from scene builder
   */
  Scene load();
}
