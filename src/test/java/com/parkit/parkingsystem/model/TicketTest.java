/**
 * 
 */
package com.parkit.parkingsystem.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mysql.cj.exceptions.AssertionFailedException;
import com.parkit.parkingsystem.constants.ParkingType;



class TicketTest {

	
	

	
	/**
	 * Test method for {@link com.parkit.parkingsystem.model.Ticket#setId(int)}.
	 */
	@Test
	public void testSetId() {
		
		int id = 2;
		Ticket ticket = new Ticket();
		ticket.setId(id);
		
		
	}
	
	
	
	/**
	 * Test method for {@link com.parkit.parkingsystem.model.Ticket#getId()}.
	 */
	@Test
	public void testGetId() {
		
		Ticket ticket = new Ticket();
		ticket.setId(3);
		int result = ticket.getId();
		int expectedId = 3;  
		
		assertEquals(expectedId, result);  
		
		
	}

	
	
	/**
	 * Test method for {@link com.parkit.parkingsystem.model.Ticket#getParkingSpot()}.
	 */
	@Test
	public void testGetParkingSpot() {
	
		Ticket ticket = new Ticket();
		ParkingSpot parkingSpot = new ParkingSpot(3, ParkingType.CAR, true);
		
		ticket.setParkingSpot(parkingSpot);
		 ParkingSpot actualResult = ticket.getParkingSpot();
		
		ParkingSpot expectedParkingSpot = new ParkingSpot(3, ParkingType.CAR, true);
	
		
		assertEquals(expectedParkingSpot, actualResult);
	}

	/**
	 * Test method for {@link com.parkit.parkingsystem.model.Ticket#setParkingSpot(com.parkit.parkingsystem.model.ParkingSpot)}.
	 */
	@Test
	public void testSetParkingSpot() {

		ParkingSpot parkingSpot = new ParkingSpot(1, ParkingType.CAR, true);
		Ticket ticket = new Ticket();
		ticket.setParkingSpot(parkingSpot);
	}

	/**
	 * Test method for {@link com.parkit.parkingsystem.model.Ticket#getVehicleRegNumber()}.
	 */
	@Test
	public void testGetVehicleRegNumber() {
		
		Ticket ticket = new Ticket();
		ticket.setVehicleRegNumber("ABCDEF");
		String actualResult = ticket.getVehicleRegNumber();
		
		String expectedNum = "ABCDEF";
		
		assertEquals(expectedNum, actualResult);
		

		
	}

	/**
	 * Test method for {@link com.parkit.parkingsystem.model.Ticket#setVehicleRegNumber(java.lang.String)}.
	 */
	@Test
	public void testSetVehicleRegNumber() {
		
		String vehicleRegNumberTest = "ABCDEF";
		Ticket ticket = new Ticket();
		ticket.setVehicleRegNumber(vehicleRegNumberTest);
	}
	
	/**
	 * Test method for {@link com.parkit.parkingsystem.model.Ticket#setPrice(double)}.
	 */
	@Test
	public void testSetPrice() {
		
		double price = 10.5;
		Ticket ticket = new Ticket();
		ticket.setPrice(price);
		
	}

	/**
	 * Test method for {@link com.parkit.parkingsystem.model.Ticket#getPrice()}.
	 */
	@Test
	public void testGetPrice() {
		
		Ticket ticket = new Ticket();
		ticket.setPrice(20.5);
		double result = ticket.getPrice();
		double expectedPrice = 20.5;
		
		assertEquals(expectedPrice, result);
		
		
	}

	
	
	/**
	 * Test method for {@link com.parkit.parkingsystem.model.Ticket#setInTime(java.util.Date)}.
	 */
	@Test
	public void testSetInTime() {
		

		Date InTime = new Date();
		Ticket ticket = new Ticket();
		ticket.setInTime(InTime);
		
	}


	/**
	 * Test method for {@link com.parkit.parkingsystem.model.Ticket#getInTime()}.
	 */
	@Test
	public void testGetInTime() {
		
		Ticket ticket = new Ticket();
		ticket.setInTime(new Date());
		Date result = ticket.getInTime();
		Date expectedInTime = new Date();
		
		assertEquals(expectedInTime, result);
		
		
	}

	
	/**
	 * Test method for {@link com.parkit.parkingsystem.model.Ticket#setOutTime(java.util.Date)}.
	 */
	@Test
	public void testSetOutTime() {
		
		Date outTime = new Date();
		Ticket ticket = new Ticket();
		ticket.setOutTime(outTime);
		
	}

	
	/**
	 * Test method for {@link com.parkit.parkingsystem.model.Ticket#getOutTime()}.
	 */
	@Test
	public void testGetOutTime() {
		
		Ticket ticket = new Ticket();
		ticket.setOutTime(new Date());
		 
		Date OutTimeActual = ticket.getOutTime();
		
		Date OutTimeExpected = new Date();
		
		assertEquals(OutTimeExpected, OutTimeActual);
		
	}

	


	
}
