package br.com.fatec.escola.testes;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;

public class ConnectionTest {

	@Test 
	public void blah()
	{
		//LiquibaseRunnerService.run();
	}
	@Test
	public void testeClasseConexao()
	{
		Connection conexao;
		try {
			conexao = DriverManager.getConnection("jdbc:hsqldb:mem:fatec", "SA", ""); //conex�o com banco de dados
											//("url","login","pass")
											//A url � �nica para o banco de dados: protocolo:host:porta:mem:nome_banco_dados/file:nome_banco_dados criacao em arquivo
			//Execu��o do liquibase
			//O acoplamento entre o hsqldb e o liquibase � feito
			//pelo nome da base, que para este caso � fatec
			
			Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(
					new JdbcConnection(conexao));
			Liquibase liquibase = new Liquibase("br/com/fatec/escola/liquibase/changelog-master.xml",
					new ClassLoaderResourceAccessor(), database);
			liquibase.forceReleaseLocks();
			liquibase.update("fatec");
						
			//Alternativamente:
			//LiquibaseRunnerService.run();
			
			//PreparedStatement statement = conexao.prepareStatement("SQL"); 
			PreparedStatement insertStatement = conexao.prepareStatement("INSERT INTO USER VALUES(?,?,?,?)"); 
			PreparedStatement selectStatement = conexao.prepareStatement("SELECT * FROM USER");
			
			/* Inserindo valores na tabela */
			insertStatement.setLong(1, 2); // (<posicao come�ando em um>, <valor>)//Protege-se com rela��o ao tipo de vari�vel a ser escrito
			insertStatement.setString(2, "dante.alemao");
			insertStatement.setString(3, "senha123");
			insertStatement.setString(4, "Dante Martins");
			//insertStatement.getResultSet();
			insertStatement.execute();
			
			/* Retornando valores inseridos */
			// selectStatement.execute(); //Forma01
			
			//ResultSet resultado = selectStatement.getResultSet(); //Forma01
			ResultSet resultado = selectStatement.executeQuery(); //Forma02
			
			while(resultado.next())
			{
				System.out.print("\nID: "+resultado.getString(1));
				System.out.print("\nLogin: "+resultado.getString(2));
				System.out.print("\nSenha: "+resultado.getString(3));
				System.out.print("\nNome: "+resultado.getString(4));
				
			}
			//statement.getResultSet();
			//statement.execute();
			conexao.close();
		} catch (Exception e) {
			throw new RuntimeException("Erro na execução do Liquibase", e);
		}
		
	}
}
