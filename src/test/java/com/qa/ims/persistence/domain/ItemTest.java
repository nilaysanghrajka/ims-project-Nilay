package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class ItemTest {

  private Item item;
  private Item other;

  @Before
  public void setUp() {
    item = new Item(1L, "Shirt", 19.99);
    other = new Item(1L, "Shirt", 19.99);
  }

  @Test
  public void settersTest() {
    assertNotNull(item.getId());
    assertNotNull(item.getItemName());
    assertNotNull(item.getValue());

    item.setId(null);
    assertNull(item.getId());
    item.setItemName(null);
    assertNull(item.getItemName());
    item.getValue();
    assertNull(item.getValue());

  }

  @Test
  public void equalsWithNull() {
    assertFalse(item.equals(null));
  }

  @Test
  public void equalsWithDifferentObject() {
    assertFalse(item.equals(new Object()));
  }

  @Test
  public void createItemWithId() {
    assertEquals(1L, item.getId(), 0);
    assertEquals("Shirt", item.getItemName());
    assertEquals(19.99, item.getValue(), 0);
  }

  @Test
  public void checkEquality() {
    assertTrue(item.equals(item));
  }

  @Test
  public void checkEqualityBetweenDifferentObjects() {
    assertTrue(item.equals(other));
  }

  @Test
  public void itemNameNullButOtherNameNotNull() {
    item.setItemName(null);
    assertFalse(item.equals(other));
  }

  @Test
  public void itemNamesNotEqual() {
    other.setItemName("Socks");
    assertFalse(item.equals(other));
  }

  @Test
  public void checkEqualityBetweenDifferentObjectsNullName() {
    item.setItemName(null);
    other.setItemName(null);
    assertTrue(item.equals(other));
  }

  @Test
  public void nullId() {
    item.setId(null);
    assertFalse(item.equals(other));
  }

  @Test
  public void nullIdOnBoth() {
    item.setId(null);
    other.setId(null);
    assertTrue(item.equals(other));
  }

  @Test
  public void otherIdDifferent() {
    other.setId(2L);
    assertFalse(item.equals(other));
  }

  @Test
  public void nullValueSurname() {
    item.setValue((Double) null);
    assertFalse(item.equals(other));
  }

  @Test
  public void nullValueOnBoth() {
    item.setValue((Double) null);
    other.setValue((Double) null);
    assertTrue(item.equals(other));
  }

  @Test
  public void otherValueDifferent() {
    other.setValue(19.99);
    assertFalse(item.equals(other));
  }

  @Test
  public void constructorWithoutId() {
    Customer customer = new Customer("Chris", "Perrins");
    assertNull(customer.getId());
    assertNotNull(customer.getFirstName());
    assertNotNull(customer.getSurname());
  }

  @Test
  public void hashCodeTest() {
    assertEquals(item.hashCode(), other.hashCode());
  }
  @Test
  public void hashCodeTestWithNull() {
    Customer customer = new Customer(null, null);
    Customer other = new Customer(null, null);
    assertEquals(customer.hashCode(), other.hashCode());
  }

  @Test
  public void toStringTest() {
    String toString = "id:1 first name:Chris surname:Perrins";
    assertEquals(toString, item.toString());
  }
}
