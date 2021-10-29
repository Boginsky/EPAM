package by.boginsky.audiostore.model.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * The type Abstract entity.
 */
public abstract class AbstractEntity implements Serializable, Cloneable {

    private Long id;

    /**
     * Instantiates a new Abstract entity.
     */
    public AbstractEntity() {
    }

    /**
     * Instantiates a new Abstract entity.
     *
     * @param id the id
     */
    public AbstractEntity(Long id) {
        this.id = id;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractEntity that = (AbstractEntity) o;
        return Objects.equals(id, that.id);
    }


    @Override
    public int hashCode() {
        int result = 0;
        return 31 * result + ((id == null) ? 0 : id.hashCode());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AbstractEntity{");
        sb.append("id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
