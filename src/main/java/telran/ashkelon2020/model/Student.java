package telran.ashkelon2020.model;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@Document(collection = "students")
public class Student {
	@Id
	int id;
	@Setter
	String name;
	@Setter
	String password;
	Map<String, Integer> scores = new HashMap<>(); //пришлось сделать инициализацию здесь, 
	                                               //чтобы score не было null при мэппинге
	public Student(int id, String name, String password) {
		this.id = id;
		this.name = name;
		this.password = password;
//		scores = new HashMap<>(); 
	}
	
	public boolean addScore(String exam, Integer score) {
		return scores.put(exam, score)!=null;
	}
	
	

}
