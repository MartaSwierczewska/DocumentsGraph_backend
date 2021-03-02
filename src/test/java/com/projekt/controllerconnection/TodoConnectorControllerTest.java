package com.projekt.controllerconnection;

import com.projekt.connectingrepo.TodoConnector;
import com.projekt.connectingrepo.TodoConnectorService;
import com.projekt.houses.HouseService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest
public class TodoConnectorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TodoConnectorService todoConnectorService;
    @MockBean
    private HouseService houseService;

    private TodoConnector todoConnector; // sample todoConnector result

    @Before
    public void setUp() {
        todoConnector = new TodoConnector();
        todoConnector.setCompleted(true);
    }

//    @Test
//    public void whenGetHousesTodos_thenExpectStatusAcceptedAndJsonArray() throws Exception { //sample controllerconnection method test
//
//        given(todoConnectorService.getHouseTodosByHouseName(anyString())).willReturn(Collections.singletonList(todoConnector));
//
//        mockMvc.perform(get("/house/house1/todos")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isAccepted())
//                .andExpect(content().contentType("application/json;charset=UTF-8"))
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$[0].completed", is(todoConnector.isCompleted())));
//    }

}
