package ttajiri.todayeats.repository;

import org.springframework.data.repository.*;
import ttajiri.todayeats.model.*;

public interface HomeRepository extends CrudRepository<TodayEats, String> {
}
