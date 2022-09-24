package apollo.spl.Service;

import apollo.spl.Dao.OrderMapper;
import apollo.spl.Pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.List;

@Service
public class OrderServiceImp implements OrderService{

    @Autowired
    private OrderMapper orderMapper;

    public List<Order> findAll() {

        Jedis jedis = new Jedis("localhost");

        List<Order> orderList = orderMapper.selectAll();

        return orderList;
    }

    public Order findById(Long id) {
        Order order = orderMapper.selectById(id);
        return order;
    }

    public Boolean save(Order order) {

        Integer affectedRow;
        if(orderMapper.exist(order.getId())){
            affectedRow = orderMapper.update(order);
        }else{
            affectedRow = orderMapper.insert(order);
        }
        return affectedRow > 0;
    }
}
