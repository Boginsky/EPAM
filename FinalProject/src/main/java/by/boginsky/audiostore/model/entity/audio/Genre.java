package by.boginsky.audiostore.model.entity.audio;

import by.boginsky.audiostore.model.entity.AbstractEntity;

/**
 * The type Genre.
 */
public class Genre extends AbstractEntity {

    private String genreName;

    /**
     * Gets genre name.
     *
     * @return the genre name
     */
    public String getGenreName() {
        return genreName;
    }

    /**
     * Sets genre name.
     *
     * @param genreName the genre name
     */
    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) return false;
        Genre genre = (Genre) o;
        return genreName.equals(genre.genreName);
    }

    @Override
    public int hashCode() {
        int result = 1;
        return 31 * result + ((genreName == null) ? 0 : genreName.hashCode());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Genre{");
        sb.append("genreName='").append(genreName).append('\'');
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
        private final Genre genre;

        /**
         * Instantiates a new Builder.
         */
        public Builder() {
            genre = new Genre();
        }

        /**
         * Sets id.
         *
         * @param id the id
         * @return the id
         */
        public Builder setId(Long id) {
            genre.setId(id);
            return this;
        }

        /**
         * Sets genre name.
         *
         * @param genreName the genre name
         * @return the genre name
         */
        public Builder setGenreName(String genreName) {
            genre.setGenreName(genreName);
            return this;
        }

        /**
         * Build genre.
         *
         * @return the genre
         */
        public Genre build() {
            return genre;
        }
    }
}
