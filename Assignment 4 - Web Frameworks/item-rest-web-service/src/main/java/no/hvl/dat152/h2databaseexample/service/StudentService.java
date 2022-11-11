package no.hvl.dat152.h2databaseexample.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import no.hvl.dat152.h2databaseexample.model.Student;
import no.hvl.dat152.h2databaseexample.repository.StudentRepository;

@Service
public class StudentService {
	@Autowired
	StudentRepository studentRepository;
  
	public List<Student> getAllStudent() {
		List<Student> students = new ArrayList<Student>();
		studentRepository.findAll().forEach(student -> students.add(student)) ;
		return students;
	}

	public Student getStudentById(int id) {
		return studentRepository.findById(id).get();
	}

	public Student saveOrUpdate(Student student) {
		studentRepository.save(student);
		return student;
	}
 
	public void delete(int id) {
		studentRepository.deleteById(id);
	}
}
