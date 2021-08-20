package model.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class Song extends Entity {

    private Long songId;
    private String songName;
    private String imageURL;
    private BigDecimal price;
    private Long authorId;
    private Long genreId;
    private Long albumId;
    private Long userId;

    public Song(Long songId, String songName, String imageURL, BigDecimal price, Long authorId, Long genreId, Long albumId, Long userId) {
        this.songId = songId;
        this.songName = songName;
        this.imageURL = imageURL;
        this.price = price;
        this.authorId = authorId;
        this.genreId = genreId;
        this.albumId = albumId;
        this.userId = userId;
    }


    public Long getSongId() {
        return songId;
    }

    public void setSongId(Long songId) {
        this.songId = songId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Song)) return false;
        Song song = (Song) o;
        return songId.equals(song.songId) && songName.equals(song.songName) && imageURL.equals(song.imageURL) && price.equals(song.price) && authorId.equals(song.authorId) && genreId.equals(song.genreId) && albumId.equals(song.albumId) && userId.equals(song.userId);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = (int) (31 * result + songId);
        result = 31 * result + ((songName == null) ? 0 : songName.hashCode());
        result = 31 * result + ((imageURL == null) ? 0 : imageURL.hashCode());
        result = 31 * result + ((price == null) ? 0 : price.hashCode());
        result = 31 * result + ((authorId == null) ? 0 : authorId.hashCode());
        result = 31 * result + ((genreId == null) ? 0 : genreId.hashCode());
        result = 31 * result + ((albumId == null) ? 0 : albumId.hashCode());
        result = 31 * result + ((userId == null) ? 0 : userId.hashCode());
        return result;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Song{");
        sb.append("songId=").append(songId);
        sb.append(", songName='").append(songName).append('\'');
        sb.append(", imageURL='").append(imageURL).append('\'');
        sb.append(", price=").append(price);
        sb.append(", authorId=").append(authorId);
        sb.append(", genreId=").append(genreId);
        sb.append(", albumId=").append(albumId);
        sb.append(", userId=").append(userId);
        sb.append('}');
        return sb.toString();
    }
}
