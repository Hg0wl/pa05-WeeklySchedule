package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a single day in the planner
 */
public class Day {
  private final int maxEvents;
  private final int maxTasks;
  private final DaysOfWeek day;
  private final List<DayEvent> events;
  private final List<DayTask> tasks;

  /**
   * Creates a new day object with the given maximums and a day of the week
   *
   * @param maxEvents maximum number of events in the day
   * @param maxTasks maximum number of events in the day
   * @param day this day object's Day of the week
   * @param events list of DayEvents for this day
   * @param tasks list of DayTasks for this day
   */
  @JsonCreator
  public Day(
      @JsonProperty("maxEvents") int maxEvents,
      @JsonProperty("maxTasks") int maxTasks,
      @JsonProperty("day") DaysOfWeek day,
      @JsonProperty("events") List<DayEvent> events,
      @JsonProperty("tasks") List<DayTask> tasks) {
    this.maxEvents = maxEvents;
    this.maxTasks = maxTasks;
    this.day = day;
    this.events = events;
    this.tasks = tasks;
  }

  /**
   * Convenience constructor to create a day object with only maximums and a day of the week
   *
   * @param maxEvents maximum number of events in the day
   * @param maxTasks maximum number of events in the day
   * @param dayOfWeek this day object's Day of the week
   */
  public Day(int maxEvents, int maxTasks, DaysOfWeek dayOfWeek) {
    this.maxEvents = maxEvents;
    this.maxTasks = maxTasks;
    this.day = dayOfWeek;
    this.events = new ArrayList<>();
    this.tasks = new ArrayList<>();
  }

  /**
   * Determines if the number of events/tasks exceed the max
   *
   * @return true, if true, false otherwise
   */
  public boolean commitmentWarning() {
    return this.events.size() > this.maxEvents || this.tasks.size() > this.maxTasks;
  }

  /**
   * Adds the given event to this day
   *
   * @param event the day event object to be added to the day
   */
  public void addEvent(DayEvent event) {
    if (event.getDay() != this.day) {
      throw new IllegalArgumentException(this.day.toString() + " is not the day specified for the event");
    }
    this.events.add(event);
  }

  /**
   * Gets this day's events
   *
   * @return returns a deep copy of this day's list of events
   */
  public ArrayList<DayEvent> getEvents() {
    ArrayList<DayEvent> copy = new ArrayList<>();
    for (DayEvent event : this.events) {
      DayEvent newEvent = new DayEvent(event.getName(), event.getDescription(), event.getDay(),
          event.getStartTime(), event.getDurationMin(), event.getCategory());
      copy.add(newEvent);
    }
    return copy;
  }

  /**
   * Adds the given task to this day
   *
   * @param task the DayTask to be added to this Day
   */
  public void addTask(DayTask task) {
    if (task.getDay() != this.day) {
      throw new IllegalArgumentException(this.day.toString() + " is not the day specified for the task");
    }
    this.tasks.add(task);
  }

  /**
   * Gets this day's tasks
   *
   * @return returns a deep copy of this day's list of tasks
   */
  public List<DayTask> getTasks() {
    ArrayList<DayTask> copy = new ArrayList<>();
    for (DayTask task : this.tasks) {
      DayTask newTask = new DayTask(task.getName(), task.getDescription(), task.getDay(),
      task.getCompleteStatus());
      copy.add(newTask);
    }
    return copy;
  }

  /**
   * Returns this day's DaysOfWeek
   *
   * @return this day's day of week
   */
  public DaysOfWeek getDay() {
    return this.day;
  }

}
