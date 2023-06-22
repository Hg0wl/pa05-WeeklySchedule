package cs3500.pa05.controller.fxadapters;

import cs3500.pa05.controller.eventhandlers.TaskToggleEventHandler;
import cs3500.pa05.model.CompleteStatus;
import cs3500.pa05.model.DayTask;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.Border;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

/**
 * Class of static methods for creating a task checkbox
 */
public class TaskCheckbox {

  /**
   * Creates a VBox that contains both the checkbox and a link if the description contains a link
   *
   * @param task the task to be turned into a JavaFX node
   * @return VBox that contains either just the checkbox or the checkbox and a link if necessary
   */
  public static VBox create(DayTask task) {
    if (task.getDescription().contains("http")) {
      String text = createTextNoDesc(task);
      CheckBox output = new CheckBox(text);
      output.setWrapText(true);
      output.setBorder(Border.stroke(Paint.valueOf("Green")));
      output.setIndeterminate(false);

      if (task.getCompleteStatus().equals(CompleteStatus.COMPLETE)) {
        output.fire();
      }
      Hyperlink link = new Hyperlink(task.getDescription());
      link.setOnAction(e -> OpenLink.handleLink(task.getDescription()));
      link.setWrapText(true);
      output.setOnAction(new TaskToggleEventHandler(task));
      return new VBox(output, link);

    } else {
      String text = createText(task);
      CheckBox output = new CheckBox(text);
      output.setWrapText(true);
      output.setBorder(Border.stroke(Paint.valueOf("Green")));
      output.setIndeterminate(false);
      if (task.getCompleteStatus().equals(CompleteStatus.COMPLETE)) {
        output.fire();
      }
      output.setOnAction(new TaskToggleEventHandler(task));
      return new VBox(output);
    }
  }

  /**
   * Helper that creates the text associated with the supplied day task
   *
   * @param task the day task to convert into text
   * @return a string representation of the task's information
   */
  private static String createText(DayTask task) {
    String name = task.getName();
    String description = task.getDescription();
    String category = task.getCategory();
    StringBuilder text = new StringBuilder();

    text.append(name);
    if (!description.isEmpty()) {
      text.append(",\n");
      text.append(description).append(",\n");
    }
    if (!category.isEmpty()) {
      text.append(",\n#").append(category);
    }

    return text.toString();
  }

  /**
   * Helper that creates the text associated with the supplied day task, without its description
   *
   * @param task the supplied task to parse
   * @return the string representation
   */
  private static String createTextNoDesc(DayTask task) {
    String name = task.getName();
    String category = task.getCategory();
    StringBuilder text = new StringBuilder();

    text.append(name);
    if (!category.isEmpty()) {
      text.append(",\n#").append(category);
    }
    return text.toString();
  }
}
