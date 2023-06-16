package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa05.model.Day;
import java.util.List;

public record WeekJson(
    @JsonProperty("days") List<Day> days,
    @JsonProperty("name") String name){
}
