package ttajiri.todayeats.model;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
public class TodayEats {
    @Id
    private String name;
}
