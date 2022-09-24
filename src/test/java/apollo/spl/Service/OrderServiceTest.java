package apollo.spl.Service;

import apollo.spl.Pojo.Order;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:spring-servlet.xml")
@Transactional("transactionManager")
class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void findAll() {
        assertNotEquals(orderService, null);
        List<Order> orderList= orderService.findAll();
        Iterator iterator = orderList.iterator();
        while(iterator.hasNext()){
            Order order = (Order)iterator.next();
            assertNotEquals(order, null);
        }
    }

    @Test
    public void findById() {
        Order order = new Order(1L, "This is a order for clothing", "CANCELLED");
        assertNotEquals(orderService, null);
        assertEquals(order, orderService.findById(1L));
    }

    @Test
    public void save() {
        assertNotEquals(orderService, null);
        Order order1 = new Order(50L, "This is a order for clothing", "IN_PROGRESS");
        assertEquals(orderService.save(order1), true);
        Order order2 = new Order(50L, "This is a order for clothing", "COMPLETE");
        assertEquals(orderService.save(order2), true);


    }

}