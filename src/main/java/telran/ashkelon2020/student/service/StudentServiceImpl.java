package telran.ashkelon2020.student.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.ashkelon2020.model.Student;
import telran.ashkelon2020.student.dao.StudentRepository;
import telran.ashkelon2020.student.dto.ScoreDto;
import telran.ashkelon2020.student.dto.StudentDto;
import telran.ashkelon2020.student.dto.StudentNotFoundException;
import telran.ashkelon2020.student.dto.StudentResponceDto;
import telran.ashkelon2020.student.dto.StudentUpdateDto;

@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	ModelMapper modelMapper;

	@Override
	public boolean addStudent(StudentDto studentDto) {
		Student student = new Student(studentDto.getId(), studentDto.getName(), studentDto.getPassword());
		return studentRepository.addStudent(student);
	}

	@Override
	public StudentResponceDto findStudent(int id) {
		Student student = studentRepository.findStudenById(id);
		if (student == null) {
			throw new StudentNotFoundException(id);
		} else {
			return StudentResponceDto.builder().id(id).name(student.getName()).scores(student.getScores()).build();
		}
	}

	@Override
	public StudentResponceDto deleteStudent(int id) {
		Student student = studentRepository.deleteStudenById(id);
		if(student==null) {
			throw new StudentNotFoundException(id);
		} else {
			return StudentResponceDto.builder().id(id).name(student.getName()).scores(student.getScores()).build();
		}
	}

	@Override
	public StudentDto updateStudent(int id, StudentUpdateDto studentUpdateDto) {
		if(studentUpdateDto.getName()==null&&studentUpdateDto.getPassword()==null) {
			return null;
		}
		Student student = studentRepository.findStudenById(id);
		if(student==null) {
			throw new StudentNotFoundException(id);
		} else {
			if(studentUpdateDto.getName()!=null) {
				student.setName(studentUpdateDto.getName());
			}
			if(studentUpdateDto.getPassword()!=null) {
				student.setPassword(studentUpdateDto.getPassword());
			}
			return StudentDto.builder().id(id).name(student.getName()).password(student.getPassword()).build();
		}
	}

	@Override
	public boolean addScore(int id, ScoreDto scoreDto) {
//		if(scoreDto.getExamName()==null||scoreDto.getScore()==null) {
//			return false;
//		}
		Student student = studentRepository.findStudenById(id);
//		if(student==null) {
//			return false;
//		} else {
			try {
				return student.addScore(scoreDto.getExamName(), scoreDto.getScore());
			} catch (NullPointerException e) {
				throw new StudentNotFoundException(id);
			}
//		}
	}

}
