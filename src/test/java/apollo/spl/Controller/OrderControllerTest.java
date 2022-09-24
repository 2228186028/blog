package apollo.spl.Controller;


import apollo.spl.Pojo.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:spring-servlet.xml")
@Transactional("transactionManager")
class OrderControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private OrderController orderController;


    @BeforeEach
    public void setUp() {
        System.out.println("Running: setup !!! ");
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
    }

    @AfterEach
    public void tearDown() {
        System.out.println("Running: tearDown !!! ");

    }


    @Test
    public void all() throws Exception {

        String str = mockMvc.perform(MockMvcRequestBuilders
                            .get("/orders")
                            .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                            .andExpect(status().isOk())
                            .andReturn().getResponse().getContentAsString();

//        System.out.println(str);
        assertNotEquals(str, null);
    }

    @Test
    public void one() throws Exception {

        String str = mockMvc.perform(MockMvcRequestBuilders
                        .get("/orders/1")
                        .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                        .andExpect(status().isOk())
                        .andReturn().getResponse().getContentAsString();

        // validation your result ...

//        System.out.println(str);

        assertNotEquals(str, null);

    }

    @Test
    public void newOrder() throws Exception {

        Order order = new Order(0L, "This is a newOrder", "CANCELLED");

        // trans order:Order to json;
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(order);

//        System.out.println(requestJson);
        String responseString = mockMvc.perform(MockMvcRequestBuilders.post("/orders").contentType(MediaType.APPLICATION_JSON).content(requestJson)).
                andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

//        System.out.println(responseString);

    }

    @Test
    public void cancel() throws Exception{

        String responseString = mockMvc.perform(MockMvcRequestBuilders
                                .delete("/orders/1/cancel")
                                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                                .andExpect(status().isOk())
                                .andReturn().getResponse().getContentAsString();
//        System.out.println(responseString);
        assertEquals(responseString instanceof String, true);

    }

    @Test
    public void complete() throws Exception{

        String responseString = mockMvc.perform(MockMvcRequestBuilders
                        .put("/orders/1/complete")
                        .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                        .andExpect(status().isOk())
                        .andReturn().getResponse().getContentAsString();
//        System.out.println(responseString);
        assertEquals(responseString instanceof String, true);

    }


}