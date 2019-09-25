package ttajiri.todayeats.repository.dto;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "my_category")
public class MyCategoryDto {
    @Id
    private String username;

    private Integer id;

    @OneToOne
    @JoinColumn(name = "id", insertable = false, updatable = false)
    private CategoryDto category;
}
