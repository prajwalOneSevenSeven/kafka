package com.example.kafka_demo.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderService service;

    @CrossOrigin
    @PostMapping("/placeOrder")
    public Order placeOrder(@RequestBody Order order) {
        order.setOrderDate(LocalDateTime.now());
        Order savedOrder = service.placeOrder(order);
        return savedOrder;
    }

    public static class OrderEvent {

        private String id;
        private String customerName;
        private String product;
        private int quantity;
        private double totalAmount;
        private LocalDateTime orderDate;


        public OrderEvent() {}

        public OrderEvent(String orderId, String customerName, String product, int quantity, double totalAmount, LocalDateTime orderDate) {
            this.id = orderId;
            this.customerName = customerName;
            this.product = product;
            this.quantity = quantity;
            this.totalAmount = totalAmount;
            this.orderDate =  orderDate;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public String getProduct() {
            return product;
        }

        public void setProduct(String product) {
            this.product = product;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public double getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(double totalAmount) {
            this.totalAmount = totalAmount;
        }

        public LocalDateTime getOrderDate() {
            return orderDate;
        }

        public void setOrderDate(LocalDateTime orderDate) {
            this.orderDate = orderDate;
        }
    }
}
