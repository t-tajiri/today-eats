package ttajiri.todayeats.model;


import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.validation.constraints.*;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MyCategory {
    @Min(value = 0)
    private Integer id;

    private String name;
}
