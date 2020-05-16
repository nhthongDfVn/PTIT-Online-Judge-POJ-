package ptitcode.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Solution")
public class Solution {
	@Id
	@GeneratedValue
	@Column(name="SolutionID")
	private int solutionID;
	
	@ManyToOne
	@JoinColumn(name="ExerciseID")
	private Exercise exercise1;
	
	@Column(name="code")
	private String code;
	
	
	@Column(name="Language")
	private String Language;

	public String getLanguage() {
		return Language;
	}

	public void setLanguage(String language) {
		Language = language;
	}

	public int getSolutionID() {
		return solutionID;
	}

	public void setSolutionID(int solutionID) {
		this.solutionID = solutionID;
	}

	public Exercise getExercise() {
		return exercise1;
	}

	public void setExercise(Exercise exercise) {
		this.exercise1 = exercise;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
}
