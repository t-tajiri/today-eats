package ttajiri.todayeats.service;

import org.springframework.stereotype.*;
import ttajiri.todayeats.model.*;
import ttajiri.todayeats.repository.*;
import ttajiri.todayeats.util.*;

import java.util.*;
import java.util.stream.*;

@Service
public class HomeService {
    private HomeRepository repository;
    private RandomHelper random;

    public HomeService(HomeRepository repository, RandomHelper random) {
        this.repository = repository;
        this.random = random;
    }

    public TodayEats retrieveTodayEats() {
        // @formatter:off
        List<TodayEats> result = StreamSupport.stream(repository.findAll().spliterator(), false)
                                              .collect(Collectors.toUnmodifiableList());
        // @formatter:on

        //TODO throw data not found error
        if (result.isEmpty()) {
            return null;
        }

        int n = random.nextInt(result.size());
        return result.get(n);
    }

}
