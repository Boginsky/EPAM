package model.entity;

import java.sql.Timestamp;

public class Author extends AbstractEntity {
    private Long authorId;
    private String firstName;
    private String lastName;
    private String imageURL;
    private String informationAboutAuthor;
    private Timestamp dateOfBirth;

    public Author(Long authorId, String firstName, String lastName, String imageURL, String informationAboutAuthor, Timestamp dateOfBirth) {
        this.authorId = authorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.imageURL = imageURL;
        this.informationAboutAuthor = informationAboutAuthor;
        this.dateOfBirth = dateOfBirth;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

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

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getInformationAboutAuthor() {
        return informationAboutAuthor;
    }

    public void setInformationAboutAuthor(String informationAboutAuthor) {
        this.informationAboutAuthor = informationAboutAuthor;
    }

    public Timestamp getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Timestamp dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author)) return false;
        Author author = (Author) o;
        return authorId.equals(author.authorId) && firstName.equals(author.firstName) && lastName.equals(author.lastName) && imageURL.equals(author.imageURL) && informationAboutAuthor.equals(author.informationAboutAuthor) && dateOfBirth.equals(author.dateOfBirth);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = (int) (31 * result + authorId);
        result = 31 * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = 31 * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = 31 * result + ((imageURL == null) ? 0 : imageURL.hashCode());
        result = 31 * result + ((informationAboutAuthor == null) ? 0 : informationAboutAuthor.hashCode());
        result = 31 * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Author{");
        sb.append("authorId=").append(authorId);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", imageURL='").append(imageURL).append('\'');
        sb.append(", informationAboutAuthor='").append(informationAboutAuthor).append('\'');
        sb.append(", dateOfBirth=").append(dateOfBirth);
        sb.append('}');
        return sb.toString();
    }
}
