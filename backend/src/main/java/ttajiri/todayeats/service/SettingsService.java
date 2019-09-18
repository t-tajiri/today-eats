package ttajiri.todayeats.service;

import org.springframework.stereotype.*;
import ttajiri.todayeats.model.*;
import ttajiri.todayeats.repository.*;
import ttajiri.todayeats.repository.dto.*;

import java.util.*;
import java.util.stream.*;

@Service
public class SettingsService {

    // TODO 認証サービスと連携した際は下記のユーザー名を削除すること
    private static final String USERNAME = "test";

    private CategoryRepository categoryRepository;
    private MyCategoryRepository myCategoryRepository;

    public SettingsService(CategoryRepository categoryRepository, MyCategoryRepository myCategoryRepository) {
        this.categoryRepository = categoryRepository;
        this.myCategoryRepository = myCategoryRepository;
    }

    public List<Category> retrieveCategories() {
        // @formatter:off
        return StreamSupport.stream(categoryRepository.findAll().spliterator(), false)
                            .map(category -> {
                                var c = new Category();
                                c.setId(category.getId());
                                c.setName(category.getName());
                                return c;
                            })
                            .collect(Collectors.toUnmodifiableList());
        // @formatter:on
    }

    public MyCategory retrieveMyCategory() {
        // ユーザー初回登録時に初期データとして登録するため、データが見つからない場合はエラーとする
        var myCategory = myCategoryRepository.findById(USERNAME).orElseThrow(RuntimeException::new);

        var result = new MyCategory();
        result.setId(myCategory.getId());
        return result;
    }

    public void registerCategory(MyCategory category) {
        var myCategory = new MyCategoryDto();
        myCategory.setUsername(USERNAME);
        myCategory.setId(category.getId());

        myCategoryRepository.save(myCategory);
    }

}
