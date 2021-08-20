package model.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Order extends Entity {

    private Long orderId;
    private BigDecimal totalPrice;
    private Timestamp dateOfCreation;
    private OrderStatus orderStatus;
    private Long userId;
    private Long songId;

    public Order(Long orderId, BigDecimal totalPrice, Timestamp dateOfCreation, OrderStatus orderStatus, Long userId, Long songId) {
        this.orderId = orderId;
        this.totalPrice = totalPrice;
        this.dateOfCreation = dateOfCreation;
        this.orderStatus = orderStatus;
        this.userId = userId;
        this.songId = songId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Timestamp getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Timestamp dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSongId() {
        return songId;
    }

    public void setSongId(Long songId) {
        this.songId = songId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return orderId.equals(order.orderId) && totalPrice.equals(order.totalPrice) && dateOfCreation.equals(order.dateOfCreation) && orderStatus == order.orderStatus && userId.equals(order.userId) && songId.equals(order.songId);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = (int) (31 * result + orderId);
        result = 31 * result + ((totalPrice == null) ? 0 : totalPrice.hashCode());
        result = 31 * result + ((dateOfCreation == null) ? 0 : dateOfCreation.hashCode());
        result = 31 * result + ((orderStatus == null) ? 0 : orderStatus.hashCode());
        result = 31 * result + ((userId == null) ? 0 : userId.hashCode());
        result = 31 * result + ((songId == null) ? 0 : songId.hashCode());
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("orderId=").append(orderId);
        sb.append(", totalPrice=").append(totalPrice);
        sb.append(", dateOfCreation=").append(dateOfCreation);
        sb.append(", orderStatus=").append(orderStatus);
        sb.append(", userId=").append(userId);
        sb.append(", songId=").append(songId);
        sb.append('}');
        return sb.toString();
    }

    enum OrderStatus {
        PAID, NOT_PAID;
    }
}
