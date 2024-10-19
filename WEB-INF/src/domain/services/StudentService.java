package domain.services;

import static org.apache.commons.lang3.Validate.*;

import java.util.List;

import domain.aggregate.StudentManager;
import domain.entities.Pc;
import repository.IPcRepository;

public class StudentService {
	private IPcRepository pcRepository;
	private StudentManager studentManager;
	
	public StudentService(IPcRepository pcRepository) {
		notNull(pcRepository);
		this.pcRepository=pcRepository;
		List<Pc> pcList = pcRepository.getPcList(); 
		this.studentManager=new StudentManager(pcList);
	}
	

}
