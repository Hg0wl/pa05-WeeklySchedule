package cs3500.pa05.controller;

import cs3500.pa05.model.DayTask;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class TaskQueueLabel {

  /**
   *
   *
   * @param task
   * @return
   */
  public static Label create(DayTask task) {
    String text = createText(task);
    System.out.println(text);
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
   * helper for the create method. gets the name and completion status of a task
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
