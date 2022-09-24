package apollo.spl.Controller;

import apollo.spl.Pojo.Order;
import apollo.spl.Service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class OrderController {


    private static Log log = LogFactory.getLog(OrderController.class);

    @Autowired
    @Qualifier("orderServiceImp")
    private OrderService orderService;



    @GetMapping("/orders")
    public String all() throws JsonProcessingException {

        List<Order> orderList = orderService.findAll();

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String responseJson = ow.writeValueAsString(orderList);

        return responseJson;

    }

    // orderSercice 为 null ;   is a problem ...
    @GetMapping("/orders/{id}")
    public String one(@PathVariable("id") Long id) throws Exception {
        Order order = orderService.findById(id);

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String responseJson = ow.writeValueAsString(order);

        return responseJson;
    }

    // post form
    //@PostMapping("/orders")
    @PostMapping("/orders")
    public String newOrder(@RequestBody Order order) throws JsonProcessingException {

        log.error("this is not error, just a test: " + order.toString());

        order.setStatus(new String("IN_PROGRESS"));
        Boolean isSaved = orderService.save(order);

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String responseJson = ow.writeValueAsString(isSaved);

        return responseJson;

    }

    @DeleteMapping("/orders/{id}/cancel")
    public String cancel(@PathVariable("id") Long id) throws JsonProcessingException {
        Order order = orderService.findById(id);

        if(order.getStatus().equals("IN_PROGRESS")){
            order.setStatus("CANCELLED");
            orderService.save(order);
        }

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String responseJson = ow.writeValueAsString(order);

        return responseJson;
    }

    @PutMapping("/orders/{id}/complete")
    public String complete(@PathVariable("id") Long id) throws JsonProcessingException {
        Order order = orderService.findById(id);

        if(order.getStatus().equals("IN_PROGRESS")){
            order.setStatus("COMPLETED");
            orderService.save(order);
        }

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String responseJson = ow.writeValueAsString(order);

        return responseJson;
    }
}




