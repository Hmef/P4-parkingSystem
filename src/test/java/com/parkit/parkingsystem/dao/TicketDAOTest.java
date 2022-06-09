package com.parkit.parkingsystem.dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.integration.config.DataBaseTestConfig;
import com.parkit.parkingsystem.integration.service.DataBasePrepareService;
import com.parkit.parkingsystem.model.ParkingSpot;
import com.parkit.parkingsystem.model.Ticket;
import com.parkit.parkingsystem.util.InputReaderUtil;


public class TicketDAOTest {

	private static DataBaseTestConfig dataBaseTestConfig = new DataBaseTestConfig();
    private static ParkingSpotDAO parkingSpotDAO;
    private static TicketDAO ticketDAO;
    private static DataBasePrepareService dataBasePrepareService;
	
    
	

    @BeforeAll
    private static void setUp() {
        parkingSpotDAO = new ParkingSpotDAO();
        parkingSpotDAO.dataBaseConfig = dataBaseTestConfig;
        ticketDAO = new TicketDAO();
        ticketDAO.dataBaseConfig = dataBaseTestConfig;
        dataBasePrepareService = new DataBasePrepareService();
    }
    
    
    @BeforeEach
    private void setUpPerTest() {
    	
        
        dataBasePrepareService.clearDataBaseEntries();
    }

	@Test 
	public void saveTicketTest() {  // teste les deux méthodes saveTicket() et getTicket()
		
		//Ticket ticket = new Ticket(1, parkingSpot, "ABCDEF", 2, );

		ParkingSpot parkingSpot = new ParkingSpot(2, ParkingType.CAR, false);
				
		ticketDAO = new TicketDAO();
				
		Ticket ticket = new Ticket();
		//ID, PARKING_NUMBER, VEHICLE_REG_NUMBER, PRICE, IN_TIME, OUT_TIME)
		//ticket.setId(ticketID);
	    ticket.setParkingSpot(parkingSpot);
		ticket.setVehicleRegNumber("ABCDEF");
		ticket.setPrice(0);
		ticket.setInTime(new Date());
	    //ticket.setOutTime(new Date());
			    
	    ticketDAO.saveTicket(ticket); 
		
		assertNotNull(ticketDAO.getTicket("ABCDEF"));
		
		
	}
	
	@Test
	public void updateTicketTest() {
		

		ParkingSpot parkingSpot = new ParkingSpot(2, ParkingType.CAR, true);
		
		ticketDAO = new TicketDAO();
		
		Ticket ticket = new Ticket();
		//ID, PARKING_NUMBER, VEHICLE_REG_NUMBER, PRICE, IN_TIME, OUT_TIME)
		//ticket.setId(ticketID);
	    ticket.setParkingSpot(parkingSpot);
		ticket.setVehicleRegNumber("ABCDEFEE");
		ticket.setPrice(1.5);
		

		System.out.println("ticket.getPrice()   -->   " + ticket.getPrice());
		
		ticket.setInTime(new Date());
	    ticket.setOutTime(new Date());
		
		ticketDAO.updateTicket(ticket);
		
		assertNotNull(ticketDAO.getTicket("ABCDEF"));
		
		
	}
	
	
	@AfterEach
	public void tearDown() {
		
	}
	
	
}
