package ttajiri.todayeats.controller;

import com.fasterxml.jackson.databind.*;
import org.junit.*;
import org.junit.runner.*;
import org.mockito.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.http.*;
import org.springframework.test.context.junit4.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.*;
import org.springframework.test.web.servlet.result.*;
import org.springframework.test.web.servlet.setup.*;
import ttajiri.todayeats.model.*;
import ttajiri.todayeats.service.*;

import java.util.*;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(SettingsController.class)
public class SettingsControllerTest {

    private static final String REQUEST_PATH = "/settings";
    private static final String REQUEST_EATS_PATH = REQUEST_PATH + "/eats";

    private MockMvc mvc;

    @MockBean
    private SettingsService service;

    @InjectMocks
    private SettingsController target;

    @Captor
    private ArgumentCaptor<TodayEats> todayEatsCaptor;

    @Captor
    private ArgumentCaptor<Integer> idCaptor;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(target).build();
    }

    @Test
    public void カテゴリ一覧にリクエストを送るとHTTPステータスコード200とデータが返却される() throws Exception {
        var categories = new ArrayList<Category>();
        var category = new Category();
        category.setId(1);
        categories.add(category);

        when(service.retrieveCategories()).thenReturn(categories);

        // @formatter:off
        mvc.perform(MockMvcRequestBuilders
                    .get(REQUEST_PATH)
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id", equalTo(category.getId().intValue())));
        // @formatter:on
    }

    @Test
    public void 自分のカテゴリにリクエストを送るとHTTPステータスコード200とデータが返却される() throws Exception {
        var category = new MyCategory();
        category.setId(1);

        when(service.retrieveMyCategory()).thenReturn(category);

        // @formatter:off
        mvc.perform(MockMvcRequestBuilders
                    .get(REQUEST_PATH + "/mine")
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("id").value(category.getId()));
        // @formatter:on
    }

    @Test
    public void 設定したいカテゴリにリクエストを送るとHTTPステータスコード201とデータが返却される() throws Exception {
        var category = new MyCategory();
        category.setId(2);
        category.setName("test");

        // @formatter:off
        mvc.perform(MockMvcRequestBuilders
                    .post(REQUEST_PATH)
                    .content(new ObjectMapper().writeValueAsString(category))
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated())
            .andExpect(MockMvcResultMatchers.header().string("location", containsString(category.getId().toString())));
        // @formatter:on
    }

    @Test
    public void 設定したご飯の一覧とHTTPステータスコード200が返却される() throws Exception {
        var expected = new TodayEats();
        expected.setName("test");
        var eats = Collections.singletonList(expected);

        when(service.retrieveEats()).thenReturn(eats);

        // @formatter:off
        mvc.perform(MockMvcRequestBuilders
                    .get(REQUEST_EATS_PATH)
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.[0].name").value(expected.getName()));
        // @formatter:on
    }

    @Test
    public void 内容を変更したいご飯に対してリクエストを送るとHTTPステータスコード204が返却される() throws Exception {
        var expected = new TodayEats();
        expected.setName("test");

        // @formatter:off
        mvc.perform(MockMvcRequestBuilders
                    .put(REQUEST_EATS_PATH)
                    .content(new ObjectMapper().writeValueAsString(expected))
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());
        // @formatter:on

        verify(service).updateEats(todayEatsCaptor.capture());
        assertThat(todayEatsCaptor.getValue(), is(expected));
    }

    @Test
    public void 内容を削除したいご飯に対してリクエストを送るとHTTPステータスコード204が返却される() throws Exception {
        int expected = 1;

        // @formatter:off
        mvc.perform(MockMvcRequestBuilders
                    .delete(REQUEST_EATS_PATH + "/" + expected)
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());
        // @formatter:on

        verify(service).deleteEats(idCaptor.capture());
        assertThat(idCaptor.getValue(), is(expected));
    }
}
