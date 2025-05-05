package com.example.orderservice.repository

import com.example.orderservice.model.Order
import com.example.orderservice.model.OrderStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository : JpaRepository<Order, Long> {
    fun findByCustomerEmail(email: String): List<Order>
    fun findByStatus(status: OrderStatus): List<Order>
} 