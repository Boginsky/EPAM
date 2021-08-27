package model.entity.audio;

import model.entity.AbstractEntity;

import java.util.Objects;

public class Comment extends AbstractEntity {

    private String commentMessage;
    private Long songId;
    private Long userId;

    public Comment(Long id) {
        super(id);
    }

    public Comment(String commentMessage, Long songId, Long userId) {
        this.commentMessage = commentMessage;
        this.songId = songId;
        this.userId = userId;
    }

    public Comment(Long commentId, String commentMessage, Long songId, Long userId) {
        super(commentId);
        this.commentMessage = commentMessage;
        this.songId = songId;
        this.userId = userId;
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
}
