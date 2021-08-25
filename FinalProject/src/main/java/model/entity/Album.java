package model.entity;

import java.sql.Timestamp;

public class Album extends AbstractEntity {

    private Long albumId;
    private String albumName;
    private Timestamp dateOfCreation;
    private String informationAboutAlbum;
    private String imageURL;

    public Album(Long albumId, String albumName, Timestamp dateOfCreation, String informationAboutAlbum, String imageURL) {
        this.albumId = albumId;
        this.albumName = albumName;
        this.dateOfCreation = dateOfCreation;
        this.informationAboutAlbum = informationAboutAlbum;
        this.imageURL = imageURL;
    }

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

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

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Album)) return false;
        Album album = (Album) o;
        return albumId.equals(album.albumId) && albumName.equals(album.albumName) && dateOfCreation.equals(album.dateOfCreation) && informationAboutAlbum.equals(album.informationAboutAlbum) && imageURL.equals(album.imageURL);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = (int) (31 * result + albumId);
        result = 31 * result + ((albumName == null) ? 0 : albumName.hashCode());
        result = 31 * result + ((dateOfCreation == null) ? 0 : dateOfCreation.hashCode());
        result = 31 * result + ((informationAboutAlbum == null) ? 0 : informationAboutAlbum.hashCode());
        result = 31 * result + ((imageURL == null) ? 0 : imageURL.hashCode());
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Album{");
        sb.append("albumId=").append(albumId);
        sb.append(", albumName='").append(albumName).append('\'');
        sb.append(", dateOfCreation=").append(dateOfCreation);
        sb.append(", informationAboutAlbum='").append(informationAboutAlbum).append('\'');
        sb.append(", imageURL='").append(imageURL).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
