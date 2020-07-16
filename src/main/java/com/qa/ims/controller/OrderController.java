package com.qa.ims.controller;

import java.util.List;

import org.apache.log4j.Logger;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.services.CrudServices;
import com.qa.ims.utils.Utils;

public class OrderController implements CrudController<Order> {

public static final Logger LOGGER = Logger.getLogger(OrderController.class);

	private CrudServices<Order> orderService;

	public OrderController(CrudServices<Order> orderService) {
		this.orderService = orderService;
	}

	String getInput() {
		return Utils.getInput();
	}

	/**
	 * Reads all orders to the logger
	 */
	@Override
	public List<Order> readAll() {
		List<Order> orders = orderService.readAll();
		for(Order order: orders) {
			LOGGER.info(order.toString());
		}
		return orders;
	}

	/**
	 * Creates an order by taking in user input
	 */
	@Override
	public Order create() {
		LOGGER.info("Please enter the Customer ID for the order.");
		Long customer_id = Long.valueOf(getInput());
		LOGGER.info("Please enter the Item ID for the order.");
		Long item_id = Long.valueOf(getInput());
		LOGGER.info("Please enter the amount of units you would like.");
		Long units = Long.valueOf(getInput());
		Order order = orderService.create(new Order(customer_id, item_id, units, units, units));
		LOGGER.info("Order created");
		return order;
	}

	/**
	 * Updates an existing order by taking in user input
	 */
	@Override
	public Order update() {
		LOGGER.info("Please enter the id of the order you would like to update");
		Long id = Long.valueOf(getInput());

		LOGGER.info("Please enter the Item ID you would like to update.");
		Long item_id = Long.valueOf(getInput());

		LOGGER.info("Please enter the amount of units you would like to update.");
		Long units = Long.valueOf(getInput());

		LOGGER.info("Please enter the new total.");
		double value = Double.valueOf(getInput());

		Order order = orderService.update(new Order(id, item_id, units, value));
		LOGGER.info("Order updated");
		return order;
	}

	/**
	 * Deletes an existing order by the id of the customer
	 */
	@Override
	public void delete() {
		LOGGER.info("Please enter the id of the order you would like to delete");
		Long id = Long.valueOf(getInput());
		orderService.delete(id);
	}
}
