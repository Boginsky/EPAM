package model.entity;

public class Comment extends AbstractEntity {

    private Long commentId;
    private String commentMessage;
    private Long songId;
    private Long userId;

    public Comment(Long commentId, String commentMessage, Long songId, Long userId) {
        this.commentId = commentId;
        this.commentMessage = commentMessage;
        this.songId = songId;
        this.userId = userId;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

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
        if (!(o instanceof Comment)) return false;
        Comment comment = (Comment) o;
        return commentId.equals(comment.commentId) && commentMessage.equals(comment.commentMessage) && songId.equals(comment.songId) && userId.equals(comment.userId);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = (int) (31 * result + commentId);
        result = 31 * result + ((commentMessage == null) ? 0 : commentMessage.hashCode());
        result = 31 * result + ((songId == null) ? 0 : songId.hashCode());
        result = 31 * result + ((userId == null) ? 0 : userId.hashCode());
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Comment{");
        sb.append("commentId=").append(commentId);
        sb.append(", commentMessage='").append(commentMessage).append('\'');
        sb.append(", songId=").append(songId);
        sb.append(", userId=").append(userId);
        sb.append('}');
        return sb.toString();
    }
}
