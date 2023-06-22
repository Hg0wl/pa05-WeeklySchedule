package cs3500.pa05.controller.fxadapters;

import cs3500.pa05.model.DayTask;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

/**
 * Represents a Label for a DayTask
 */
public class TaskQueueLabel {

  /**
   * Converts a day task to a label
   *
   * @param task the supplied label
   * @return a label containing information from the task
   */
  public static Label create(DayTask task) {
    String text = createText(task);
    Label output = new Label(text);
    if (text.contains("INCOMPLETE")) {
      output.setTextFill(Color.RED);
    } else {
      output.setTextFill(Color.GREEN);
    }
    output.setWrapText(true);
    return output;
  }

  /**
   * Helper for the create method. Gets the name and completion status of a task
   *
   * @param tasks task being checked
   * @return a string with the name and completion status
   */
  private static String createText(DayTask tasks) {
    String name = tasks.getName();
    String complete = tasks.getCompleteStatus().toString();

    StringBuilder text = new StringBuilder();

    text.append(name).append(", ").append(complete);
    return text.toString();
  }

}
