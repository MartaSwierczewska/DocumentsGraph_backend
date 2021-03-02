package com.projekt.integration;

import com.projekt.SiteApplication;
import com.projekt.connectingrepo.TodoConnectorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = TodoConnectorService.class
)
@ContextConfiguration(classes = SiteApplication.class)
@AutoConfigureMockMvc
public class TodoConnectorIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    // bez mockowania wiec integracyjny

    @Test
    public void whenGetHousesTodos_thenExpectStatusAccepted() throws Exception {
        mockMvc.perform(get("/house/house1/todos"))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(status().isAccepted());

    }

    @Test
    public void whenGetNumberOfAllCompletedTodos_thenExpectStatusOk() throws Exception { //if it reads number from database
        mockMvc.perform(get("/house/statistics/all"))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk());

    }

    @Test
    public void whenGetNumberOfAllHousesCompletedTodos_thenExpectStatusOk() throws Exception { //if it reads number from database
        mockMvc.perform(get("/house/statistics"))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk());

    }

}
