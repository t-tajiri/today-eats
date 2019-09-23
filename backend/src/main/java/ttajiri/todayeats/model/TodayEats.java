package ttajiri.todayeats.model;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TodayEats {
    private Integer id;

    private String name;

    @JsonProperty("category_id")
    private Long categoryId;
}
