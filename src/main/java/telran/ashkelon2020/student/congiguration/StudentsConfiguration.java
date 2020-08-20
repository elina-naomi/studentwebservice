package telran.ashkelon2020.student.congiguration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentsConfiguration {
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
