package lab4.ordermanagment.service;


import lab4.ordermanagment.domain.model.Order;
import lab4.ordermanagment.domain.model.Type;
import lab4.ordermanagment.domain.valueojects.Game;
import lab4.ordermanagment.service.forms.OrderForm;
import lab4.ordermanagment.service.forms.OrderItemForm;
import lab4.ordermanagment.xport.ProductClient;
import lab4.sharedkernel.domain.financial.Currency;
import lab4.sharedkernel.domain.financial.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@SpringBootTest
public class OrderServiceImplTests {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductClient productClient;



    private static Game newProduct(String name, Money price, Type gameType,int sales) {
        Long random = new Random().nextLong();
        Game g = new Game(random, price, name,gameType,sales);
        return g;
    }

    @Test
    public void testPlaceOrder() {

        OrderItemForm oi1 = new OrderItemForm();
        oi1.setGame(newProduct("Gta III", Money.valueOf(Currency.MKD,200),Type.ACTION,10));
        oi1.setQuantity(1);

        OrderItemForm oi2 = new OrderItemForm();
        oi2.setGame(newProduct("Gta Vice City", Money.valueOf(Currency.MKD,100),Type.ACTION,8));
        oi2.setQuantity(2);

        OrderForm orderForm = new OrderForm();
        orderForm.setCurrency(Currency.MKD);
        orderForm.setGames(Arrays.asList(oi1,oi2));

        Long newOrderId = orderService.placeOrder(orderForm);
        Order newOrder = orderService.findById(newOrderId).orElseThrow(RuntimeException::new);
        Assertions.assertEquals(newOrder.total(),Money.valueOf(Currency.MKD,300));

    }

    @Test
    public void testPlaceOrderWithRealData() {
        List<Game> productList = productClient.findAll();
        Game p1 = productList.get(0);
        Game p2 = productList.get(1);

        OrderItemForm oi1 = new OrderItemForm();
        oi1.setGame(p1);
        oi1.setQuantity(1);

        OrderItemForm oi2 = new OrderItemForm();
        oi2.setGame(p2);
        oi2.setQuantity(2);

        OrderForm orderForm = new OrderForm();
        orderForm.setCurrency(Currency.MKD);
        orderForm.setGames(Arrays.asList(oi1,oi2));

        Long newOrderId = orderService.placeOrder(orderForm);
        Order newOrder = orderService.findById(newOrderId).orElseThrow(RuntimeException::new);

        Money outMoney = p1.getPrice().multiply(oi1.getQuantity()).add(p2.getPrice().multiply(oi2.getQuantity()));
        Assertions.assertEquals(newOrder.total(),outMoney);
    }


}
