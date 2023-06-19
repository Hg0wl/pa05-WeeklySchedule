package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class for the tasks object
 */
public class DayTask {

  private final String name;
  private final String description;
  private final DaysOfWeek day;
  private CompleteStatus completeStatus;
  private final String category;

  /**
   * Creator for DayTask
   *
   * @param name the name of this task
   * @param description the description of this task
   * @param day the day of the week
   * @param completeStatus the completion status
   * @param category the string representing the category of this task
   */
  @JsonCreator
  public DayTask(
      @JsonProperty("name") String name,
      @JsonProperty("description") String description,
      @JsonProperty("day") DaysOfWeek day,
      @JsonProperty("completeStatus") CompleteStatus completeStatus,
      @JsonProperty("category") String category
  ) {
    this.name = name;
    this.description = description;
    this.day = day;
    this.completeStatus = completeStatus;
    this.category = category;
  }

  /**
   * Convenience constructor to make a DayTask without a description
   *
   * @param name String name of the task
   * @param day day of the week the task is on
   * @param completeStatus whether the task itself is completed or not
   */
  public DayTask(String name, DaysOfWeek day, CompleteStatus completeStatus) {
    this(name, "", day, completeStatus, "");
  }

  /**
   * Convenience constructor to make a DayTask without a description
   *
   * @param name String name of the task
   * @param description String representing the description of this task
   * @param day day of the week the task is on
   * @param completeStatus whether the task itself is completed or not
   */
  public DayTask(String name, String description, DaysOfWeek day, CompleteStatus completeStatus) {
    this(name, description, day, completeStatus, "");
  }

  /**
   * Convenience constructor to make a DayTask without a description
   *
   * @param name String name of the task
   * @param day day of the week the task is on
   * @param completeStatus whether the task itself is completed or not
   * @param category the string representing the category of this task
   */
  public DayTask(String name, DaysOfWeek day, CompleteStatus completeStatus, String category) {
    this(name, "", day, completeStatus, category);
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
   * Returns this task's category
   *
   * @return String representing the category of this task
   */
  public String getCategory() {
    return this.category;
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

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof DayTask otherTask)) {
      return false;
    } else {
      return otherTask.getName().equals(this.name)
          && otherTask.getDescription().equals(this.description)
          && otherTask.getDay().equals(this.day)
          && otherTask.getCompleteStatus().equals(this.completeStatus)
          && otherTask.getCategory().equals(this.category);
    }
  }

  @Override
  public int hashCode() {
    return this.name.hashCode() + this.description.hashCode() + this.day.hashCode()
        + this.category.hashCode() + this.completeStatus.hashCode();
  }

}
