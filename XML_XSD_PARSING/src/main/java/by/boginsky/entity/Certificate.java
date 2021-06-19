package by.boginsky.entity;

import java.time.LocalDate;

public class Certificate {

    private LocalDate dateOfIssue;
    private String id;
    private String countryOfRegistration;

    public Certificate() {
    }

    public Certificate(LocalDate dateOfIssue, String id, String countryOfRegistration) {
        this.dateOfIssue = dateOfIssue;
        this.id = id;
        this.countryOfRegistration = countryOfRegistration;
    }

    public LocalDate getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(LocalDate dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCountryOfRegistration() {
        return countryOfRegistration;
    }

    public void setCountryOfRegistration(String countryOfRegistration) {
        this.countryOfRegistration = countryOfRegistration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Certificate certificate = (Certificate) o;

        return dateOfIssue.equals(certificate.dateOfIssue) && id.equals(certificate.id)
                && countryOfRegistration.equals(certificate.countryOfRegistration);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + ((dateOfIssue == null) ? 0 : dateOfIssue.hashCode());
        result = 31 * result + ((id == null) ? 0 : id.hashCode());
        result = 31 * result + ((countryOfRegistration == null) ? 0 : countryOfRegistration.hashCode());
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Certificate{");
        sb.append("dateOfIssue=").append(dateOfIssue);
        sb.append(", id='").append(id).append('\'');
        sb.append(", countryOfRegistration='").append(countryOfRegistration).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Certificate certificate;

        private Builder() {
            certificate = new Certificate();
        }

        public Builder dateOfIssueSet(LocalDate dateOfIssue) {
            certificate.setDateOfIssue(dateOfIssue);
            return this;
        }

        public Builder idSet(String id) {
            certificate.setId(id);
            return this;
        }

        public Builder countryOfRegistrationSet(String countryOfRegistration) {
            certificate.setCountryOfRegistration(countryOfRegistration);
            return this;
        }

        public Certificate build() {
            return certificate;
        }
    }
}
