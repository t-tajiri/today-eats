package ttajiri.todayeats.controller;

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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EatsController.class)
public class EatsControllerTest {

    private MockMvc mvc;

    @MockBean
    private EatsService service;

    @InjectMocks
    private EatsController target = new EatsController(service);

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(target).build();
    }

    @Test
    public void serviceからデータが渡された場合HTTPステータスコード200とデータが返却される() throws Exception {
        TodayEats eats = new TodayEats();
        eats.setName("ハンバーガー");

        when(service.retrieveTodayEats()).thenReturn(eats);

        // @formatter:off
        mvc.perform(MockMvcRequestBuilders
                    .get("/today-eats")
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("name").value(eats.getName()));
        // @formatter:on
    }
}
