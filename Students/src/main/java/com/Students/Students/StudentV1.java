package com.Students.Students;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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


@XmlRootElement
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonFilter("studentFilter")     /*in case of static filtering comment it*/
public class StudentV1 {
	
	@NotNull
	@GeneratedValue
	@Id
	@JsonProperty("studentId") 
	//@JsonIgnore   /*in case of static filtering comment it*/
	private Long studentId;
	
	@JsonProperty("studentName")
	@Size(min = 2, message = "name should contain min 2 characters")
	private String studentName;
	
	@Past
	@JsonProperty("dateofbirth")
	private Date dateofbirth;	
	
	public StudentV1(String studentName) {
		this.studentName=studentName;
	}
	
}