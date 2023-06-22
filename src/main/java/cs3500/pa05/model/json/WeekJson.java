package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa05.model.Day;
import java.util.List;

/**
 * Represents a json record for a week
 *
 * @param days the list of days in the week
 * @param categories the list of categories in the week
 * @param name the name of the week
 */
public record WeekJson(
    @JsonProperty("days") List<Day> days,
    @JsonProperty("categories") List<String> categories,
    @JsonProperty("name") String name){
}
