package ttajiri.todayeats.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.*;
import org.springframework.data.repository.query.*;
import ttajiri.todayeats.repository.dto.*;

import java.util.*;

public interface EatsRepository extends CrudRepository<TodayEatsDto, Integer> {
    @Query("SELECT t FROM #{#entityName} t WHERE t.categoryId = :id")
    List<TodayEatsDto> findAllBy(@Param("id") Integer id);
}
