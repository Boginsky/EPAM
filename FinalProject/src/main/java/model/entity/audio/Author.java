package model.entity.audio;

import model.entity.AbstractEntity;

import java.sql.Timestamp;
import java.util.Objects;

public class Author extends AbstractEntity {


    private String firstName;
    private String lastName;
    private String imageURL;
    private String informationAboutAuthor;
    private Timestamp dateOfBirth;

    public Author(Long id) {
        super(id);
    }

    public Author(String firstName, String lastName, String imageURL, String informationAboutAuthor, Timestamp dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.imageURL = imageURL;
        this.informationAboutAuthor = informationAboutAuthor;
        this.dateOfBirth = dateOfBirth;
    }

    public Author(Long authorId, String firstName, String lastName, String imageURL, String informationAboutAuthor, Timestamp dateOfBirth) {
        super(authorId);
        this.firstName = firstName;
        this.lastName = lastName;
        this.imageURL = imageURL;
        this.informationAboutAuthor = informationAboutAuthor;
        this.dateOfBirth = dateOfBirth;
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
        if(o == null || getClass() != o.getClass()){
            return false;
        }
        if (!super.equals(o)) return false;
        Author author = (Author) o;
        return firstName.equals(author.firstName) &&
                lastName.equals(author.lastName) &&
                imageURL.equals(author.imageURL) &&
                informationAboutAuthor.equals(author.informationAboutAuthor) &&
                dateOfBirth.equals(author.dateOfBirth);
    }

    @Override
    public int hashCode() {
        int result = 1;
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
        sb.append("firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", imageURL='").append(imageURL).append('\'');
        sb.append(", informationAboutAuthor='").append(informationAboutAuthor).append('\'');
        sb.append(", dateOfBirth=").append(dateOfBirth);
        sb.append('}');
        return sb.toString();
    }
}
