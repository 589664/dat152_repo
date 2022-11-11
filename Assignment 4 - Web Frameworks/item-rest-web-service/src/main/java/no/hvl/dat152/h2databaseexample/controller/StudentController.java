package no.hvl.dat152.h2databaseexample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import no.hvl.dat152.h2databaseexample.model.Student;
import no.hvl.dat152.h2databaseexample.service.StudentService;

@RestController
public class StudentController {
	@Autowired
	StudentService studentService;

	@GetMapping("/students")
	private List<Student> getAllStudent() {
		return studentService.getAllStudent();
	}

	@GetMapping("/students/{id}")
	private Student getStudent(@PathVariable("id") int id) {
		return studentService.getStudentById(id);
	}

	@DeleteMapping("/students/{id}")
	private void deleteStudent(@PathVariable("id") int id) {
		studentService.delete(id);
	}

	@PostMapping("/students")
	private int saveStudent(@RequestBody Student student) {
		studentService.saveOrUpdate(student);
		return student.getId();
	}
}
