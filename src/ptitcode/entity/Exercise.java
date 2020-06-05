package ptitcode.entity;



import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="exercise")
public class Exercise {
	@Id
	@GeneratedValue
	@Column(name="exerciseID")
	private int exerciseID;
	
	@Column(name="name")
	private String name;
	
	@Column(name="time")
	private Double time;
	
	@Column(name="type")
	private String type;
	
	@Column(name="memlimit")
	private int memlimit;
	
	@Column(name="detail")
	private String detail;
	
	@Column(name="input")
	private String input;
	
	@Column(name="output")
	private String output;
	
	@Column(name="solve")
	private int solves;
	
	
	//@OneToMany(mappedBy="exercise",fetch=FetchType.EAGER)
	//private Collection<Testcase> testcase;
	

	public int getSolves() {
		return solves;
	}

	public void setSolves(int solves) {
		this.solves = solves;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getTime() {
		return time;
	}

	public void setTime(Double time) {
		this.time = time;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getMemlimit() {
		return memlimit;
	}

	public void setMemlimit(int memlimit) {
		this.memlimit = memlimit;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
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

	public int getExerciseID() {
		return exerciseID;
	}

	public void setExerciseID(int exerciseID) {
		this.exerciseID = exerciseID;
	}

	public Exercise() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Exercise(int exerciseID, String name, Double time, String type, int memlimit, String detail, String input,
			String output) {
		super();
		this.exerciseID = exerciseID;
		this.name = name;
		this.time = time;
		this.type = type;
		this.memlimit = memlimit;
		this.detail = detail;
		this.input = input;
		this.output = output;
	}	
	
	
}
