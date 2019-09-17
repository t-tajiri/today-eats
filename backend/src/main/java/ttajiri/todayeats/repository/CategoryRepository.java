package ttajiri.todayeats.repository;

import org.springframework.data.repository.*;
import ttajiri.todayeats.repository.dto.*;

public interface CategoryRepository extends CrudRepository<CategoryDto, Long> {
}
