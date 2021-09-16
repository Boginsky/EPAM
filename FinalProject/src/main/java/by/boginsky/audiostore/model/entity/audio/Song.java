package by.boginsky.audiostore.model.entity.audio;

import by.boginsky.audiostore.model.entity.AbstractEntity;

import java.math.BigDecimal;

public class Song extends AbstractEntity {

    private String songName;
    private String imageUrl;
    private BigDecimal price;
    private String author;
    private String genre;
    private String album;

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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
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
                author.equals(song.author) &&
                genre.equals(song.genre) &&
                album.equals(song.album);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + ((songName == null) ? 0 : songName.hashCode());
        result = 31 * result + ((imageUrl == null) ? 0 : imageUrl.hashCode());
        result = 31 * result + ((price == null) ? 0 : price.hashCode());
        result = 31 * result + ((author == null) ? 0 : author.hashCode());
        result = 31 * result + ((genre== null) ? 0 : genre.hashCode());
        result = 31 * result + ((album == null) ? 0 : album.hashCode());
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Song{");
        sb.append("songName='").append(songName).append('\'');
        sb.append(", imageUrl='").append(imageUrl).append('\'');
        sb.append(", price=").append(price);
        sb.append(", authorName='").append(author).append('\'');
        sb.append(", genreName='").append(genre).append('\'');
        sb.append(", albumName='").append(album).append('\'');
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

        public Builder setId(Long id){
            song.setId(id);
            return this;
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

        public Builder setAuthor(String author){
            song.setAuthor(author);
            return this;
        }

        public Builder setGenre(String genre){
            song.setGenre(genre);
            return this;
        }

        public Builder setAlbum(String album){
            song.setAlbum(album);
            return this;
        }

        public Song build(){
            return song;
        }
    }
}
