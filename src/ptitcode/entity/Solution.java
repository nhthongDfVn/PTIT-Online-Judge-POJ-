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
	@Column(name="ExerciseID")
	private int exerciseID;
	
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getExerciseID() {
		return exerciseID;
	}

	public void setExerciseID(int exerciseID) {
		this.exerciseID = exerciseID;
	}
	
	
	
	
}
