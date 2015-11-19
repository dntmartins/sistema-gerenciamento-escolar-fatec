package br.com.fatec.escola.testes.dao;

import static org.junit.Assert.*;

import java.util.List;

//import junit.framework.Assert;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.fatec.escola.api.dao.ClassRoomDAO;
import br.com.fatec.escola.api.dao.UserDAO;
import br.com.fatec.escola.api.entity.ClassRoom;
import br.com.fatec.escola.api.entity.Discipline;
import br.com.fatec.escola.api.entity.Module;
import br.com.fatec.escola.api.entity.User;
import br.com.fatec.escola.core.dao.DisciplineDAOImpl;
import br.com.fatec.escola.core.dao.ModuleDAOImpl;
import br.com.fatec.escola.testes.common.EscolaBaseTest;
import br.com.spektro.minispring.core.implfinder.ImplementationFinder;

public class ClassRoomDAOImplTest extends EscolaBaseTest {

	private ClassRoomDAO dao;
	
	@Before
	public void config()
	{
		this.dao = (ClassRoomDAO) ImplementationFinder.getinstance().getImpl(ClassRoomDAO.class);
	}
	
	@Test
	public void testSave() {
		Discipline discipline = new Discipline();
		Module module = new Module();
		ClassRoom classroom = new ClassRoom();
		discipline.setName("Matematica");
		DisciplineDAOImpl disciplineDAO = new DisciplineDAOImpl();
		discipline = disciplineDAO.save(discipline);
		classroom.setName("402");
		classroom.setModule(1l);
		classroom.setDiscipline(discipline.getId());
		this.dao.save(classroom);
	}
}
