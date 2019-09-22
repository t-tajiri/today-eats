package ttajiri.todayeats.service;

import org.junit.*;
import org.mockito.*;
import ttajiri.todayeats.model.*;
import ttajiri.todayeats.repository.*;
import ttajiri.todayeats.repository.dto.*;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SettingsServiceTest {

    private static final long ID = 1L;
    private static final String CATEGORY_NAME = "testCategory";

    private SettingsService target;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private MyCategoryRepository myCategoryRepository;

    @Captor
    private ArgumentCaptor<MyCategoryDto> captor;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        target = new SettingsService(myCategoryRepository, categoryRepository);
    }

    @Test
    public void カテゴリ一覧が取得できる() {
        var categories = new ArrayList<CategoryDto>();
        var dto = new CategoryDto();
        dto.setId(ID);
        dto.setName(CATEGORY_NAME);
        categories.add(dto);

        when(categoryRepository.findAll()).thenReturn(categories);

        var result = target.retrieveCategories();

        assertThat(result.get(0).getId(), is(ID));
        assertThat(result.get(0).getName(), is(CATEGORY_NAME));
    }

    @Test
    public void 自分の設定したカテゴリが取得できる() {
        var dto = new MyCategoryDto();
        dto.setId(ID);
        Optional<MyCategoryDto> myCategory = Optional.of(dto);

        when(myCategoryRepository.findById(anyString())).thenReturn(myCategory);

        var result = target.retrieveMyCategory();

        assertThat(result.getId(), is(ID));
    }

    @Test(expected = RuntimeException.class)
    public void 自分の設定したカテゴリが見つからない場合は例外が発生する() {
        target.retrieveMyCategory();
        fail("例外が発生していません");
    }

    @Test
    public void 自分のカテゴリを登録できる() {
        var myCategory = new MyCategory();
        myCategory.setId(ID);

        target.registerCategory(myCategory);

        verify(myCategoryRepository).save(captor.capture());
        assertThat(captor.getValue().getId(), is(ID));
    }
}
