package telran.ashkelon2020.student.dao;

import telran.ashkelon2020.model.Student;

public interface StudentRepository {
	boolean addStudent(Student student);
	
	Student findStudentById(int id);
	
	Student deleteStudenById(int id);
	
	Student updateStudent(int id, String name, String password);
	
	boolean addScore(int id, String exam, int score);

}
