package telran.ashkelon2020.student.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.ashkelon2020.model.Student;
import telran.ashkelon2020.student.dao.StudentRepositoryMongoDB;
import telran.ashkelon2020.student.dto.ScoreDto;
import telran.ashkelon2020.student.dto.StudentDto;
import telran.ashkelon2020.student.dto.StudentNotFoundException;
import telran.ashkelon2020.student.dto.StudentResponseDto;
import telran.ashkelon2020.student.dto.StudentUpdateDto;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentRepositoryMongoDB studentRepository;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public boolean addStudent(StudentDto studentDto) {
		// Student student = new Student(studentDto.getId(), studentDto.getName(),
		// studentDto.getPassword());
		Student student = modelMapper.map(studentDto, Student.class);
		if (studentRepository.existsById(student.getId())) {
			return false;
		} else {
			studentRepository.save(student);
			return true;
		}
	}

	@Override
	public StudentResponseDto findStudent(int id) {
		Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
		// return convertStudentToStudentResponseDto(student);
		return modelMapper.map(student, StudentResponseDto.class);
	}

	@Override
	public StudentResponseDto deleteStudent(int id) {
		Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
		studentRepository.delete(student);
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
		Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
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

		// return convertStudentToStudentDto(studentRepository.updateStudent(id, name,
		// password));
		student.setName(name);
		student.setPassword(password);
		studentRepository.save(student);
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
		Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
		boolean res = student.addScore(scoreDto.getExamName(), scoreDto.getScore());
		studentRepository.save(student);
		return res;
	}

}
