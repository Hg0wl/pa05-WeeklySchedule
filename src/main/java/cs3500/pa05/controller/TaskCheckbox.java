package cs3500.pa05.controller;

import cs3500.pa05.controller.eventHandlers.TaskToggleEventHandler;
import cs3500.pa05.model.CompleteStatus;
import cs3500.pa05.model.DayTask;
import java.nio.file.WatchKey;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.Border;
import javafx.scene.paint.Paint;

/**
 *
 */
public class TaskCheckbox {


  public static CheckBox create(DayTask task) {
    String text = createText(task);
    System.out.println(text);
    CheckBox output = new CheckBox(text);
    output.setWrapText(true);
    output.setBorder(Border.stroke(Paint.valueOf("Green")));
    output.setIndeterminate(false);
    if (task.getCompleteStatus().equals(CompleteStatus.COMPLETE)) {
      output.fire();
    }
    output.setOnAction(new TaskToggleEventHandler(task));
    return output;
  }

  private static String createText(DayTask task) {
    String name = task.getName();
    String description = task.getDescription();
    String category = task.getCategory();
    StringBuilder text = new StringBuilder();

    text.append(name).append(", ");
    if (!description.isEmpty()) {
      text.append(description).append(", ");
    }
    if (!category.isEmpty()) {
      text.append(", #").append(category);
    }

    return text.toString();

  }

}
