package ttajiri.todayeats.service;

import org.springframework.stereotype.*;
import ttajiri.todayeats.model.*;
import ttajiri.todayeats.repository.*;
import ttajiri.todayeats.repository.dto.*;
import ttajiri.todayeats.util.*;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

@Service
public class HomeService {
    private static final Long CATEGORY_ALL = 1L;

    private SettingsService settingsService;
    private HomeRepository homeRepository;
    private RandomHelper random;

    public HomeService(SettingsService settingsService, HomeRepository homeRepository, RandomHelper random) {
        this.settingsService = settingsService;
        this.homeRepository = homeRepository;
        this.random = random;
    }

    public TodayEats retrieveTodayEats() {
        var myCategory = settingsService.retrieveMyCategory().getId();
        List<TodayEats> result;

        if (CATEGORY_ALL.equals(myCategory)) {
            // @formatter:off
             result = StreamSupport.stream(homeRepository.findAll().spliterator(), false)
                                   .map(dto -> convertDtoToEntity.apply(dto))
                                   .collect(Collectors.toUnmodifiableList());
            // @formatter:on
        } else {
            // @formatter:off
            result = homeRepository.findAllBy(myCategory).stream()
                                                         .map(dto -> convertDtoToEntity.apply(dto))
                                                         .collect(Collectors.toUnmodifiableList());
            // @formatter:on
        }

        //TODO throw data not found error
        if (result.isEmpty()) {
            return null;
        }

        var n = random.nextInt(result.size());
        return result.get(n);
    }

    private Function<TodayEatsDto, TodayEats> convertDtoToEntity = (TodayEatsDto dto) -> {
        var entity = new TodayEats();
        entity.setName(dto.getName());
        return entity;
    };
}
