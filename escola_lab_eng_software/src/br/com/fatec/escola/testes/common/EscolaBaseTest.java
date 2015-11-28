package br.com.fatec.escola.testes.common;

import org.junit.BeforeClass;

import br.com.fatec.escola.core.service.LiquibaseRunnerService;
import br.com.spektro.minispring.core.dbmapper.ConfigDBMapper;
import br.com.spektro.minispring.core.implfinder.ContextSpecifier;

public class EscolaBaseTest {

	@BeforeClass //Executado antes do primeiro teste
	public static void setUp() {
		//A linha seguinte informa o caminho onde devem ser postas as implementa��es
		ContextSpecifier.getInstance().setContext("br.com.fatec.escola.core");
		//O m�todo seguinte recebe como par�metro: database1.name=test
		ConfigDBMapper.getInstance().setDefaultConnectionName("escola_bd_test");
		LiquibaseRunnerService.run();
	}
}
