package by.boginsky.model.entity.audio;

import by.boginsky.model.entity.AbstractEntity;

public class Comment extends AbstractEntity {

    private String commentMessage;
    private Long userId;
    private Long songId;

    public String getCommentMessage() {
        return commentMessage;
    }

    public void setCommentMessage(String commentMessage) {
        this.commentMessage = commentMessage;
    }

    public Long getSongId() {
        return songId;
    }

    public void setSongId(Long songId) {
        this.songId = songId;
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
        if(o == null || getClass() != o.getClass()){
            return false;
        }
        if (!super.equals(o)) return false;
        Comment comment = (Comment) o;
        return commentMessage.equals(comment.commentMessage) &&
                songId.equals(comment.songId) &&
                userId.equals(comment.userId);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + ((commentMessage == null) ? 0 : commentMessage.hashCode());
        result = 31 * result + ((songId == null) ? 0 : songId.hashCode());
        result = 31 * result + ((userId == null) ? 0 : userId.hashCode());
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Comment{");
        sb.append("commentMessage='").append(commentMessage).append('\'');
        sb.append(", songId=").append(songId);
        sb.append(", userId=").append(userId);
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

        public Builder setCommentMessage(String commentMessage){
            comment.setCommentMessage(commentMessage);
            return this;
        }

        public Builder setUserId(Long userId){
            comment.setUserId(userId);
            return this;
        }

        public Builder setSongId(Long songId){
            comment.setSongId(songId);
            return this;
        }

        public Comment build(){
            return build();
        }
    }

}
