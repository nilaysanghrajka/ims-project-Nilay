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
import com.qa.ims.controller.ItemController;
import com.qa.ims.persistence.dao.OrderDaoMysql;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.services.ItemServices;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ItemDaoMysqlTest {
	/**
	 * The thing I want to fake functionality for
	 */
	@Mock
	private ItemServices itemServices;

	/**
	 * Spy is used because i want to mock some methods inside the item I'm testing
	 * InjectMocks uses dependency injection to insert the mock into the customer
	 * controller
	 */
	@Spy
	@InjectMocks
	private ItemController itemController;

	public static final Logger LOGGER = Logger.getLogger(Ims.class);
	static String jdbcurl = "jdbc:mysql://localhost:3306/ims?serverTimezone=UTC";
	static String username = "root";
	static String password = "root";

	// static String jdbcurl = "jdbc:mysql://" + Utils.MYSQL_URL + "/ims";
	// static String username = "root";
	// static String password = "root";

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
		ItemDaoMysql itemDaoMysql = new ItemDaoMysql(
				"jdbc:mysql://localhost:3306/ims?serverTimezone=UTC", "root", "root");
		String itemName = "Jacket";
		double value = 40.50;
		Item item = new Item(1L, itemName, value);
		String itemName2 = "Blazer";
		double value2 = 120.50;
		Item item2 = new Item(2L, itemName, value);
		String itemName3 = "Slippers";
		double value3 = 12.99;
		Item item3 = new Item(3L, itemName, value);
		assertEquals(item, itemDaoMysql.create(item));
		assertEquals(item2, itemDaoMysql.create(item2));
		assertEquals(item3, itemDaoMysql.create(item3));
	}

	@Test
	public void ReadAllTest() {
		ItemDaoMysql itemDaoMysql = new ItemDaoMysql(
				"jdbc:mysql://localhost:3306/ims?serverTimezone=UTC", "root", "root");
		List<Item> items = new ArrayList<>();
		items.add(new Item(1L, "Jacket", 40.50));
		items.add(new Item(2L, "Blazer", 120.50));
		items.add(new Item(3L, "Slippers", 12.99));

		assertEquals(items, itemDaoMysql.readAll());
	}

	@Test
	public void ReadLatestTest() {
		ItemDaoMysql itemDaoMysql = new ItemDaoMysql(
				"jdbc:mysql://localhost:3306/ims?serverTimezone=UTC", "root", "root");
		Item item = new Item(3L, "Slippers", 12.99);
		assertEquals(item, itemDaoMysql.readLatest());
	}

	@Test
	public void ReadItemTest() {
		ItemDaoMysql itemDaoMysql = new ItemDaoMysql(
				"jdbc:mysql://localhost:3306/ims?serverTimezone=UTC", "root", "root");
		Item item = new Item(2L, "Blazer", 120.50);
		assertEquals(item, itemDaoMysql.readItem(2L));
	}

//
//	/**
//	 *
//	 */
	@Test
	public void UpdateTest() {
		ItemDaoMysql itemDaoMysql = new ItemDaoMysql(
				"jdbc:mysql://localhost:3306/ims?serverTimezone=UTC", "root", "root");
		Long id = 1L;
		String itemName = "Jacket";
		double value = 40.50;
		Item item = new Item((id), itemName, value);
		assertEquals(item, itemDaoMysql.update(item));
	}

//	/**
//	 * makes sure that after you delete, the entry is no longer in the database.
//	 */
	@Test
	public void DeleteTest() {
		ItemDaoMysql itemDaoMysql = new ItemDaoMysql(
				"jdbc:mysql://localhost:3306/ims?serverTimezone=UTC", "root", "root");
		String id = "3";
		itemDaoMysql.delete(Long.parseLong(id));
		List<Item> items = new ArrayList<>();
		items.add(new Item(3L, "Slippers", 12.99));
		assertEquals(items, itemDaoMysql.readAll());
	}

	@AfterClass
	public static void cleanDB() {

		try (Connection connection = DriverManager.getConnection(jdbcurl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("drop table items");
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
	}

}
