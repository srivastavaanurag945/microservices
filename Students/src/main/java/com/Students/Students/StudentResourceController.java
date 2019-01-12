package com.Students.Students;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.http.converter.json.MappingJacksonValue;
import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@Path("produceboth")
@RestController
@RequestMapping("/jpa")
@Api(value="StudentStore", description="Operations pertaining Students Information in Online Store")
public class StudentResourceController {
    
	@Autowired
    private StudentDatabaseService studentDatabaseService ;

    @GetMapping("/students")
    @Path("both")
    @ApiOperation(value = "View a list of available Students",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
//    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Resources<List> getAllStudents()
	 {
    	List<StudentV1> studentList = studentDatabaseService.findAll();
       
        if(studentList.size()==0){
        	throw new StudentNotFoundException("Student data is empty-");
        }
        
        Resources<List> resource = new Resources(studentList);
        ControllerLinkBuilder linkBuilder = linkTo(methodOn(this.getClass()).getAllStudents());

        resource.add(linkBuilder.withRel("all-students"));

        return resource;
	 }
    
    @ApiOperation(value = "Search a Student with an ID",response = StudentV1.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @GetMapping("/students/{studentId}")
	 public Resource<StudentV1> getStudent(@PathVariable Long studentId)
	 {
    	StudentV1 student =  studentDatabaseService.findOne(studentId);
        if(student==null){
            throw new StudentNotFoundException("Student not found id-" + studentId);
        }
        Resource<StudentV1> resource = new Resource<>(student);
        ControllerLinkBuilder linkBuilder = linkTo(methodOn(this.getClass()).getAllStudents());

        resource.add(linkBuilder.withRel("current-student"));
        return resource;	 
     }

    @ApiOperation(value = "Add a Student")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @PostMapping("/students")
	 public ResponseEntity<String> saveStudent( @Valid @RequestBody StudentV1 student){
	        
	            StudentV1 createdStudent = studentDatabaseService.save(student);
	            URI uri=null;
	            if(createdStudent!=null) {
	            	uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
	            return ResponseEntity.created(uri).build();
	            }
	            else{
	            	throw new StudentNotFoundException("Student Id - " + student.getStudentId());
	            }
	    }
	 
	 @ApiOperation(value = "Update Student")
	 @ApiResponses(value = {
	            @ApiResponse(code = 200, message = "Successfully retrieved list"),
	            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	    }
	    )
	 @PutMapping(value="/students/{studentId}")
	 public void updateStudent(@PathVariable long studentId, @Valid @RequestBody StudentV1 updatedStudent) {
		 StudentV1 student =  studentDatabaseService.findOne(studentId);
	        if(student!=null){
	            studentDatabaseService.update(studentId,updatedStudent);
	        }
	        else {
	        	throw new StudentNotFoundException("Student Id - " + student.getStudentId());
	        }
	 }
	 
	 @ApiOperation(value = "Delete a Student")
	 @ApiResponses(value = {
	            @ApiResponse(code = 200, message = "Successfully retrieved list"),
	            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	    }
	    )
	 @DeleteMapping(value = "/students/{studentId}")
	 public void deleteStudent(@PathVariable long studentId){
		 studentDatabaseService.deleteById(studentId);
	 }
	
	 
	 @GetMapping("/filteredStudentResponse")
	    @Path("both")
	    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	    @Produces({MediaType.APPLICATION_JSON})
	    @ApiOperation(value = "View a list of available Students with filterd response",response = Iterable.class)
	    @ApiResponses(value = {
	            @ApiResponse(code = 200, message = "Successfully retrieved list"),
	            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	    }
	    )
	 
	 
	 
	    public MappingJacksonValue getAllStudentsWithFilters()
		 {
	    	List<StudentV1> studentList = studentDatabaseService.findAll();
	        if(studentList.size()==0){
	        	throw new StudentNotFoundException("Student data is empty-");
	        }
	    	
	        SimpleFilterProvider filterProvider = new SimpleFilterProvider();
	    	filterProvider.addFilter("studentFilter", SimpleBeanPropertyFilter.serializeAllExcept("studentId")); 
	    	MappingJacksonValue mapping = new MappingJacksonValue(studentList);
	        mapping.setFilters(filterProvider); 
	        return mapping;
		 }
}	 