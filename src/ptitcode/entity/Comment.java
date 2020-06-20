package ptitcode.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="`comment`")
public class Comment implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="commentID")
	private int commentID;
	@Column(name="username")
	private String username;
	@Column(name="comment")
	private String comment;
	@Column(name="postID")
	private int PostID;
	
	
	public Comment(){
		super();
	}

	
	

	public Comment(int commentID, String username, String comment) {
		super();
		this.commentID = commentID;
		this.username = username;
		this.comment = comment;
	}




	public int getPostID() {
		return PostID;
	}




	public void setPostID(int postID) {
		PostID = postID;
	}




	public int getCommentID() {
		return commentID;
	}


	public void setCommentID(int commentID) {
		this.commentID = commentID;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}

	
	
	
}
