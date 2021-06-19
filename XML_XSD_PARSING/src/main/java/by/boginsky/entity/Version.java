package by.boginsky.entity;

public class Version {

    private Certificate certificate;
    private Package infoAboutPackage;
    private Dosage dosage;

    public Version() {
    }

    public Version(Certificate certificate, Package infoOfPackage, Dosage dosage) {
        this.certificate = certificate;
        this.infoAboutPackage = infoOfPackage;
        this.dosage = dosage;
    }

    public Certificate getCertificate() {
        return certificate;
    }

    public void setCertificate(Certificate certificate) {
        this.certificate = certificate;
    }

    public Package getInfoAboutPackage() {
        return infoAboutPackage;
    }

    public void setInfoAboutPackage(Package infoAboutPackage) {
        this.infoAboutPackage = infoAboutPackage;
    }

    public Dosage getDosage() {
        return dosage;
    }

    public void setDosage(Dosage dosage) {
        this.dosage = dosage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Version version = (Version) o;
        return certificate.equals(version.certificate) && infoAboutPackage.equals(version.infoAboutPackage)
                && dosage.equals(version.dosage);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + ((certificate == null) ? 0 : certificate.hashCode());
        result = 31 * result + ((infoAboutPackage == null) ? 0 : infoAboutPackage.hashCode());
        result = 31 * result + ((dosage == null) ? 0 : dosage.hashCode());
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Version{");
        sb.append("certificate=").append(certificate);
        sb.append(", infoOfPackage=").append(infoAboutPackage);
        sb.append(", dosage=").append(dosage);
        sb.append('}');
        return sb.toString();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Version version;

        private Builder() {
            version = new Version();
        }

        public Builder setCertificate (Certificate certificate) {
            version.setCertificate(certificate);
            return this;
        }

        public Builder setInfoAboutPackage(Package infoAboutPackage){
            version.setInfoAboutPackage(infoAboutPackage);
            return this;
        }

        public Builder setDosage(Dosage dosage){
            version.setDosage(dosage);
            return this;
        }

        public Version build() {
            return version;
        }
    }

}
