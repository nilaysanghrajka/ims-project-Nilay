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
import com.qa.ims.controller.OrderController;
import com.qa.ims.persistence.dao.OrderDaoMysql;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.services.OrderServices;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrderDaoMysqlTest {
	/**
	 * The thing I want to fake functionality for
	 */
	@Mock
	private OrderServices orderServices;

	/**
	 * Spy is used because i want to mock some methods inside the item I'm testing
	 * InjectMocks uses dependency injection to insert the mock into the customer
	 * controller
	 */
	@Spy
	@InjectMocks
	private OrderController orderController;

	public static final Logger LOGGER = Logger.getLogger(Ims.class);
//	static String jdbcurl = "jdbc:mysql://localhost:3306/ims_test?serverTimezone=UTC";
//	static String username = "root";
//	static String password = "root";

	static String jdbcurl = "jdbc:mysql://" + Utils.MYSQL_URL + "/ims";
	static String username = "root";
	static String password = "Pr1yanka20";

//	@BeforeClass
//	public static void setup() {
//		try {
//			Connection connection = DriverManager.getConnection(jdbcurl, username, password);
//			Statement statement = connection.createStatement();
//			statement.executeUpdate("Drop database ims_test");
//
//		} catch (Exception e) {
//			LOGGER.debug(e.getStackTrace());
//			LOGGER.error(e.getMessage());
//		}
//	}

	@BeforeClass
	public static void aInit() {
		Ims ims = new Ims();
		ims.init("jdbc:mysql://localhost:3306/ims_test?serverTimezone=UTC", "root", "root",
				"src/test/resources/sql-schema.sql");
	}

	@Test
	public void CreateTest() {
		OrderDaoMysql orderDaoMysql = new OrderDaoMysql(
				"jdbc:mysql://localhost:3306/ims_test?serverTimezone=UTC", "root", "root");
		Long customerID = 1L;
		Long itemID = 1L;
		double units = 3;
		Order order = new Order(1L, customerID, itemID, units);
		Long customerID2 = 2L;
		Long itemID2 = 3L;
		double units2 = 4;
		Order order2 = new Order(2L, customerID, itemID, units);
		Long customerID3 = 3L;
		Long itemID3 = 2L;
		double units3 = 5;
		Order order3 = new Order(3L, customerID, itemID, units);
		assertEquals(order, orderDaoMysql.create(order));
		assertEquals(order2, orderDaoMysql.create(order2));
		assertEquals(order3, orderDaoMysql.create(order3));
	}

	@Test
	public void ReadAllTest() {
		OrderDaoMysql orderDaoMysql = new OrderDaoMysql(
				"jdbc:mysql://localhost:3306/ims_test?serverTimezone=UTC", "root", "root");
		List<Order> orders = new ArrayList<>();
		orders.add(new Order(1L, 1L, 1L, 3));
		orders.add(new Order(2L, 2L, 3L, 4));
		orders.add(new Order(3L, 3L, 2L, 5));

		assertEquals(orders, orderDaoMysql.readAll());
	}

	@Test
	public void ReadLatestTest() {
		OrderDaoMysql orderDaoMysql = new OrderDaoMysql(
				"jdbc:mysql://localhost:3306/ims_test?serverTimezone=UTC", "root", "root");
		Order order = new Order(2L, 2L, 3L, 4);
		assertEquals(order, orderDaoMysql.readLatest());
	}

	@Test
	public void ReadOrderTest() {
		OrderDaoMysql orderDaoMysql = new OrderDaoMysql(
				"jdbc:mysql://localhost:3306/ims_test?serverTimezone=UTC", "root", "root");
		Order order = new Order(2L, 2L, 3L, 4);
		assertEquals(order, orderDaoMysql.readOrder(2L));
	}

//
//	/**
//	 *
//	 */
	@Test
	public void UpdateTest() {
		OrderDaoMysql orderDaoMysql = new OrderDaoMysql(
				"jdbc:mysql://localhost:3306/ims_test?serverTimezone=UTC", "root", "root");
		Long id = 1L;
		Long customerID = 1L;
		Long itemID = 1L;
		double units = 3;
		Order order = new Order((id), customerID, itemID, units);
		assertEquals(order, orderDaoMysql.update(order));
	}

//	/**
//	 * makes sure that after you delete, the entry is no longer in the database.
//	 */
	@Test
	public void gDeleteTest() {
		OrderDaoMysql orderDaoMysql = new OrderDaoMysql(
				"jdbc:mysql://localhost:3306/ims_test?serverTimezone=UTC", "root", "root");
		String id = "3";
		orderDaoMysql.delete(Long.parseLong(id));
		List<Order> orders = new ArrayList<>();
		orders.add(new Order(3L, 3L, 2L, 5));
		assertEquals(orders, orderDaoMysql.readAll());
	}

	@AfterClass
	public static void cleanDB() {

		try (Connection connection = DriverManager.getConnection(jdbcurl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("drop table orders");
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
	}

}
