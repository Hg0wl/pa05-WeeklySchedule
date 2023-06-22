package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a record for a planner
 *
 * @param week the week
 * @param notes the notes
 */
public record PlannerJson(
    @JsonProperty("week") WeekJson week,
    @JsonProperty("notes") NotesJson notes,
    @JsonProperty("password") String password) {
}
