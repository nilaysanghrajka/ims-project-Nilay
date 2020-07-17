package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.Ims;
import com.qa.ims.controller.CustomerController;
import com.qa.ims.persistence.dao.CustomerDaoMysql;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.services.CustomerServices;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CustomerDaoMysqlTest {
	/**
	 * The thing I want to fake functionality for
	 */
	@Mock
	private CustomerServices customerServices;

	/**
	 * Spy is used because i want to mock some methods inside the item I'm testing
	 * InjectMocks uses dependency injection to insert the mock into the customer
	 * controller
	 */
	@Spy
	@InjectMocks
	private CustomerController customerController;

	public static final Logger LOGGER = Logger.getLogger(Ims.class);
//	static String jdbcurl = "jdbc:mysql://localhost:3306/ims_test?serverTimezone=UTC";
//	static String username = "root";
//	static String password = "root";

	static String jdbcurl = "jdbc:mysql://" + Utils.MYSQL_URL + "/ims";
	static String username = "root";
	static String password = "root";

	@BeforeClass
	public static void setup() {
		try {
			Connection connection = DriverManager.getConnection(jdbcurl, username, password);
			Statement statement = connection.createStatement();
			statement.executeUpdate("Drop database ims");

		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
	}

	@BeforeClass
	public static void Init() {
		Ims ims = new Ims();
		ims.init("jdbc:mysql://localhost:3306/ims?serverTimezone=UTC", "root", "root",
				"src/test/resources/sql-schema.sql");
	}

	@Test
	public void CreateTest() {
		CustomerDaoMysql customerDaoMysql = new CustomerDaoMysql(
				"jdbc:mysql://localhost:3306/ims?serverTimezone=UTC", "root", "root");
		String firstName = "Chris";
		String surname = "Perrins";
		Customer customer = new Customer(1L, firstName, surname);
		String firstName2 = "Rhys";
		String surname2 = "Thompson";
		Customer customer2 = new Customer(2L, firstName2, surname2);
		String firstName3 = "Nic";
		String surname3 = "Johnson";
		Customer customer3 = new Customer(3L, firstName3, surname3);
		assertEquals(customer, customerDaoMysql.create(customer));
		assertEquals(customer2, customerDaoMysql.create(customer2));
		assertEquals(customer3, customerDaoMysql.create(customer3));
	}

	@Test
	public void ReadAllTest() {
		CustomerDaoMysql customerDaoMysql = new CustomerDaoMysql(
				"jdbc:mysql://localhost:3306/ims?serverTimezone=UTC", "root", "root");
		List<Customer> customers = new ArrayList<>();
		customers.add(new Customer(1L, "Chris", "Perrins"));
		customers.add(new Customer(2L, "Rhys", "Thompson"));
		customers.add(new Customer(3L, "Nic", "Johnson"));

		assertEquals(customers, customerDaoMysql.readAll());
	}

	@Test
	public void ReadLatestTest() {
		CustomerDaoMysql customerDaoMysql = new CustomerDaoMysql(
				"jdbc:mysql://localhost:3306/ims?serverTimezone=UTC", "root", "root");
		Customer customer = new Customer(3L, "Nic", "Johnson");
		assertEquals(customer, customerDaoMysql.readLatest());
	}

	@Test
	public void ReadCustomerTest() {
		CustomerDaoMysql customerDaoMysql = new CustomerDaoMysql(
				"jdbc:mysql://localhost:3306/ims?serverTimezone=UTC", "root", "root");
		Customer customer = new Customer(2L, "Rhys", "Thompson");
		assertEquals(customer, customerDaoMysql.readCustomer(2L));
	}

//
//	/**
//	 *
//	 */
	@Test
	public void UpdateTest() {
		CustomerDaoMysql customerDaoMysql = new CustomerDaoMysql(
				"jdbc:mysql://localhost:3306/ims?serverTimezone=UTC", "root", "root");
		Long id = 1L;
		String firstName = "Chris";
		String surname = "Perrins";
		Customer customer = new Customer((id), firstName, surname);
		assertEquals(customer, customerDaoMysql.update(customer));
	}

//	/**
//	 * makes sure that after you delete, the entry is no longer in the database.
//	 */
	@Test
	public void DeleteTest() {
		CustomerDaoMysql customerDaoMysql = new CustomerDaoMysql(
				"jdbc:mysql://localhost:3306/ims?serverTimezone=UTC", "root", "root");
		String id = "3";
		customerDaoMysql.delete(Long.parseLong(id));
		List<Customer> customers = new ArrayList<>();
		customers.add(new Customer(3L, "Nic", "Johnson"));
		assertEquals(customers, customerDaoMysql.readAll());
	}

	@AfterClass
	public static void cleanDB() {

		try (Connection connection = DriverManager.getConnection(jdbcurl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("drop table customers");
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
	}

}
