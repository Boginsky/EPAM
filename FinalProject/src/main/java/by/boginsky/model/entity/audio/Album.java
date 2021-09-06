package by.boginsky.model.entity.audio;

import by.boginsky.model.entity.AbstractEntity;

import java.sql.Timestamp;

public class Album extends AbstractEntity {

    private String albumName;
    private Timestamp dateOfCreation;
    private String informationAboutAlbum;


    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public Timestamp getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Timestamp dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public String getInformationAboutAlbum() {
        return informationAboutAlbum;
    }

    public void setInformationAboutAlbum(String informationAboutAlbum) {
        this.informationAboutAlbum = informationAboutAlbum;
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
                dateOfCreation.equals(album.dateOfCreation) &&
                informationAboutAlbum.equals(album.informationAboutAlbum);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + ((albumName == null) ? 0 : albumName.hashCode());
        result = 31 * result + ((dateOfCreation == null) ? 0 : dateOfCreation.hashCode());
        result = 31 * result + ((informationAboutAlbum == null) ? 0 : informationAboutAlbum.hashCode());
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Album{");
        sb.append("albumName='").append(albumName).append('\'');
        sb.append(", dateOfCreation=").append(dateOfCreation);
        sb.append(", informationAboutAlbum='").append(informationAboutAlbum).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {
        private Album album;

        public Builder() {
            album = new Album();
        }

        public Builder setAlbumName(String albumName){
            album.setAlbumName(albumName);
            return this;
        }

        public Builder setDateOfCreation(Timestamp dateOfCreation){
            album.setDateOfCreation(dateOfCreation);
            return this;
        }

        public Builder setInformationAboutAlbum(String informationAboutAlbum){
            album.setInformationAboutAlbum(informationAboutAlbum);
            return this;
        }

        public Album build(){
            return album;
        }
    }
}
