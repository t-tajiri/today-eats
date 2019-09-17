package ttajiri.todayeats.repository;

import org.springframework.data.repository.*;
import ttajiri.todayeats.repository.dto.*;

public interface MyCategoryRepository extends CrudRepository<MyCategoryDto, String> {
}
