package ptitcode.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="exercise")
public class Exercise {
	@Id
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

	
	
}