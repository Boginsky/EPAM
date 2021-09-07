package by.boginsky.audiostore.model.entity.audio;

import by.boginsky.audiostore.model.entity.AbstractEntity;

import java.math.BigDecimal;

public class Song extends AbstractEntity {

    private String songName;
    private String imageUrl;
    private BigDecimal price;
    private Long authorId;
    private Long genreId;
    private Long albumId;

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getGenreId() {
        return genreId;
    }

    public void setGenreId(Long genreId) {
        this.genreId = genreId;
    }

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(o == null || getClass() != o.getClass()){
            return false;
        }
        if (!super.equals(o)) return false;
        Song song = (Song) o;
        return songName.equals(song.songName) &&
                imageUrl.equals(song.imageUrl) &&
                price.equals(song.price) &&
                authorId.equals(song.authorId) &&
                genreId.equals(song.genreId) &&
                albumId.equals(song.albumId);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + ((songName == null) ? 0 : songName.hashCode());
        result = 31 * result + ((imageUrl == null) ? 0 : imageUrl.hashCode());
        result = 31 * result + ((price == null) ? 0 : price.hashCode());
        result = 31 * result + ((authorId == null) ? 0 : authorId.hashCode());
        result = 31 * result + ((genreId== null) ? 0 : genreId.hashCode());
        result = 31 * result + ((albumId == null) ? 0 : albumId.hashCode());
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Song{");
        sb.append("songName='").append(songName).append('\'');
        sb.append(", imageUrl='").append(imageUrl).append('\'');
        sb.append(", price=").append(price);
        sb.append(", authorName='").append(authorId).append('\'');
        sb.append(", genreName='").append(genreId).append('\'');
        sb.append(", albumName='").append(albumId).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {
        private Song song;

        public Builder(){
            song = new Song();
        }

        public Builder setSongName(String songName){
            song.setSongName(songName);
            return this;
        }

        public Builder setImageUrl (String imageUrl){
            song.setImageUrl(imageUrl);
            return this;
        }

        public Builder setPrice (BigDecimal price){
            song.setPrice(price);
            return this;
        }

        public Builder setAuthorId(Long authorId){
            song.setAuthorId(authorId);
            return this;
        }

        public Builder setGenreId(Long genreId){
            song.setGenreId(genreId);
            return this;
        }

        public Builder setAlbumId(Long albumId){
            song.setAlbumId(albumId);
            return this;
        }

        public Song build(){
            return song;
        }
    }
}
