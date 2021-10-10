package by.boginsky.audiostore.model.entity.audio;

import by.boginsky.audiostore.model.entity.AbstractEntity;

public class Comment extends AbstractEntity {

    private Long albumId;
    private String userName;
    private String albumName;
    private String commentMessage;
    private String userImageUrl;

    public String getCommentMessage() {
        return commentMessage;
    }

    public void setCommentMessage(String commentMessage) {
        this.commentMessage = commentMessage;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getUserImageUrl() {
        return userImageUrl;
    }

    public void setUserImageUrl(String userImageUrl) {
        this.userImageUrl = userImageUrl;
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
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) return false;
        Comment comment = (Comment) o;
        return commentMessage.equals(comment.commentMessage) &&
                userName.equals(comment.userName) &&
                albumId.equals(comment.albumId) &&
                albumName.equals(comment.albumName) &&
                userImageUrl.equals(comment.userImageUrl);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + ((commentMessage == null) ? 0 : commentMessage.hashCode());
        result = 31 * result + ((userName == null) ? 0 : userName.hashCode());
        result = 31 * result + ((albumId == null) ? 0 : albumId.hashCode());
        result = 31 * result + ((albumName == null) ? 0 : albumName.hashCode());
        result = 31 * result + ((userImageUrl == null) ? 0 : userImageUrl.hashCode());
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Comment{");
        sb.append("userName=").append(userName);
        sb.append(", albumId=").append(albumId);
        sb.append(", albumName='").append(albumName).append('\'');
        sb.append(", commentMessage='").append(commentMessage).append('\'');
        sb.append(", userImageUrl='").append(userImageUrl).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Comment comment;

        public Builder() {
            comment = new Comment();
        }

        public Builder setId(Long commentId){
            comment.setId(commentId);
            return this;
        }

        public Builder setAlbumName(String albumName){
            comment.setAlbumName(albumName);
            return this;
        }

        public Builder setCommentMessage(String commentMessage) {
            comment.setCommentMessage(commentMessage);
            return this;
        }

        public Builder setAlbumId(Long albumId){
            comment.setAlbumId(albumId);
            return this;
        }

        public Builder setUserName(String userName){
            comment.setUserName(userName);
            return this;
        }

        public Builder setUserImageUrl(String userImageUrl) {
            comment.setUserImageUrl(userImageUrl);
            return this;
        }

        public Comment build() {
            return comment;
        }
    }
}
