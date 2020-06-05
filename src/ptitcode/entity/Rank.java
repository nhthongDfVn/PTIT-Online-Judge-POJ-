package ptitcode.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="`rank`")
public class Rank {
	@Id
	@Column(name="username")
	private String username;
	
	@Column(name="score")
	private int score;
	
	
	public Rank() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Rank(String username, int score) {
		super();
		this.username = username;
		this.score = score;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	
	
}
