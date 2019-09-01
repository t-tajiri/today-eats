package ttajiri.todayeats.service;

import org.springframework.stereotype.*;
import ttajiri.todayeats.model.*;
import ttajiri.todayeats.repository.*;

import java.security.*;
import java.util.*;

@Service
public class HomeService {
    private HomeRepository repository;
    private SecureRandom random;

    public HomeService(HomeRepository repository) throws NoSuchAlgorithmException {
        this.repository = repository;
        this.random = SecureRandom.getInstanceStrong();
    }

    public TodayEats retrieveTodayEats() {
        List<TodayEats> result = new ArrayList<>();

        for (TodayEats todayEats : repository.findAll()) {
            result.add(todayEats);
        }

        int n = random.nextInt(result.size());
        return result.get(n);
    }

}
