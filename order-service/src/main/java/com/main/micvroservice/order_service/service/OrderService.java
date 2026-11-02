package com.main.micvroservice.order_service.service;

import com.main.micvroservice.order_service.InventoryClient;
import com.main.micvroservice.order_service.dto.OrderRequest;
import com.main.micvroservice.order_service.model.Order;
import com.main.micvroservice.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private  final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;
    public void placeOrder(OrderRequest orderRequest){
        var isProductInStock= inventoryClient.isInStock(orderRequest.skucode(),orderRequest.quantity());
        if(isProductInStock) {
            // map OrderRequest to Order object
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setPrice(orderRequest.price());
            order.setSkucode(orderRequest.skucode());
            order.setQuantity(orderRequest.quantity());
            orderRepository.save(order);
        }else{
            throw  new RuntimeException("Product with skuCode "+
                    orderRequest.skucode()+" is not in stock");
        }
    }
}
