package no.hvl.dat152.h2databaseexample.repository;

import org.springframework.data.repository.CrudRepository;
import no.hvl.dat152.h2databaseexample.model.Student;

public interface StudentRepository extends CrudRepository<Student, Integer>   {

}
