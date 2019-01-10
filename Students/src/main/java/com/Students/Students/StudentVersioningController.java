package com.Students.Students;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentVersioningController {
	
	/*------------Request Parameter VERSIONING--------------------------*/
	//localhost:8082/VC/students/param?version=1
	@GetMapping(value = "/VC/students/param", params = "version=1")
    public StudentV1 getStudentV1(){
        return new StudentV1("Anurag Srivastava V1");
    }

	//localhost:8082/VC/students/param?version=2
    @GetMapping(value = "/VC/students/param", params = "version=2")
    public StudentV2 getStudentV2(){
        Name name = new Name("Anurag", "Srivastava V2");
        return new StudentV2(name);
    }

    /*------------Header VERSIONING--------------------------*/
    //
    @GetMapping(value = "/VC/students", headers = "X-API-VERSION=1")
    public StudentV1 getHeaderV1(){
        return new StudentV1("Anurag Srivastava V1");
    }

    @GetMapping(value = "/VC/students", headers = "X-API-VERSION=2")
    //
    public StudentV2 getHeaderV2(){
        Name name = new Name("Anurag", "Srivastava H2");
        return new StudentV2(name);
    }
    /*------------Media type VERSIONING--------------------------*/
    @GetMapping(value = "/VC/students/produces", produces = "application/vnd.company.app-v1+json")
    public StudentV1 getContentV1(){
        return new StudentV1("Anurag Srivastava V1");
    }

    @GetMapping(value = "/VC/students/produces", produces = "application/vnd.company.app-v2+json")
    public StudentV2 getContentV2(){
        Name name = new Name("Anurag", "Srivastava V2");
        return new StudentV2(name);
    }
}