package telran.ashkelon2020.student.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.ashkelon2020.model.Student;
import telran.ashkelon2020.student.dao.StudentRepository;
import telran.ashkelon2020.student.dto.ScoreDto;
import telran.ashkelon2020.student.dto.StudentDto;
import telran.ashkelon2020.student.dto.StudentNotFoundException;
import telran.ashkelon2020.student.dto.StudentResponseDto;
import telran.ashkelon2020.student.dto.StudentUpdateDto;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public boolean addStudent(StudentDto studentDto) {
		// Student student = new Student(studentDto.getId(), studentDto.getName(),
		// studentDto.getPassword());
		Student student = modelMapper.map(studentDto, Student.class);
		return studentRepository.addStudent(student);
	}

	@Override
	public StudentResponseDto findStudent(int id) {
		Student student = studentRepository.findStudentById(id);
		if (student == null) {
			throw new StudentNotFoundException(id);
		} else {
			// return convertStudentToStudentResponseDto(student);
			return modelMapper.map(student, StudentResponseDto.class);
		}

	}

	@Override
	public StudentResponseDto deleteStudent(int id) {
		Student student = studentRepository.deleteStudenById(id);
		if (student == null) {
			throw new StudentNotFoundException(id);
		}
		// return convertStudentToStudentResponseDto(student);
		return modelMapper.map(student, StudentResponseDto.class);

	}

	// private StudentResponseDto convertStudentToStudentResponseDto(Student
	// student) {
	// return StudentResponseDto.builder()
	// .id(student.getId())
	// .name(student.getName())
	// .scores(student.getScores())
	// .build();
	// }

	@Override
	public StudentDto updateStudent(int id, StudentUpdateDto studentUpdateDto) {
		Student student = studentRepository.findStudentById(id);
		if (student == null) {
			throw new StudentNotFoundException(id);
		}
		String name = studentUpdateDto.getName();
		if (name == null) {
			name = student.getName();
		}
		String password = studentUpdateDto.getPassword();
		if (password == null) {
			password = student.getPassword();
		}
		student = studentRepository.updateStudent(id, name, password);
		// return convertStudentToStudentDto(studentRepository.updateStudent(id, name,
		// password));
		return modelMapper.map(student, StudentDto.class);
	}

	// private StudentDto convertStudentToStudentDto(Student student) {
	// return StudentDto.builder()
	// .id(student.getId())
	// .name(student.getName())
	// .password(student.getPassword())
	// .build();
	// }

	@Override
	public boolean addScore(int id, ScoreDto scoreDto) {
		try {
			return studentRepository.addScore(id, scoreDto.getExamName(), scoreDto.getScore());
		} catch (NullPointerException e) {
			throw new StudentNotFoundException(id);
		}
	}

}
