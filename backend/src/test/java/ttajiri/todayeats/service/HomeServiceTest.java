package ttajiri.todayeats.service;

import org.junit.*;
import org.mockito.*;
import ttajiri.todayeats.model.*;
import ttajiri.todayeats.repository.*;
import ttajiri.todayeats.util.*;

import java.security.*;
import java.util.*;

import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.is;

public class HomeServiceTest {

    private HomeService target;

    @Mock
    private HomeRepository repository;

    @Mock
    private RandomHelper random;

    @Before
    public void setup() throws NoSuchAlgorithmException {
        MockitoAnnotations.initMocks(this);
        target = new HomeService(repository, random);
    }

    @Test
    public void 複数個のデータから特定のデータが返却される() {
        Iterable<TodayEats> resultList = createData();
        when(repository.findAll()).thenReturn(resultList);
        when(random.nextInt(3)).thenReturn(1);

        assertThat(target.retrieveTodayEats().getName(), is("pizza"));
    }

    private Iterable<TodayEats> createData() {
        TodayEats curry = new TodayEats();
        curry.setName("curry");
        TodayEats pizza = new TodayEats();
        pizza.setName("pizza");
        TodayEats pasta = new TodayEats();
        pasta.setName("pasta");

        return Arrays.asList(curry, pizza, pasta);
    }

    @Test
    public void 空のデータの場合はnullが返却される() {
        Iterable<TodayEats> resultList = Collections.emptyList();
        when(repository.findAll()).thenReturn(resultList);
        when(random.nextInt(0)).thenThrow(new RuntimeException("空データの場合はこの処理は流れません"));

        assertThat(target.retrieveTodayEats(), is(nullValue()));
    }
}
