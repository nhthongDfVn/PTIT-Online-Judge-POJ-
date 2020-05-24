package ptitcode.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="submit")
public class Submit {
	@Id
	@Column(name="submitID")
	private int submitID;
	
	@Column(name="exerciseID")
	private int exerciseID;
	
	@Column(name="username")
	private String username;
	
	@Column(name="answer")
	private int answer;
	
	@Column(name="timerun")
	private int timerun;
	
	@Column(name="timesubmit")
	private String timesubmit ;
	
	@Column(name="memory")
	private int memory;

	public int getSubmitID() {
		return submitID;
	}

	public void setSubmitID(int submitID) {
		this.submitID = submitID;
	}

	public int getExerciseID() {
		return exerciseID;
	}

	public void setExerciseID(int exerciseID) {
		this.exerciseID = exerciseID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getAnswer() {
		return answer;
	}

	public void setAnswer(int answer) {
		this.answer = answer;
	}

	public int getTimerun() {
		return timerun;
	}

	public void setTimerun(int timerun) {
		this.timerun = timerun;
	}

	public String getTimesubmit() {
		return timesubmit;
	}

	public void setTimesubmit(String timesubmit) {
		this.timesubmit = timesubmit;
	}

	public int getMemory() {
		return memory;
	}

	public void setMemory(int memory) {
		this.memory = memory;
	}
}
