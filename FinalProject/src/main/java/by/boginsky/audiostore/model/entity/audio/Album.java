package by.boginsky.audiostore.model.entity.audio;

import by.boginsky.audiostore.model.entity.AbstractEntity;

import java.util.List;

public class Album extends AbstractEntity {

    private String albumName;
    private List<String> listOfAuthors;
    private String informationAboutAlbum;
    private String imageUrl;

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public List<String> getListOfAuthors() {
        return listOfAuthors;
    }

    public void setListOfAuthors(List<String> listOfAuthors) {
        this.listOfAuthors = listOfAuthors;
    }

    public String getInformationAboutAlbum() {
        return informationAboutAlbum;
    }

    public void setInformationAboutAlbum(String informationAboutAlbum) {
        this.informationAboutAlbum = informationAboutAlbum;
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

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Album album;

        public Builder() {
            album = new Album();
        }

        public Builder setAlbumName(String albumName) {
            album.setAlbumName(albumName);
            return this;
        }

        public Builder setListOfAuthors(List<String> listOfAuthors){
            album.setListOfAuthors(listOfAuthors);
            return this;
        }

        public Builder setInformationAboutAlbum(String informationAboutAlbum) {
            album.setInformationAboutAlbum(informationAboutAlbum);
            return this;
        }

        public Builder setImageUrl(String imageUrl) {
            album.setImageUrl(imageUrl);
            return this;
        }

        public Builder setAlbumId(Long albumId) {
            album.setId(albumId);
            return this;
        }


        public Album build() {
            return album;
        }
    }
}
