package cs3500.pa05.controller.eventHandlers;

import cs3500.pa05.model.DayTask;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Represents an event handler for toggling a task completion status
 */
public class TaskToggleEventHandler implements EventHandler<ActionEvent> {

  DayTask task;

  /**
   * Handles the toggling of the checkbox
   *
   * @param task the day task of which the completion status is changing
   */
  public TaskToggleEventHandler(DayTask task) {
    this.task = task;
  }

  /**
   * Handles the event
   *
   * @param event the event which occurred
   */
  @Override
  public void handle(ActionEvent event) {
    task.toggleCompleteStatus();
  }

}
