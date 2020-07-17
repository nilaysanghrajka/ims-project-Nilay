package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class OrderTest {

  private Order order;
  private Order other;

  @Before
  public void setUp() {
    order = new Order (1L, 1L, 1L, 3);
    other = new Order (1L, 1L, 1L, 3);
  }

//  @Test
//  public void settersTest() {
//    assertNotNull(order.getId());
//    assertNotNull(order.getCustomerID());
//    assertNotNull(order.getItemID());
//    assertNotNull(order.getUnits());
//
//    order.setId(null);
//    assertNull(order.getId());
//    order.setCustomerID(null);
//    assertNull(order.getCustomerID());
//    order.setItemID(null);
//    assertNull(order.getItemID());
//    order.setUnits((Double) null);
//    assertNull(order.getUnits());
//
//  }

  @Test
  public void equalsWithNull() {
    assertFalse(order.equals(null));
  }

  @Test
  public void equalsWithDifferentObject() {
    assertFalse(order.equals(new Object()));
  }

  @Test
  public void createOrderWithId() {
    assertEquals(1L, order.getId(), 0);
    assertEquals(1L, order.getCustomerID(), 0);
    assertEquals(1L, order.getItemID(), 0);
    assertEquals(3, order.getUnits(), 0);
  }

  @Test
  public void checkEquality() {
    assertTrue(order.equals(order));
  }

  @Test
  public void checkEqualityBetweenDifferentObjects() {
    assertTrue(order.equals(other));
  }

  @Test
  public void orderNameNullButOtherNameNotNull() {
    order.setCustomerID(null);
    assertFalse(order.equals(other));
  }

  @Test
  public void orderNamesNotEqual() {
    other.setCustomerID(2L);
    assertFalse(order.equals(other));
  }

  @Test
  public void checkEqualityBetweenDifferentObjectsNullName() {
    order.setCustomerID(null);
    other.setCustomerID(null);
    assertTrue(order.equals(other));
  }

  @Test
  public void nullId() {
    order.setId(null);
    assertFalse(order.equals(other));
  }

  @Test
  public void nullIdOnBoth() {
    order.setId(null);
    other.setId(null);
    assertTrue(order.equals(other));
  }

  @Test
  public void otherIdDifferent() {
    other.setId(2L);
    assertFalse(order.equals(other));
  }

  @Test
  public void nullItemID() {
    order.setItemID(null);
    assertFalse(order.equals(other));
  }

  @Test
  public void nullVItemIDOnBoth() {
    order.setItemID(null);
    other.setItemID(null);
    assertTrue(order.equals(other));
  }

  @Test
  public void otherItemIDDifferent() {
    other.setItemID(4L);
    assertFalse(order.equals(other));
  }

//  @Test
//  public void constructorWithoutId() {
//    Order order = new Order(5L, 5L, 5L, 23.00);
//    assertNull(order.getId());
//    assertNotNull(order.getCustomerID());
//    assertNotNull(order.getItemID());
//    assertNotNull(order.getUnits());
//  }

  @Test
  public void hashCodeTest() {
    assertEquals(order.hashCode(), other.hashCode());
  }
  @Test
  public void hashCodeTestWithNull() {
	Order order = new Order(null, null, null, 0);
	Order other = new Order(null, null, null, 0);
    assertEquals(order.hashCode(), other.hashCode());
  }

//  @Test
//  public void toStringTest() {
//    String toString = "id:1 first name:Chris surname:Perrins";
//    assertEquals(toString, order.toString());
//  }
}
