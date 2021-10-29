package by.boginsky.audiostore.model.entity.audio;

import by.boginsky.audiostore.model.entity.AbstractEntity;

import java.util.List;

/**
 * The type Album.
 */
public class Album extends AbstractEntity {

    private String albumName;
    private List<String> listOfAuthors;
    private String informationAboutAlbum;
    private String imageUrl;

    /**
     * Gets album name.
     *
     * @return the album name
     */
    public String getAlbumName() {
        return albumName;
    }

    /**
     * Sets album name.
     *
     * @param albumName the album name
     */
    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    /**
     * Gets list of authors.
     *
     * @return the list of authors
     */
    public List<String> getListOfAuthors() {
        return listOfAuthors;
    }

    /**
     * Sets list of authors.
     *
     * @param listOfAuthors the list of authors
     */
    public void setListOfAuthors(List<String> listOfAuthors) {
        this.listOfAuthors = listOfAuthors;
    }

    /**
     * Gets information about album.
     *
     * @return the information about album
     */
    public String getInformationAboutAlbum() {
        return informationAboutAlbum;
    }

    /**
     * Sets information about album.
     *
     * @param informationAboutAlbum the information about album
     */
    public void setInformationAboutAlbum(String informationAboutAlbum) {
        this.informationAboutAlbum = informationAboutAlbum;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) return false;
        Album album = (Album) o;
        return albumName.equals(album.albumName) &&
                listOfAuthors.equals(album.listOfAuthors) &&
                imageUrl.equals(album.imageUrl) &&
                informationAboutAlbum.equals(album.informationAboutAlbum);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + ((albumName == null) ? 0 : albumName.hashCode());
        result = 31 * result + ((imageUrl == null) ? 0 : imageUrl.hashCode());
        result = 31 * result + ((listOfAuthors == null) ? 0 : listOfAuthors.hashCode());
        result = 31 * result + ((informationAboutAlbum == null) ? 0 : informationAboutAlbum.hashCode());
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Album{");
        sb.append("albumName='").append(albumName).append('\'');
        sb.append(", listOfAuthors='").append(listOfAuthors).append('\'');
        sb.append(", informationAboutAlbum='").append(informationAboutAlbum).append('\'');
        sb.append(", imageUrl='").append(imageUrl).append('\'');
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
        private final Album album;

        /**
         * Instantiates a new Builder.
         */
        public Builder() {
            album = new Album();
        }

        /**
         * Sets album name.
         *
         * @param albumName the album name
         * @return the album name
         */
        public Builder setAlbumName(String albumName) {
            album.setAlbumName(albumName);
            return this;
        }

        /**
         * Sets list of authors.
         *
         * @param listOfAuthors the list of authors
         * @return the list of authors
         */
        public Builder setListOfAuthors(List<String> listOfAuthors) {
            album.setListOfAuthors(listOfAuthors);
            return this;
        }

        /**
         * Sets information about album.
         *
         * @param informationAboutAlbum the information about album
         * @return the information about album
         */
        public Builder setInformationAboutAlbum(String informationAboutAlbum) {
            album.setInformationAboutAlbum(informationAboutAlbum);
            return this;
        }

        /**
         * Sets image url.
         *
         * @param imageUrl the image url
         * @return the image url
         */
        public Builder setImageUrl(String imageUrl) {
            album.setImageUrl(imageUrl);
            return this;
        }

        /**
         * Sets album id.
         *
         * @param albumId the album id
         * @return the album id
         */
        public Builder setAlbumId(Long albumId) {
            album.setId(albumId);
            return this;
        }


        /**
         * Build album.
         *
         * @return the album
         */
        public Album build() {
            return album;
        }
    }
}
