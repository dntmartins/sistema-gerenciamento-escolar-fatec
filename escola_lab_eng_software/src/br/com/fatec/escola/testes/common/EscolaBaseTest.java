package br.com.fatec.escola.testes.common;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.DbUtils;
import org.junit.After;
import org.junit.BeforeClass;

import br.com.fatec.escola.api.entity.ClassRoom;
import br.com.fatec.escola.api.entity.Course;
import br.com.fatec.escola.api.entity.Discipline;
import br.com.fatec.escola.api.entity.Module;
import br.com.fatec.escola.api.entity.Role;
import br.com.fatec.escola.api.entity.StudentClassRoom;
import br.com.fatec.escola.api.entity.Teacher;
import br.com.fatec.escola.api.entity.User;
import br.com.fatec.escola.core.service.LiquibaseRunnerService;
import br.com.spektro.minispring.core.dbmapper.ConfigDBMapper;
import br.com.spektro.minispring.core.implfinder.ContextSpecifier;

public class EscolaBaseTest {

	@BeforeClass //Executado antes do primeiro teste, realiza conexão com liquibase para todos os testes
	public static void setUp() {
		ContextSpecifier.setContext("br.com.fatec.escola");
		ConfigDBMapper.setDefaultConnectionName("escola_bd_test");
		LiquibaseRunnerService.run();
	}
	/*
	@After
	public void setDown() throws SQLException {
		Connection defaultConnection = ConfigDBMapper.getDefaultConnection();
		defaultConnection.prepareStatement("DELETE FROM " + Discipline.TABLE_NAME).execute();
		defaultConnection.prepareStatement("DELETE FROM " + Course.TABLE_NAME).execute();
		defaultConnection.prepareStatement("DELETE FROM " + ClassRoom.TABLE_NAME).execute();
		defaultConnection.prepareStatement("DELETE FROM " + Module.TABLE_NAME).execute();
		defaultConnection.prepareStatement("DELETE FROM " + Role.TABLE_NAME).execute();
		defaultConnection.prepareStatement("DELETE FROM " + StudentClassRoom.TABLE_NAME).execute();
		defaultConnection.prepareStatement("DELETE FROM TEACHER_CLASS_ROOM" ).execute();
		defaultConnection.prepareStatement("DELETE FROM " + User.TABLE_NAME).execute();
		DbUtils.closeQuietly(defaultConnection);
	}*/
}
