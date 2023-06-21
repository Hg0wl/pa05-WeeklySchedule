package cs3500.pa05.model;

/**
 * All possible days of the week
 */
public enum DaysOfWeek {
  /**
   * Represents the day Sunday
   */
  SUNDAY,
  /**
   * Represents the day Monday
   */
  MONDAY,
  /**
   * Represents the day Tuesday
   */
  TUESDAY,
  /**
   * Represents the day Wednesday
   */
  WEDNESDAY,
  /**
   * Represents the day Thursday
   */
  THURSDAY,
  /**
   * Represents the day Friday
   */
  FRIDAY,
  /**
   * Represents the day Saturday
   */
  SATURDAY;

  /**
   * Returns the given day of week as an enum value
   *
   * @param day String of the possible day
   * @return Day of week value that corresponds to the given string
   */
  public static DaysOfWeek getDayValue(String day) {
    String upCaseDay = day.toUpperCase();
    if (DaysOfWeek.SUNDAY.name().equals(upCaseDay)) {
      return DaysOfWeek.SUNDAY;
    } else if (DaysOfWeek.MONDAY.name().equals(upCaseDay)) {
      return DaysOfWeek.MONDAY;
    } else if (DaysOfWeek.TUESDAY.name().equals(upCaseDay)) {
      return DaysOfWeek.TUESDAY;
    } else if (DaysOfWeek.WEDNESDAY.name().equals(upCaseDay)) {
      return DaysOfWeek.WEDNESDAY;
    } else if (DaysOfWeek.THURSDAY.name().equals(upCaseDay)) {
      return DaysOfWeek.THURSDAY;
    } else if (DaysOfWeek.FRIDAY.name().equals(upCaseDay)) {
      return DaysOfWeek.FRIDAY;
    } else if (DaysOfWeek.SATURDAY.name().equals(upCaseDay)) {
      return DaysOfWeek.SATURDAY;
    } else {
      System.out.println();
      throw new IllegalArgumentException(day + " is not a valid day of the week");
    }
  }
}
