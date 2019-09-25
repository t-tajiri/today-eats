package ttajiri.todayeats.repository.dto;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "today_eats")
public class TodayEatsDto {
    @Id
    private Integer id;

    private String name;

    private Integer categoryId;
}
