package by.boginsky.audiostore.model.entity.audio;

import by.boginsky.audiostore.model.entity.AbstractEntity;

import java.util.List;

public class Author extends AbstractEntity {

    private String authorName;
    private String informationAboutAuthor;
    private List<String> listOfGenres;
    private String imageUrl;

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }


    public String getInformationAboutAuthor() {
        return informationAboutAuthor;
    }

    public void setInformationAboutAuthor(String informationAboutAuthor) {
        this.informationAboutAuthor = informationAboutAuthor;
    }

    public List<String> getListOfGenres() {
        return listOfGenres;
    }

    public void setListOfGenres(List<String> listOfGenres) {
        this.listOfGenres = listOfGenres;
    }

    public String getImageUrl() {
        return imageUrl;
    }

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

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Author author;

        public Builder() {
            author = new Author();
        }

        public Builder setId(Long authorId) {
            author.setId(authorId);
            return this;
        }

        public Builder setName(String name) {
            author.setAuthorName(name);
            return this;
        }

        public Builder setListOfGenres(List<String> listOfGenres){
            author.setListOfGenres(listOfGenres);
            return this;
        }

        public Builder setInformationAboutAuthor(String informationAboutAuthor) {
            author.setInformationAboutAuthor(informationAboutAuthor);
            return this;
        }

        public Builder setImageUrl(String imageUrl) {
            author.setImageUrl(imageUrl);
            return this;
        }

        public Author build() {
            return author;
        }
    }
}
