package br.com.fatec.escola.testes.dao;

import static org.junit.Assert.*;

import java.util.List;

//import junit.framework.Assert;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.fatec.escola.api.dao.UserDAO;
import br.com.fatec.escola.api.entity.Discipline;
import br.com.fatec.escola.api.entity.Module;
import br.com.fatec.escola.api.entity.User;
import br.com.fatec.escola.core.dao.DisciplineDAOImpl;
import br.com.fatec.escola.core.dao.ModuleDAOImpl;
import br.com.fatec.escola.testes.common.EscolaBaseTest;
import br.com.spektro.minispring.core.implfinder.ImplementationFinder;

public class DisciplineDAOImplTest extends EscolaBaseTest {

	private DisciplineDAOImpl dao;
	
	@Before
	public void config()
	{
		this.dao = (DisciplineDAOImpl) ImplementationFinder.getinstance().getImpl(DisciplineDAOImpl.class);
	}
	
	@Test
	public void testSave() {
		ModuleDAOImpl mDAO = new ModuleDAOImpl();
		Discipline d = new Discipline();
		Module m = new Module();
		m.setId(1l);
		m.setCourse(null);
		d.setModule(null);
	}
}
