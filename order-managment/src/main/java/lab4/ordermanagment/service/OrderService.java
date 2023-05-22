package lab4.ordermanagment.service;

import lab4.ordermanagment.domain.model.Order;
import lab4.ordermanagment.service.forms.OrderForm;
import lab4.ordermanagment.service.forms.OrderItemForm;

import java.util.List;
import java.util.Optional;

public interface OrderService {

     Long placeOrder(OrderForm orderForm);
     List<Order> findAll();
     Optional<Order> findById(Long id);
     void addGame(Long id, OrderItemForm orderItemForm) throws RuntimeException;
     void deleteGame(Long orderId,Long gameId) throws RuntimeException;

}
