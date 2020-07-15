package com.qa.ims.persistence.domain;

public class Item {

	private Long item_id;
	private String item_name;
	private double item_value;

	public Item (String item_name, double item_value) {
		this.item_name = item_name;
		this.item_value = item_value;
	}

	public Item (Long item_id, String item_name, double item_value) {
		this.item_id = item_id;
		this.item_name = item_name;
		this.item_value = item_value;
	}

	// getters and setters
	public Long getitem_id() {
		return item_id;
	}

	public void setitem_id(Long item_id) {
		this.item_id = item_id;
	}

	public String getitem_name() {
		return item_name;
	}

	public void setitem_name(String item_name) {
		this.item_name = item_name;
	}

	public double getitem_value() {
		return item_value;
	}

	public void setitem_value(double item_value) {
		this.item_value = item_value;
	}

	// hashcode and equals
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((item_id == null) ? 0 : item_id.hashCode());
		result = prime * result + ((item_name == null) ? 0 : item_name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(item_value);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Item other = (Item) obj;
		if (item_id == null) {
			if (other.item_id != null)
				return false;
		} else if (!item_id.equals(other.item_id))
			return false;
		if (item_name == null) {
			if (other.item_name != null)
				return false;
		} else if (!item_name.equals(other.item_name))
			return false;
		if (Double.doubleToLongBits(item_value) != Double.doubleToLongBits(other.item_value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "item [item_id=" + item_id + ", item_name=" + item_name + ", item_value="
				+ item_value + "]";
	}

}
