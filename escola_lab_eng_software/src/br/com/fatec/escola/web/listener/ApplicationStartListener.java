package br.com.fatec.escola.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import br.com.spektro.minispring.core.dbmapper.ConfigDBMapper;
import br.com.spektro.minispring.core.implfinder.ContextSpecifier;
import br.com.spektro.minispring.core.liquibaseRunner.LiquibaseRunnerService;

/**
 * @author Dante
 * 
 * @version
 */

//Listener chamado quando o servidor inicia, dessa maneira é possível preparar o banco de dados para a aplicação utilizar
public class ApplicationStartListener implements ServletContextListener {
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		ContextSpecifier.setContext("br.com.fatec.escola");
		ConfigDBMapper.setDefaultConnectionName("escola_bd_test");
		LiquibaseRunnerService.run();
	}
}
