package by.boginsky.audiostore.model.entity.audio;

import by.boginsky.audiostore.model.entity.AbstractEntity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Author extends AbstractEntity {

    private String firstName;
    private String lastName;
    private String informationAboutAuthor;
    private String genreName;
    private String imageUrl;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getInformationAboutAuthor() {
        return informationAboutAuthor;
    }

    public void setInformationAboutAuthor(String informationAboutAuthor) {
        this.informationAboutAuthor = informationAboutAuthor;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
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
        if(o == null || getClass() != o.getClass()){
            return false;
        }
        if (!super.equals(o)) return false;
        Author author = (Author) o;
        return firstName.equals(author.firstName) &&
                lastName.equals(author.lastName) &&
                genreName.equals(author.genreName) &&
                imageUrl.equals(author.imageUrl) &&
                informationAboutAuthor.equals(author.informationAboutAuthor);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = 31 * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = 31 * result + ((genreName == null) ? 0 : genreName.hashCode());
        result = 31 * result + ((imageUrl == null) ? 0 : imageUrl.hashCode());
        result = 31 * result + ((informationAboutAuthor == null) ? 0 : informationAboutAuthor.hashCode());
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Author{");
        sb.append("firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", lastName='").append(genreName).append('\'');
        sb.append(", lastName='").append(imageUrl).append('\'');
        sb.append(", informationAboutAuthor='").append(informationAboutAuthor);
        sb.append('}');
        return sb.toString();
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {
        private Author author;

        public Builder(){
            author = new Author();
        }

        public Builder setId(Long authorId){
            author.setId(authorId);
            return this;
        }

        public Builder setFirstName(String firstName){
            author.setFirstName(firstName);
            return this;
        }

        public Builder setLastName(String lastName){
            author.setLastName(lastName);
            return this;
        }

        public Builder setGenreName(String genreName){
            author.setGenreName(genreName);
            return this;
        }

        public Builder setInformationAboutAuthor(String informationAboutAuthor){
            author.setInformationAboutAuthor(informationAboutAuthor);
            return this;
        }

        public Builder setImageUrl(String imageUrl){
            author.setImageUrl(imageUrl);
            return this;
        }

        public Author build(){
            return author;
        }
    }
}
