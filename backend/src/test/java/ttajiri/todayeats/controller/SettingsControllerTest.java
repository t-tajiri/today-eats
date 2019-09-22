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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(SettingsController.class)
public class SettingsControllerTest {

    private static final String REQUEST_PATH = "/settings";

    private MockMvc mvc;

    @MockBean
    private SettingsService service;

    @InjectMocks
    private SettingsController target;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(target).build();
    }

    @Test
    public void カテゴリ一覧にリクエストを送るとHTTPステータスコード200とデータが返却される() throws Exception {
        var categories = new ArrayList<Category>();
        var category = new Category();
        category.setId(1L);
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
        category.setId(1L);

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
    public void 設定したいカテゴリにリクエストを送るとHTTPステータスコード200とデータが返却される() throws Exception {
        var category = new MyCategory();
        category.setId(2L);
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
}
