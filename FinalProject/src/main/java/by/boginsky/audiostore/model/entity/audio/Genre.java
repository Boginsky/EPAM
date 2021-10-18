package by.boginsky.audiostore.model.entity.audio;

import by.boginsky.audiostore.model.entity.AbstractEntity;

public class Genre extends AbstractEntity {

    private String genreName;

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) return false;
        Genre genre = (Genre) o;
        return genreName.equals(genre.genreName);
    }

    @Override
    public int hashCode() {
        int result = 1;
        return  31 * result + ((genreName == null) ? 0 : genreName.hashCode());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Genre{");
        sb.append("genreName='").append(genreName).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {
        private Genre genre;

        public Builder(){
            genre = new Genre();
        }

        public Builder setId(Long id){
            genre.setId(id);
            return this;
        }

        public Builder setGenreName(String genreName){
            genre.setGenreName(genreName);
            return this;
        }

        public Genre build(){
            return genre;
        }
    }
}
