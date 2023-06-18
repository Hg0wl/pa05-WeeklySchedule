package cs3500.pa05.view;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

/**
 * An Implementation of viewing the week
 */
public class ViewWeekImpl implements ViewWeek {

  private final FXMLLoader loader;

  public ViewWeekImpl() {
    this.loader = new FXMLLoader();
    this.loader.setLocation(getClass().getClassLoader().getResource("weekView.fxml"));
  }

  @Override
  public Scene load() {
    try {
      return this.loader.load();
    } catch (IOException e) {
      throw new IllegalStateException("Unable to load layout");
    }
  }
}
