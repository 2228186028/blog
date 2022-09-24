package apollo.spl.Service;

import apollo.spl.Pojo.Order;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.List;

public interface OrderService {

    public List<Order> findAll();

    public Order findById(Long id);

    public Boolean save(Order order);

}
