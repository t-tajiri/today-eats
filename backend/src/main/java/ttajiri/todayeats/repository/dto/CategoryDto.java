package ttajiri.todayeats.repository.dto;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "category")
public class CategoryDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne(mappedBy = "category")
    private MyCategoryDto myCategory;
}
