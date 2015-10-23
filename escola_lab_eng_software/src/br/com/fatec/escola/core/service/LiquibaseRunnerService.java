package br.com.fatec.escola.core.service;

import java.sql.Connection;
import java.sql.DriverManager;

import br.com.spektro.minispring.core.dbmapper.ConfigDBMapper;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;

/**
 * @author carlosolr
 * 
 * @version 1.0.0
 */
public class LiquibaseRunnerService {

	public static void run() {
		//A classe connection tem que ser capaz de informar que uma conex�o deve
		//ser aberta, fechada.
		Connection conn;
		try {
			
			conn = ConfigDBMapper.getInstance().getDefaultConnection();
			
			/* maneira 2:
			conn = DriverManager.getConnection("jdbc:hsqldb:mem:fatec", "SA", ""); //conex�o com banco de dados
			*/
											//("url","login","pass")
											//A url � �nica para o banco de dados: protocolo:host:porta:mem:nome_banco_dados/file:nome_banco_dados criacao em arquivo
			Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(
					new JdbcConnection(conn));
			Liquibase liquibase = new Liquibase("br/com/fatec/escola/liquibase/changelog-master.xml",
					new ClassLoaderResourceAccessor(), database);
			liquibase.forceReleaseLocks();
			liquibase.update("fatec");
			conn.close();
		} catch (Exception e) {
			throw new RuntimeException("Erro na execucao do Liquibase", e);
		}
	}
}
