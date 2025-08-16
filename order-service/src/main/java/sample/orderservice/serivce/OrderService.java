package sample.orderservice.serivce;

import sample.orderservice.dto.OrderDto;
import sample.orderservice.jpa.OrderEntity;

public interface OrderService {
	OrderDto createOrder(OrderDto orderDetails);
	OrderDto getOrderByOrderId(String orderId);
	Iterable<OrderEntity> getOrdersByUserId(String userId);
}
