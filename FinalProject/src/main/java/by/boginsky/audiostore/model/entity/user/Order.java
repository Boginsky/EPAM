package by.boginsky.audiostore.model.entity.user;

import by.boginsky.audiostore.model.entity.AbstractEntity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Order extends AbstractEntity {

    public enum OrderStatus {
        PAID, NOT_PAID, CANCELED;
    }

    private BigDecimal totalPrice;
    private LocalDateTime dateOfCreation;
    private OrderStatus orderStatus;
    private Long userId;
    private Long songId;

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(LocalDateTime dateOfCreation) {
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
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) return false;
        Order order = (Order) o;
        return totalPrice.equals(order.totalPrice) &&
                dateOfCreation.equals(order.dateOfCreation) &&
                orderStatus == order.orderStatus &&
                userId.equals(order.userId) &&
                songId.equals(order.songId);
    }

    @Override
    public int hashCode() {
        int result = 1;
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
        sb.append("totalPrice=").append(totalPrice);
        sb.append(", dateOfCreation=").append(dateOfCreation);
        sb.append(", orderStatus=").append(orderStatus);
        sb.append(", userId=").append(userId);
        sb.append(", songId=").append(songId);
        sb.append('}');
        return sb.toString();
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {
        private Order order;

        public Builder() {
            order = new Order();
        }

        public Builder setTotalPrice(BigDecimal totalPrice){
            order.setTotalPrice(totalPrice);
            return this;
        }

        public  Builder setDateOfCreation(LocalDateTime dateOfCreation){
            order.setDateOfCreation(dateOfCreation);
            return this;
        }

        public Builder setOrderStatus(OrderStatus orderStatus){
            order.setOrderStatus(orderStatus);
            return this;
        }

        public Builder setUserId(Long userId){
            order.setUserId(userId);
            return this;
        }

        public Builder setSongId(Long songId){
            order.setSongId(songId);
            return this;
        }

        public Order build(){
            return order;
        }
    }
}
