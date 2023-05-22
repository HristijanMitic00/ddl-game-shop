package lab4.ordermanagment.service.impl;

import jakarta.transaction.Transactional;
import lab4.ordermanagment.domain.model.Order;
import lab4.ordermanagment.domain.repository.OrderRepository;
import lab4.ordermanagment.service.OrderService;
import lab4.ordermanagment.service.forms.OrderForm;
import lab4.ordermanagment.service.forms.OrderItemForm;
import lab4.sharedkernel.domain.events.orders.OrderGameCreated;
import lab4.sharedkernel.infra.DomainEventPublisher;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import javax.validation.Validator;


import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final DomainEventPublisher domainEventPublisher;
    private Validator validator;

    public OrderServiceImpl(OrderRepository orderRepository, DomainEventPublisher domainEventPublisher) {
        this.orderRepository = orderRepository;
        this.domainEventPublisher = domainEventPublisher;
    }


    @Override
    public Long placeOrder(OrderForm orderForm) {
        Objects.requireNonNull(orderForm, "Order must not be null");
        var constraintViolations = validator.validate(orderForm);
        if(constraintViolations.size()>0){
            throw new ConstraintViolationException("The order form is not valid",constraintViolations);
        }
        var newOrder = orderRepository.saveAndFlush(toDomainObject(orderForm));
        newOrder.getGameInOrderSet().forEach(item-> domainEventPublisher
                .publish(new OrderGameCreated(item.getGameId(),item.getQuantity())));
        return newOrder.getId();
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public void addGame(Long id, OrderItemForm orderItemForm) throws RuntimeException {
        Order order = orderRepository.findById(id).orElseThrow(RuntimeException::new);
        order.addGame(orderItemForm.getGame(),orderItemForm.getQuantity());
        orderRepository.saveAndFlush(order);
        domainEventPublisher
                .publish(new OrderGameCreated(orderItemForm.getGame().getGameId(),orderItemForm.getQuantity()));
    }

    @Override
    public void deleteGame(Long orderId, Long gameId) throws RuntimeException {
        Order order = orderRepository.findById(orderId).orElseThrow(RuntimeException::new);
        order.removeGame(gameId);
        orderRepository.saveAndFlush(order);
    }

    private Order toDomainObject(OrderForm orderForm){
        var order = new Order(Instant.now(),orderForm.getCurrency());
        orderForm.getGames().forEach(game-> order.addGame(game.getGame(),game.getQuantity()));
        return order;
    }
}
