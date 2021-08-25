package model.entity;

import java.util.Objects;

public class Genre extends AbstractEntity {

    Long genreId;
    private String genreName;
    private String informationAboutGenre;

    public Genre(Long id, String name, String informationAboutGenre) {
        this.genreId = id;
        this.genreName = name;
        this.informationAboutGenre = informationAboutGenre;
    }

    public Long getGenreId() {
        return genreId;
    }

    public void setGenreId(Long genreId) {
        this.genreId = genreId;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public String getInformationAboutGenre() {
        return informationAboutGenre;
    }

    public void setInformationAboutGenre(String informationAboutGenre) {
        this.informationAboutGenre = informationAboutGenre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Genre)) return false;
        Genre genre = (Genre) o;
        return Objects.equals(genreId, genre.genreId) && Objects.equals(genreName, genre.genreName) && Objects.equals(informationAboutGenre, genre.informationAboutGenre);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = (int) (31 * result + genreId);
        result = 31 * result + ((genreName == null) ? 0 : genreName.hashCode());
        result = 31 * result + ((informationAboutGenre == null) ? 0 : genreName.hashCode());
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Genre{");
        sb.append("genreId=").append(genreId);
        sb.append(", genreName='").append(genreName).append('\'');
        sb.append(", informationAboutGenre='").append(informationAboutGenre).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
