package by.boginsky.audiostore.model.entity.user;

import by.boginsky.audiostore.model.entity.AbstractEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Order extends AbstractEntity {

    public enum OrderStatus {
        PAID, NOT_PAID, CANCELED;
    }

    private BigDecimal totalPrice;
    private LocalDateTime dateOfCreation;
    private OrderStatus orderStatus;
    private Long userId;
    private List<Long> listOfSongsId;

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

    public List<Long> getListOfSongsId() {
        return listOfSongsId;
    }

    public void setListOfSongsId(List<Long> listOfSongsId) {
        this.listOfSongsId = listOfSongsId;
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
                listOfSongsId.equals(order.listOfSongsId);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + ((totalPrice == null) ? 0 : totalPrice.hashCode());
        result = 31 * result + ((dateOfCreation == null) ? 0 : dateOfCreation.hashCode());
        result = 31 * result + ((orderStatus == null) ? 0 : orderStatus.hashCode());
        result = 31 * result + ((userId == null) ? 0 : userId.hashCode());
        result = 31 * result + ((listOfSongsId == null) ? 0 : listOfSongsId.hashCode());
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("totalPrice=").append(totalPrice);
        sb.append(", dateOfCreation=").append(dateOfCreation);
        sb.append(", orderStatus=").append(orderStatus);
        sb.append(", userId=").append(userId);
        sb.append(", listOfSongsId=").append(listOfSongsId);
        sb.append('}');
        return sb.toString();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Order order;

        public Builder() {
            order = new Order();
        }

        public Builder setId(Long id) {
            order.setId(id);
            return this;
        }

        public Builder setTotalPrice(BigDecimal totalPrice) {
            order.setTotalPrice(totalPrice);
            return this;
        }

        public Builder setDateOfCreation(LocalDateTime dateOfCreation) {
            order.setDateOfCreation(dateOfCreation);
            return this;
        }

        public Builder setOrderStatus(OrderStatus orderStatus) {
            order.setOrderStatus(orderStatus);
            return this;
        }

        public Builder setUserId(Long id) {
            order.setUserId(id);
            return this;
        }

        public Builder setSongs(List<Long> listOfSongsId) {
            order.setListOfSongsId(listOfSongsId);
            return this;
        }

        public Order build() {
            return order;
        }
    }
}
