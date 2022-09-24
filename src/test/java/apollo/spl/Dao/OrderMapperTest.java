package apollo.spl.Dao;


import apollo.spl.Pojo.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:spring-servlet.xml")
@Transactional("transactionManager")
class OrderMapperTest {

    @Autowired
    @Qualifier("orderMapper")
    private OrderMapper orderMapper;

    @Test
    public void selectById() {
        Order order = new Order(1L, "This is a order for clothing", "CANCELLED");
        assertNotEquals(orderMapper, null);
        assertEquals(order, orderMapper.selectById(1L));
    }

    @Test
    public void selectAll() {
        assertNotEquals(orderMapper, null);
        List<Order> orderList= orderMapper.selectAll();
        Iterator iterator = orderList.iterator();
        while(iterator.hasNext()){
            Order order = (Order)iterator.next();
            assertNotEquals(order, null);
        }
    }

    @Test
    public void exist() {
        assertNotEquals(orderMapper, null);
        assertEquals(orderMapper.exist(1L), true);
        assertEquals(orderMapper.exist(20L), false);
    }

    @Test
    public void insert() {
        Order order = new Order(0L, "insert test", "CANCELLED");
        assertNotEquals(orderMapper, null);
        assertEquals(orderMapper.insert(order), 1);

        Order order1 = new Order(1000L, "insert test", "CANCELLED");
        assertEquals(orderMapper.insert(order1), 1);

    }

    @Test
    public void deleteById() {
        assertNotEquals(orderMapper, null);
        assertEquals(orderMapper.deleteById(1L), 1);
    }

    @Test
    public void update() {
        Order order = new Order(1L, "insert test", "CANCELLED");
        assertNotEquals(orderMapper, null);
        assertEquals(orderMapper.update(order), 1);
    }
}