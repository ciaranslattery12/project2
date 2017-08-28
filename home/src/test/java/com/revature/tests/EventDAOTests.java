package com.revature.tests;

import static org.junit.Assert.assertEquals;

import java.applet.AppletContext;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.revature.beans.EventStatus;
import com.revature.beans.EventType;
import com.revature.beans.Events;
import com.revature.beans.Photos;
import com.revature.beans.Users;
import com.revature.beans.UsersRole;
import com.revature.data.services.EventDAOManager;
import com.revature.data.services.UserDAOManager;

public class EventDAOTests extends ChangeForChangeTests {

	private static final Logger logger = Logger.getLogger(EventDAOTests.class);
	private static final String EVENT_COUNT = "select count(event_id) from Events";
	private static EventDAOManager eventDAOManager;
	
	@BeforeClass
	public static void setup(){
		context = new ClassPathXmlApplicationContext("dao-beans.xml");
	}
	
	@Ignore
	@Test
	public void createTest() throws ParseException{
		
		logger.info("CREATE EVENT TEST");
		
		// set date in the future
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = dateFormat.parse("01/09/2017");
		long time = date.getTime();

		eventDAOManager = (EventDAOManager) context.getBean("eventDAO");
		
		// instantiate custom types
		EventType type = new EventType(1, "WALK");
		EventStatus status = new EventStatus(1, "UPCOMING");
		UsersRole role = new UsersRole(1, "ADMIN");
		Users user = new Users(142, "Patrick", "Muldoon", "patrickM" , "password", "patrick@example.com", role);
		
		// construct event and create
		Events newEvent = new Events(250, new Timestamp(time), "Diabetes Walk", type, user, status);
		Long rowCount = jdbcTemplate.queryForObject(EVENT_COUNT, Long.class);
		eventDAOManager.create(newEvent);
		Long newRowCount = jdbcTemplate.queryForObject(EVENT_COUNT, Long.class);

		assertEquals(++rowCount, newRowCount);
	}
	
	@Ignore
	@Test
	public void updateTest() {
		
		/*eventDAOManager = (EventDAOManager) context.getBean("eventDAO");
		
		// instantiate custom types
		EventType type = new EventType(2, "FUNDRAISER");
		EventStatus status = new EventStatus(1, "UPCOMING");
		UsersRole role = new UsersRole(1, "ADMIN");
		Users user = new Users(142, "Patrick", "Muldoon", "patrickM" , "password", "patrick@example.com", role);
		
		Events event = new Events(250, new Timestamp(time), "Diabetes Walk", type, user, status);
		eventDAOManager.update(event);*/
	}
	
	@Ignore
	@Test
	public void deleteTest() {
		
	}
	
	@Ignore
	@Test
	public void findByIdTest() {
		
	}
	
	@Ignore
	@Test
	public void findAllTest() {
		
	}
	
	
	
	
	
	
	
}
