package ttajiri.todayeats.service;

import org.junit.*;
import org.mockito.*;
import ttajiri.todayeats.model.*;
import ttajiri.todayeats.repository.*;
import ttajiri.todayeats.repository.dto.*;
import ttajiri.todayeats.util.*;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SettingsServiceTest {

    private static final int ID = 1;
    private static final String NAME = "test";
    private static final int CATEGORY_ID = 2;
    private static final String CATEGORY_NAME = "testCategory";

    private SettingsService target;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private MyCategoryRepository myCategoryRepository;

    @Mock
    private EatsRepository eatsRepository;

    @Mock
    private RandomHelper randomHelper;

    @Captor
    private ArgumentCaptor<MyCategoryDto> myCategoryDtoCaptor;

    @Captor
    private ArgumentCaptor<TodayEatsDto> todayEatsDtoCaptor;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        target = new SettingsService(myCategoryRepository, categoryRepository, eatsRepository, randomHelper);
    }

    @Test
    public void カテゴリ一覧が取得できる() {
        var categories = new ArrayList<CategoryDto>();
        var dto = new CategoryDto();
        dto.setId(CATEGORY_ID);
        dto.setName(CATEGORY_NAME);
        categories.add(dto);

        when(categoryRepository.findAll()).thenReturn(categories);

        var result = target.retrieveCategories();

        assertThat(result.get(0).getId(), is(CATEGORY_ID));
        assertThat(result.get(0).getName(), is(CATEGORY_NAME));
    }

    @Test
    public void 自分の設定したカテゴリが取得できる() {
        var dto = new MyCategoryDto();
        dto.setId(CATEGORY_ID);
        Optional<MyCategoryDto> myCategory = Optional.of(dto);

        when(myCategoryRepository.findById(anyString())).thenReturn(myCategory);

        var result = target.retrieveMyCategory();

        assertThat(result.getId(), is(CATEGORY_ID));
    }

    @Test(expected = RuntimeException.class)
    public void 自分の設定したカテゴリが見つからない場合は例外が発生する() {
        target.retrieveMyCategory();
        fail("例外が発生していません");
    }

    @Test
    public void 自分のカテゴリを登録できる() {
        var myCategory = new MyCategory();
        myCategory.setId(CATEGORY_ID);

        target.registerCategory(myCategory);

        verify(myCategoryRepository).save(myCategoryDtoCaptor.capture());
        assertThat(myCategoryDtoCaptor.getValue().getId(), is(CATEGORY_ID));
    }

    @Test
    public void ご飯の一覧が取得できる() {
        var eats = new ArrayList<TodayEatsDto>();
        var dto = new TodayEatsDto();
        dto.setId(ID);
        dto.setName(NAME);
        dto.setCategoryId(CATEGORY_ID);
        eats.add(dto);

        when(eatsRepository.findAll()).thenReturn(eats);

        var result = target.retrieveEats();

        assertThat(result.get(0).getId(), is(ID));
        assertThat(result.get(0).getName(), is(NAME));
        assertThat(result.get(0).getCategoryId(), is(CATEGORY_ID));
    }

    @Test
    public void 変更対象が存在する場合に内容が変更される() {
        var eats = new TodayEats();
        eats.setId(ID);
        eats.setName(NAME);
        eats.setCategoryId(CATEGORY_ID);

        when(eatsRepository.existsById(ID)).thenReturn(true);

        target.updateEats(eats);

        verify(eatsRepository).save(todayEatsDtoCaptor.capture());
        assertThat(todayEatsDtoCaptor.getValue().getId(), is(ID));
        assertThat(todayEatsDtoCaptor.getValue().getName(), is(NAME));
        assertThat(todayEatsDtoCaptor.getValue().getCategoryId(), is(CATEGORY_ID));
    }

    @Test
    public void 変更対象が存在しない場合に内容が変更されない() {
        when(eatsRepository.existsById(any(Integer.class))).thenReturn(false);

        target.updateEats(new TodayEats());

        verify(eatsRepository, never()).save(any(TodayEatsDto.class));
    }

    @Test
    public void ご飯の内容が削除される() {
        target.deleteEats(ID);

        verify(eatsRepository).delete(todayEatsDtoCaptor.capture());
        assertThat(todayEatsDtoCaptor.getValue().getId(), is(ID));
    }
}
