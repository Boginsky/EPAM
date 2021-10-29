package by.boginsky.audiostore.model.entity.audio;

import by.boginsky.audiostore.model.entity.AbstractEntity;

import java.math.BigDecimal;

/**
 * The type Song.
 */
public class Song extends AbstractEntity {

    private String songName;
    private String imageUrl;
    private BigDecimal price;
    private String author;
    private String genre;
    private String album;

    /**
     * Gets song name.
     *
     * @return the song name
     */
    public String getSongName() {
        return songName;
    }

    /**
     * Sets song name.
     *
     * @param songName the song name
     */
    public void setSongName(String songName) {
        this.songName = songName;
    }

    /**
     * Gets image url.
     *
     * @return the image url
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Sets image url.
     *
     * @param imageUrl the image url
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Gets author.
     *
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets author.
     *
     * @param author the author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Gets genre.
     *
     * @return the genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Sets genre.
     *
     * @param genre the genre
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * Gets album.
     *
     * @return the album
     */
    public String getAlbum() {
        return album;
    }

    /**
     * Sets album.
     *
     * @param album the album
     */
    public void setAlbum(String album) {
        this.album = album;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) return false;
        Song song = (Song) o;
        return songName.equals(song.songName) &&
                imageUrl.equals(song.imageUrl) &&
                price.equals(song.price) &&
                author.equals(song.author) &&
                genre.equals(song.genre) &&
                album.equals(song.album);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + ((songName == null) ? 0 : songName.hashCode());
        result = 31 * result + ((imageUrl == null) ? 0 : imageUrl.hashCode());
        result = 31 * result + ((price == null) ? 0 : price.hashCode());
        result = 31 * result + ((author == null) ? 0 : author.hashCode());
        result = 31 * result + ((genre == null) ? 0 : genre.hashCode());
        result = 31 * result + ((album == null) ? 0 : album.hashCode());
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Song{");
        sb.append("songName='").append(songName).append('\'');
        sb.append(", imageUrl='").append(imageUrl).append('\'');
        sb.append(", price=").append(price);
        sb.append(", author='").append(author).append('\'');
        sb.append(", genre='").append(genre).append('\'');
        sb.append(", album='").append(album).append('\'');
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
        private final Song song;

        /**
         * Instantiates a new Builder.
         */
        public Builder() {
            song = new Song();
        }

        /**
         * Sets id.
         *
         * @param id the id
         * @return the id
         */
        public Builder setId(Long id) {
            song.setId(id);
            return this;
        }

        /**
         * Sets song name.
         *
         * @param songName the song name
         * @return the song name
         */
        public Builder setSongName(String songName) {
            song.setSongName(songName);
            return this;
        }

        /**
         * Sets image url.
         *
         * @param imageUrl the image url
         * @return the image url
         */
        public Builder setImageUrl(String imageUrl) {
            song.setImageUrl(imageUrl);
            return this;
        }

        /**
         * Sets price.
         *
         * @param price the price
         * @return the price
         */
        public Builder setPrice(BigDecimal price) {
            song.setPrice(price);
            return this;
        }

        /**
         * Sets author.
         *
         * @param author the author
         * @return the author
         */
        public Builder setAuthor(String author) {
            song.setAuthor(author);
            return this;
        }

        /**
         * Sets genre.
         *
         * @param genre the genre
         * @return the genre
         */
        public Builder setGenre(String genre) {
            song.setGenre(genre);
            return this;
        }

        /**
         * Sets album.
         *
         * @param album the album
         * @return the album
         */
        public Builder setAlbum(String album) {
            song.setAlbum(album);
            return this;
        }

        /**
         * Build song.
         *
         * @return the song
         */
        public Song build() {
            return song;
        }
    }
}
