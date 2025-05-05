package com.example.orderservice.service

import com.example.orderservice.model.Order
import com.example.orderservice.model.OrderStatus
import com.example.orderservice.repository.OrderRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.mockito.Mockito.*
import org.springframework.boot.test.context.SpringBootTest
import java.math.BigDecimal
import java.time.LocalDateTime

@SpringBootTest
class OrderServiceTest {

    private val orderRepository: OrderRepository = mock(OrderRepository::class.java)
    private val orderService = OrderService(orderRepository)

    @Test
    fun `test create order`() {
        val order = Order(
            customerName = "Test User",
            customerEmail = "test@example.com",
            totalAmount = BigDecimal("100.00"),
            status = OrderStatus.PENDING
        )

        `when`(orderRepository.save(any(Order::class.java))).thenReturn(order)

        val result = orderService.createOrder(order)

        assertNotNull(result)
        assertEquals("Test User", result.customerName)
        assertEquals("test@example.com", result.customerEmail)
        assertEquals(BigDecimal("100.00"), result.totalAmount)
        assertEquals(OrderStatus.PENDING, result.status)
    }

    @Test
    fun `test get order by id`() {
        val order = Order(
            id = 1L,
            customerName = "Test User",
            customerEmail = "test@example.com",
            totalAmount = BigDecimal("100.00"),
            status = OrderStatus.PENDING
        )

        `when`(orderRepository.findById(1L)).thenReturn(java.util.Optional.of(order))

        val result = orderService.getOrder(1L)

        assertNotNull(result)
        assertEquals(1L, result.id)
        assertEquals("Test User", result.customerName)
    }

    @Test
    fun `test get orders by customer email`() {
        val orders = listOf(
            Order(
                customerName = "Test User",
                customerEmail = "test@example.com",
                totalAmount = BigDecimal("100.00"),
                status = OrderStatus.PENDING
            ),
            Order(
                customerName = "Test User",
                customerEmail = "test@example.com",
                totalAmount = BigDecimal("200.00"),
                status = OrderStatus.CONFIRMED
            )
        )

        `when`(orderRepository.findByCustomerEmail("test@example.com")).thenReturn(orders)

        val result = orderService.getOrdersByCustomerEmail("test@example.com")

        assertNotNull(result)
        assertEquals(2, result.size)
        assertTrue(result.all { it.customerEmail == "test@example.com" })
    }
} 