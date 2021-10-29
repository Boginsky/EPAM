package by.boginsky.audiostore.model.entity.audio;

import by.boginsky.audiostore.model.entity.AbstractEntity;

/**
 * The type Comment.
 */
public class Comment extends AbstractEntity {

    private Long albumId;
    private Long userId;
    private String userName;
    private String albumName;
    private String commentMessage;
    private String userImageUrl;

    /**
     * Gets comment message.
     *
     * @return the comment message
     */
    public String getCommentMessage() {
        return commentMessage;
    }

    /**
     * Sets comment message.
     *
     * @param commentMessage the comment message
     */
    public void setCommentMessage(String commentMessage) {
        this.commentMessage = commentMessage;
    }

    /**
     * Gets user name.
     *
     * @return the user name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets user name.
     *
     * @param userName the user name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets album name.
     *
     * @return the album name
     */
    public String getAlbumName() {
        return albumName;
    }

    /**
     * Sets album name.
     *
     * @param albumName the album name
     */
    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    /**
     * Gets user image url.
     *
     * @return the user image url
     */
    public String getUserImageUrl() {
        return userImageUrl;
    }

    /**
     * Sets user image url.
     *
     * @param userImageUrl the user image url
     */
    public void setUserImageUrl(String userImageUrl) {
        this.userImageUrl = userImageUrl;
    }

    /**
     * Gets album id.
     *
     * @return the album id
     */
    public Long getAlbumId() {
        return albumId;
    }

    /**
     * Sets album id.
     *
     * @param albumId the album id
     */
    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
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
                userId.equals(comment.userId) &&
                albumName.equals(comment.albumName) &&
                userImageUrl.equals(comment.userImageUrl);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + ((commentMessage == null) ? 0 : commentMessage.hashCode());
        result = 31 * result + ((userName == null) ? 0 : userName.hashCode());
        result = 31 * result + ((albumId == null) ? 0 : albumId.hashCode());
        result = 31 * result + ((userId == null) ? 0 : userId.hashCode());
        result = 31 * result + ((albumName == null) ? 0 : albumName.hashCode());
        result = 31 * result + ((userImageUrl == null) ? 0 : userImageUrl.hashCode());
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Comment{");
        sb.append("albumId=").append(albumId);
        sb.append(", userId=").append(userId);
        sb.append(", userName='").append(userName).append('\'');
        sb.append(", albumName='").append(albumName).append('\'');
        sb.append(", commentMessage='").append(commentMessage).append('\'');
        sb.append(", userImageUrl='").append(userImageUrl).append('\'');
        sb.append('}');
        return sb.toString();
    }

    /**
     * Builder builder.
     *
     * @return the builder
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * The type Builder.
     */
    public static class Builder {
        private final Comment comment;

        /**
         * Instantiates a new Builder.
         */
        public Builder() {
            comment = new Comment();
        }

        /**
         * Sets id.
         *
         * @param commentId the comment id
         * @return the id
         */
        public Builder setId(Long commentId) {
            comment.setId(commentId);
            return this;
        }

        /**
         * Sets album name.
         *
         * @param albumName the album name
         * @return the album name
         */
        public Builder setAlbumName(String albumName) {
            comment.setAlbumName(albumName);
            return this;
        }

        /**
         * Sets comment message.
         *
         * @param commentMessage the comment message
         * @return the comment message
         */
        public Builder setCommentMessage(String commentMessage) {
            comment.setCommentMessage(commentMessage);
            return this;
        }

        /**
         * Sets album id.
         *
         * @param albumId the album id
         * @return the album id
         */
        public Builder setAlbumId(Long albumId) {
            comment.setAlbumId(albumId);
            return this;
        }

        /**
         * Sets user id.
         *
         * @param userId the user id
         * @return the user id
         */
        public Builder setUserId(Long userId) {
            comment.setUserId(userId);
            return this;
        }

        /**
         * Sets user name.
         *
         * @param userName the user name
         * @return the user name
         */
        public Builder setUserName(String userName) {
            comment.setUserName(userName);
            return this;
        }

        /**
         * Sets user image url.
         *
         * @param userImageUrl the user image url
         * @return the user image url
         */
        public Builder setUserImageUrl(String userImageUrl) {
            comment.setUserImageUrl(userImageUrl);
            return this;
        }

        /**
         * Build comment.
         *
         * @return the comment
         */
        public Comment build() {
            return comment;
        }
    }
}
