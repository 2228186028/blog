package apollo.spl.Dao;

import apollo.spl.Pojo.Order;

import java.util.List;

public interface OrderMapper {

    public Order selectById(Long id);

    public List<Order> selectAll();

    public Boolean exist(Long id);

    public Integer insert(Order order);

    public Integer deleteById(Long id);

    public Integer update(Order order);

}
