package telran.ashkelon2020.student.service;

import telran.ashkelon2020.student.dto.ScoreDto;
import telran.ashkelon2020.student.dto.StudentDto;
import telran.ashkelon2020.student.dto.StudentResponceDto;
import telran.ashkelon2020.student.dto.StudentUpdateDto;

public interface StudentService {
	boolean addStudent(StudentDto studentDto);
	StudentResponceDto findStudent(int id);
	StudentResponceDto deleteStudent(int id);
	StudentDto updateStudent(int id, StudentUpdateDto studentUpdateDto);
	boolean addScore(int id, ScoreDto scoreDto);
	
}
