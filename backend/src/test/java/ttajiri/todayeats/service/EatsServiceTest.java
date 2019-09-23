package ttajiri.todayeats.service;

import org.junit.*;
import org.mockito.*;
import ttajiri.todayeats.model.*;
import ttajiri.todayeats.repository.*;
import ttajiri.todayeats.repository.dto.*;
import ttajiri.todayeats.util.*;

import java.security.*;
import java.util.*;

import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.is;

public class EatsServiceTest {

    private static final Long CATEGORY_ALL = 1L;
    private static final Long CATEGORY_OTHER = 2L;

    private EatsService target;

    @Mock
    private SettingsService settingsService;

    @Mock
    private EatsRepository eatsRepository;

    @Mock
    private RandomHelper random;

    @Before
    public void setup() throws NoSuchAlgorithmException {
        MockitoAnnotations.initMocks(this);
        target = new EatsService(settingsService, eatsRepository, random);
    }

    @Test
    public void 複数個のデータから特定のデータが返却される() {
        var myCategory = new MyCategory();
        myCategory.setId(CATEGORY_ALL);
        when(settingsService.retrieveMyCategory()).thenReturn(myCategory);

        Iterable<TodayEatsDto> resultList = createData();
        when(eatsRepository.findAll()).thenReturn(resultList);
        when(random.nextInt(3)).thenReturn(1);

        assertThat(target.retrieveTodayEats().getName(), is("pizza"));
    }

    @Test
    public void カテゴリの指定がある場合はカテゴリの中から抽出されたデータの中から特定のデータが返却される() {
        var myCategory = new MyCategory();
        myCategory.setId(CATEGORY_OTHER);
        when(settingsService.retrieveMyCategory()).thenReturn(myCategory);

        List<TodayEatsDto> resultList = createSpecifiedData();
        when(eatsRepository.findAllBy(CATEGORY_OTHER)).thenReturn(resultList);
        when(random.nextInt(2)).thenReturn(1);

        assertThat(target.retrieveTodayEats().getName(), is("pasta"));
    }

    private Iterable<TodayEatsDto> createData() {
        TodayEatsDto curry = new TodayEatsDto();
        curry.setName("curry");
        TodayEatsDto pizza = new TodayEatsDto();
        pizza.setName("pizza");
        TodayEatsDto pasta = new TodayEatsDto();
        pasta.setName("pasta");

        return Arrays.asList(curry, pizza, pasta);
    }

    private List<TodayEatsDto> createSpecifiedData() {
        TodayEatsDto curry = new TodayEatsDto();
        curry.setName("curry");
        TodayEatsDto pasta = new TodayEatsDto();
        pasta.setName("pasta");

        return Arrays.asList(curry, pasta);
    }

    @Test
    public void 空のデータの場合はnullが返却される() {
        var myCategory = new MyCategory();
        myCategory.setId(CATEGORY_ALL);
        when(settingsService.retrieveMyCategory()).thenReturn(myCategory);

        Iterable<TodayEatsDto> resultList = Collections.emptyList();
        when(eatsRepository.findAll()).thenReturn(resultList);
        when(random.nextInt(0)).thenThrow(new RuntimeException("空データの場合はこの処理は流れません"));

        assertThat(target.retrieveTodayEats(), is(nullValue()));
    }
}
