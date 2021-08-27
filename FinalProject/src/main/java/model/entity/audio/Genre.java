package model.entity.audio;

import model.entity.AbstractEntity;

public class Genre extends AbstractEntity {

    private String genreName;
    private String informationAboutGenre;

    public Genre(Long id) {
        super(id);
    }

    public Genre(Long id, String name, String informationAboutGenre) {
        super(id);
        this.genreName = name;
        this.informationAboutGenre = informationAboutGenre;
    }

    public Genre(String genreName, String informationAboutGenre) {
        this.genreName = genreName;
        this.informationAboutGenre = informationAboutGenre;
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
        if(o == null || getClass() != o.getClass()){
            return false;
        }
        if (!super.equals(o)) return false;
        Genre genre = (Genre) o;
        return genreName.equals(genre.genreName) &&
                informationAboutGenre.equals(genre.informationAboutGenre);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + ((genreName == null) ? 0 : genreName.hashCode());
        result = 31 * result + ((informationAboutGenre == null) ? 0 : informationAboutGenre.hashCode());
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Genre{");
        sb.append("genreName='").append(genreName).append('\'');
        sb.append(", informationAboutGenre='").append(informationAboutGenre).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
