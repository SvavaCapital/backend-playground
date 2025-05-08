package com.example.orderservice.service

import com.example.orderservice.model.Order
import com.example.orderservice.model.OrderStatus
import com.example.orderservice.repository.OrderRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import jakarta.persistence.EntityNotFoundException

@Service
@Transactional
class OrderService(private val orderRepository: OrderRepository) {

    fun createOrder(order: Order): Order {
        return orderRepository.save(order)
    }

    fun getOrder(id: Long): Order {
        return orderRepository.findById(id)
            .orElseThrow { EntityNotFoundException("Order not found with id: $id") }
    }

    fun getAllOrderss(): List<Order> {
        return orderRepository.findAll()
    }

    fun updateOrder(id: Long, updatedOrder: Order): Order {
        val existingOrder = getOrder(id)
        return orderRepository.save(
            existingOrder.copy(
                customerName = updatedOrder.customerName,
                customerEmail = updatedOrder.customerEmail,
                totalAmount = updatedOrder.totalAmount,
                status = updatedOrder.status
            )
        )
    }

    fun deleteOrder(id: Long) {
        if (!orderRepository.existsById(id)) {
            throw EntityNotFoundException("Order not found with id: $id")
        }
        orderRepository.deleteById(id)
    }

    fun getOrdersByCustomerEmail(email: String): List<Order> {
        return orderRepository.findByCustomerEmail(email)
    }

    fun getOrdersByStatus(status: OrderStatus): List<Order> {
        return orderRepository.findByStatus(status)
    }
} 