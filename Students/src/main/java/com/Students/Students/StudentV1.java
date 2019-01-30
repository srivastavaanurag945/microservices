package com.Students.Students;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.xml.bind.annotation.XmlRootElement;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;


@XmlRootElement
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@JsonFilter("studentFilter")     /*in case of static filtering comment it*/
public class StudentV1 {
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@JsonProperty("studentId") 
	//@JsonIgnore   /*in case of static filtering comment it*/
    @ApiModelProperty(notes = "The database generated Student ID")
	private Long studentId;
	
	@JsonProperty("studentName")
	@Size(min = 2, message = "name should contain min 2 characters")
	@ApiModelProperty(notes = "The Student Name ")
	private String studentName;
	
	@Past
	@JsonProperty("dateofbirth")
	@ApiModelProperty(notes = "The Student Date OF Birth")
	private Date dateofbirth;	
	
	public StudentV1(String studentName) {
		this.studentName=studentName;
	}

	public StudentV1(@Size(min = 2, message = "name should contain min 2 characters") String studentName,
			@Past Date dateofbirth) {
		super();
		this.studentName = studentName;
		this.dateofbirth = dateofbirth;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public void setDateofbirth(Date dateofbirth) {
		this.dateofbirth = dateofbirth;
	}
	
}