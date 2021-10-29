package by.boginsky.audiostore.model.entity.audio;

import by.boginsky.audiostore.model.entity.AbstractEntity;

import java.util.List;

/**
 * The type Author.
 */
public class Author extends AbstractEntity {

    private String authorName;
    private String informationAboutAuthor;
    private List<String> listOfGenres;
    private String imageUrl;

    /**
     * Gets author name.
     *
     * @return the author name
     */
    public String getAuthorName() {
        return authorName;
    }

    /**
     * Sets author name.
     *
     * @param authorName the author name
     */
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }


    /**
     * Gets information about author.
     *
     * @return the information about author
     */
    public String getInformationAboutAuthor() {
        return informationAboutAuthor;
    }

    /**
     * Sets information about author.
     *
     * @param informationAboutAuthor the information about author
     */
    public void setInformationAboutAuthor(String informationAboutAuthor) {
        this.informationAboutAuthor = informationAboutAuthor;
    }

    /**
     * Gets list of genres.
     *
     * @return the list of genres
     */
    public List<String> getListOfGenres() {
        return listOfGenres;
    }

    /**
     * Sets list of genres.
     *
     * @param listOfGenres the list of genres
     */
    public void setListOfGenres(List<String> listOfGenres) {
        this.listOfGenres = listOfGenres;
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
        Author author = (Author) o;
        return authorName.equals(author.authorName) &&
                listOfGenres.equals(author.listOfGenres) &&
                imageUrl.equals(author.imageUrl) &&
                informationAboutAuthor.equals(author.informationAboutAuthor);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + ((authorName == null) ? 0 : authorName.hashCode());
        result = 31 * result + ((listOfGenres == null) ? 0 : listOfGenres.hashCode());
        result = 31 * result + ((imageUrl == null) ? 0 : imageUrl.hashCode());
        result = 31 * result + ((informationAboutAuthor == null) ? 0 : informationAboutAuthor.hashCode());
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Author{");
        sb.append("name='").append(authorName).append('\'');
        sb.append(", informationAboutAuthor='").append(informationAboutAuthor).append('\'');
        sb.append(", genreName='").append(listOfGenres).append('\'');
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
        private final Author author;

        /**
         * Instantiates a new Builder.
         */
        public Builder() {
            author = new Author();
        }

        /**
         * Sets id.
         *
         * @param authorId the author id
         * @return the id
         */
        public Builder setId(Long authorId) {
            author.setId(authorId);
            return this;
        }

        /**
         * Sets name.
         *
         * @param name the name
         * @return the name
         */
        public Builder setName(String name) {
            author.setAuthorName(name);
            return this;
        }

        /**
         * Sets list of genres.
         *
         * @param listOfGenres the list of genres
         * @return the list of genres
         */
        public Builder setListOfGenres(List<String> listOfGenres) {
            author.setListOfGenres(listOfGenres);
            return this;
        }

        /**
         * Sets information about author.
         *
         * @param informationAboutAuthor the information about author
         * @return the information about author
         */
        public Builder setInformationAboutAuthor(String informationAboutAuthor) {
            author.setInformationAboutAuthor(informationAboutAuthor);
            return this;
        }

        /**
         * Sets image url.
         *
         * @param imageUrl the image url
         * @return the image url
         */
        public Builder setImageUrl(String imageUrl) {
            author.setImageUrl(imageUrl);
            return this;
        }

        /**
         * Build author.
         *
         * @return the author
         */
        public Author build() {
            return author;
        }
    }
}
