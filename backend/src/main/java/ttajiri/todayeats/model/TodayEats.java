package ttajiri.todayeats.model;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import ttajiri.todayeats.model.validation.*;

import javax.validation.constraints.*;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TodayEats {
    @NotNull(groups = { Update.class })
    @Min(value = 0, groups = { Update.class })
    private Integer id;

    @NotBlank(groups = { Insert.class, Update.class })
    private String name;

    @JsonProperty("category_id")
    @NotNull(groups = { Insert.class, Update.class })
    @Min(value = 0, groups = { Insert.class, Update.class })
    private Integer categoryId;
}
