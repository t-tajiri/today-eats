package ttajiri.todayeats.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.*;
import org.springframework.data.repository.query.*;
import ttajiri.todayeats.repository.dto.*;

import java.util.*;

public interface HomeRepository extends CrudRepository<TodayEatsDto, String> {
    @Query("SELECT t FROM #{#entityName} t WHERE t.categoryId = :id")
    List<TodayEatsDto> findAllBy(@Param("id") Long id);
}
