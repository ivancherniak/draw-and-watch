package model;

public class Comment {
	private long commentId;
	private long pictureId;
	private String login;
	private String name;
	private String commentData;

	public Comment(long commentId, long pictureId, String login, String name, String commentData) {
		this.commentId = commentId;
		this.pictureId = pictureId;
		this.login = login;
		this.name = name;
		this.commentData = commentData;
	}

	public long getCommentId() {
		return commentId;
	}

	public long getPictureId() {
		return pictureId;
	}

	public String getLogin() {
		return login;
	}

	public String getName() {
		return name;
	}

	public String getCommentData() {
		return commentData;
	}
}
