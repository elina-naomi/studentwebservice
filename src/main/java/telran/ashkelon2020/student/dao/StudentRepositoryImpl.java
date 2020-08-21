package telran.ashkelon2020.student.dao;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import telran.ashkelon2020.model.Student;

@Repository
public class StudentRepositoryImpl implements StudentRepository {
	
	Map<Integer, Student> students = new ConcurrentHashMap<>();

	@Override
	public boolean addStudent(Student student) {
		return students.putIfAbsent(student.getId(), student)==null;
	}

	@Override
	public Student findStudentById(int id) {
		return students.get(id);
	}

	@Override
	public Student deleteStudenById(int id) {
		return students.remove(id);
	}

	@Override
	public Student updateStudent(int id, String name, String password) {
		Student student = students.get(id);
		student.setName(name);
		student.setPassword(password);
		return student;
	}

	@Override
	public boolean addScore(int id, String exam, int score) {
		
		return students.get(id).addScore(exam, score);
	}

}
