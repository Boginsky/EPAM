package by.boginsky.audiostore.model.entity.user;

import by.boginsky.audiostore.model.entity.AbstractEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * The type Order.
 */
public class Order extends AbstractEntity {

    private BigDecimal totalPrice;
    private LocalDateTime dateOfCreation;
    private Long userId;
    private List<Long> listOfSongsId;

    /**
     * Gets total price.
     *
     * @return the total price
     */
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    /**
     * Sets total price.
     *
     * @param totalPrice the total price
     */
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * Gets date of creation.
     *
     * @return the date of creation
     */
    public LocalDateTime getDateOfCreation() {
        return dateOfCreation;
    }

    /**
     * Sets date of creation.
     *
     * @param dateOfCreation the date of creation
     */
    public void setDateOfCreation(LocalDateTime dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * Gets list of songs id.
     *
     * @return the list of songs id
     */
    public List<Long> getListOfSongsId() {
        return listOfSongsId;
    }

    /**
     * Sets list of songs id.
     *
     * @param listOfSongsId the list of songs id
     */
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
                userId.equals(order.userId) &&
                listOfSongsId.equals(order.listOfSongsId);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + ((totalPrice == null) ? 0 : totalPrice.hashCode());
        result = 31 * result + ((dateOfCreation == null) ? 0 : dateOfCreation.hashCode());
        result = 31 * result + ((userId == null) ? 0 : userId.hashCode());
        result = 31 * result + ((listOfSongsId == null) ? 0 : listOfSongsId.hashCode());
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("totalPrice=").append(totalPrice);
        sb.append(", dateOfCreation=").append(dateOfCreation);
        sb.append(", userId=").append(userId);
        sb.append(", listOfSongsId=").append(listOfSongsId);
        sb.append('}');
        return sb.toString();
    }

    /**
     * Builder builder.
     *
     * @return the builder
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * The type Builder.
     */
    public static class Builder {
        private final Order order;

        /**
         * Instantiates a new Builder.
         */
        public Builder() {
            order = new Order();
        }

        /**
         * Sets id.
         *
         * @param id the id
         * @return the id
         */
        public Builder setId(Long id) {
            order.setId(id);
            return this;
        }

        /**
         * Sets total price.
         *
         * @param totalPrice the total price
         * @return the total price
         */
        public Builder setTotalPrice(BigDecimal totalPrice) {
            order.setTotalPrice(totalPrice);
            return this;
        }

        /**
         * Sets date of creation.
         *
         * @param dateOfCreation the date of creation
         * @return the date of creation
         */
        public Builder setDateOfCreation(LocalDateTime dateOfCreation) {
            order.setDateOfCreation(dateOfCreation);
            return this;
        }

        /**
         * Sets user id.
         *
         * @param id the id
         * @return the user id
         */
        public Builder setUserId(Long id) {
            order.setUserId(id);
            return this;
        }

        /**
         * Sets songs.
         *
         * @param listOfSongsId the list of songs id
         * @return the songs
         */
        public Builder setSongs(List<Long> listOfSongsId) {
            order.setListOfSongsId(listOfSongsId);
            return this;
        }

        /**
         * Build order.
         *
         * @return the order
         */
        public Order build() {
            return order;
        }
    }
}
