package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa05.model.CompleteStatus;
import cs3500.pa05.model.DaysOfWeek;

/**
 * Class for the tasks object
 */
public class DayTask {

  private final String name;
  private final String description;
  private final DaysOfWeek day;
  private CompleteStatus completeStatus;

  /**
   * Creator for DayTask
   *
   * @param name the name of this task
   * @param description the description of this task
   * @param day the day of the week
   * @param completeStatus the completion status
   */
  @JsonCreator
  public DayTask(
      @JsonProperty("name") String name,
      @JsonProperty("description") String description,
      @JsonProperty("day") DaysOfWeek day,
      @JsonProperty("completeStatus") CompleteStatus completeStatus
  ) {
    this.name = name;
    this.description = description;
    this.day = day;
    this.completeStatus = completeStatus;
  }

  /**
   * Gets the name of this task
   *
   * @return the name
   */
  public String getName() {
    return this.name;
  }

  /**
   * Gets the description of this task
   *
   * @return the description
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * Gets the day of the week of this task
   *
   * @return the day of the week
   */
  public DaysOfWeek getDay() {
    return day;
  }

  /**
   * gets the complete status of this task
   *
   * @return the completeStatus
   */
  public CompleteStatus getCompleteStatus() {
    return this.completeStatus;
  }

  /**
   * toggles the Complete Status of the task
   */
  public void toggleCompleteStatus() {
    if (this.completeStatus.equals(CompleteStatus.INCOMPLETE)) {
      this.completeStatus = CompleteStatus.COMPLETE;
    } else {
      this.completeStatus = CompleteStatus.INCOMPLETE;
    }

  }
}
