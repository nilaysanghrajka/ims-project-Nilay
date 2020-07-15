package com.qa.ims.persistence.domain;

public class Order {
	
	private Long order_id;
	private Long customer_id;
	private Long item_id;
	private Long units;
	private double total_value;
	
		
	public Long getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}
	public Long getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(Long customer_id) {
		this.customer_id = customer_id;
	}
	public Long getitem_id() {
		return item_id;
	}
	public void setitem_id(Long item_id) {
		this.item_id = item_id;
	}
	public Long getUnits() {
		return units;
	}
	public void setUnits(Long units) {
		this.units = units;
	}
	public double getTotal_value() {
		return total_value;
	}
	public void setTotal_value(double total_value) {
		this.total_value = total_value;
	}
	
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customer_id == null) ? 0 : customer_id.hashCode());
		result = prime * result + ((order_id == null) ? 0 : order_id.hashCode());
		result = prime * result + ((item_id == null) ? 0 : item_id.hashCode());
		long temp;
		temp = Double.doubleToLongBits(total_value);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((units == null) ? 0 : units.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (customer_id == null) {
			if (other.customer_id != null)
				return false;
		} else if (!customer_id.equals(other.customer_id))
			return false;
		if (order_id == null) {
			if (other.order_id != null)
				return false;
		} else if (!order_id.equals(other.order_id))
			return false;
		if (item_id == null) {
			if (other.item_id != null)
				return false;
		} else if (!item_id.equals(other.item_id))
			return false;
		if (Double.doubleToLongBits(total_value) != Double.doubleToLongBits(other.total_value))
			return false;
		if (units == null) {
			if (other.units != null)
				return false;
		} else if (!units.equals(other.units))
			return false;
		return true;
	}
	
	
	public Order(Long order_id, Long customer_id, Long item_id, Long units, double total_value) {
		super();
		this.order_id = order_id;
		this.customer_id = customer_id;
		this.item_id = item_id;
		this.units = units;
		this.total_value = total_value;
	}

}
