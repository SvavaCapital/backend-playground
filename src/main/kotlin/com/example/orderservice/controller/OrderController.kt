package com.example.orderservice.controller

import com.example.orderservice.model.Order
import com.example.orderservice.model.OrderStatus
import com.example.orderservice.service.OrderService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import jakarta.validation.Valid

@RestController
@RequestMapping("/api/orders")
class OrderController(private val orderService: OrderService) {

    @PostMapping
    fun createOrder(@Valid @RequestBody order: Order): ResponseEntity<Order> {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(order))
    }

    @GetMapping("/{id}")
    fun getOrder(@PathVariable id: Long): ResponseEntity<Order> {
        return ResponseEntity.ok(orderService.getOrder(id))
    }

    @GetMapping
    fun getAllOrders(): ResponseEntity<List<Order>> {
        return ResponseEntity.ok(orderService.getAllOrderss())
    }

    @PutMapping("/{id}")
    fun updateOrder(@PathVariable id: Long, @Valid @RequestBody order: Order): ResponseEntity<Order> {
        return ResponseEntity.ok(orderService.updateOrder(id, order))
    }

    @DeleteMapping("/{id}")
    fun deleteOrder(@PathVariable id: Long): ResponseEntity<Void> {
        orderService.deleteOrder(id)
        return ResponseEntity.noContent().build()
    }

    @GetMapping("/customer/{email}")
    fun getOrdersByCustomerEmail(@PathVariable email: String): ResponseEntity<List<Order>> {
        return ResponseEntity.ok(orderService.getOrdersByCustomerEmail(email))
    }

    @GetMapping("/status/{status}")
    fun getOrdersByStatus(@PathVariable status: OrderStatus): ResponseEntity<List<Order>> {
        return ResponseEntity.ok(orderService.getOrdersByStatus(status))
    }
} 