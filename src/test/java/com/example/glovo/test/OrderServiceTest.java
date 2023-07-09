package com.example.glovo.test;

import com.example.glovo.dto.OrderDto;
import com.example.glovo.dto.ProductDto;
import com.example.glovo.entity.Order;
import com.example.glovo.entity.Product;
import com.example.glovo.exeption.OrderNotFoundExeption;
import com.example.glovo.repository.OrderRepository;
import com.example.glovo.repository.ProductRepository;
import com.example.glovo.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class OrderServiceTest {

    private OrderRepository orderRepository;
    private ProductRepository productRepository;
    private OrderService orderService;

    @BeforeEach
    public void prepare() {
        this.orderRepository = Mockito.mock(OrderRepository.class);
        this.productRepository = Mockito.mock(ProductRepository.class);

        this.orderService = new OrderService(orderRepository, productRepository);
    }

    @Test
    void ordersEmptyIfNoOrderAdded() {
        var orders = orderService.getAll();
        assertTrue(orders.isEmpty(), "Orders list should be empty");
    }

    @Test
    void getAll() {
        Order order1 = Order.builder().id(1).build();
        Order order2 = Order.builder().id(2).build();

        List<Order> orders = List.of(order1, order2);

        Mockito.doReturn(orders).when(orderRepository).findAll();

        var getAllResult = orderService.getAll();
        assertEquals(2, getAllResult.size());
    }

    @Test
    void save() {
        OrderDto orderDto = OrderDto.builder().id(1).products(getListOfProductDtos()).build();
        Order order = Order.builder().id(1).build();

        Mockito.doReturn(order).when(orderRepository).save(any());

        orderService.save(orderDto);
        orderService.save(orderDto);

        Mockito.verify(orderRepository, Mockito.times(2)).save(any());
    }

    @Test
    void getOrderById() {
        var orderId = 1;
        List<Product> products = getListOfProducts(orderId);

        Order order = Order.builder().id(orderId).build();

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));
        when(productRepository.findAllByOrderId(anyInt())).thenReturn(products);

        OrderDto orderDto = orderService.getOrderById(orderId);

        assertEquals(60, orderDto.getCost());
    }

    @Test
    void trowExceptionIfOrderNotFound() {
        var orderId = 1;
        Order order = Order.builder().id(orderId).build();

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

        assertThrows(OrderNotFoundExeption.class, () -> orderService.getOrderById(2));
    }

    private static List<Product> getListOfProducts(int orderId) {
        return List.of(
                Product.builder().cost(10).name("Cola").orderId(orderId).build(),
                Product.builder().cost(20).name("Fanta").orderId(orderId).build(),
                Product.builder().cost(30).name("Sprite").orderId(orderId).build()
        );
    }

    private static List<ProductDto> getListOfProductDtos() {
        return List.of(
                ProductDto.builder().cost(10).name("Cola").id(1).build(),
                ProductDto.builder().cost(20).name("Fanta").id(2).build(),
                ProductDto.builder().cost(30).name("Sprite").id(3).build()
        );
    }
}