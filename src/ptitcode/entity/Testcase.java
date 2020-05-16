package ptitcode.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="testcase")
public class Testcase {

	@Id
	@GeneratedValue
	@Column(name="testcaseID")
	private int testcaseID;
	
	@Column(name="numtest")
	private int numtest;
	
	@Column(name="input")
	private String input;
	
	@Column(name="output")
	private String output;

	
	@ManyToOne
	@JoinColumn(name="ExerciseID")
	private Exercise exercise;


	public int getTestcaseID() {
		return testcaseID;
	}


	public void setTestcaseID(int testcaseID) {
		this.testcaseID = testcaseID;
	}


	public int getNumtest() {
		return numtest;
	}


	public void setNumtest(int numtest) {
		this.numtest = numtest;
	}


	public String getInput() {
		return input;
	}


	public void setInput(String input) {
		this.input = input;
	}


	public String getOutput() {
		return output;
	}


	public void setOutput(String output) {
		this.output = output;
	}


	public Exercise getExercise() {
		return exercise;
	}


	public void setExercise(Exercise exercise) {
		this.exercise = exercise;
	}

	
}
