package com.parkit.parkingsystem.integration;

import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.dao.ParkingSpotDAO;
import com.parkit.parkingsystem.dao.TicketDAO;
import com.parkit.parkingsystem.integration.config.DataBaseTestConfig;
import com.parkit.parkingsystem.integration.service.DataBasePrepareService;
import com.parkit.parkingsystem.model.ParkingSpot;
import com.parkit.parkingsystem.model.Ticket;
import com.parkit.parkingsystem.service.ParkingService;
import com.parkit.parkingsystem.util.InputReaderUtil;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.when;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

//import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;



@ExtendWith(MockitoExtension.class)
public class ParkingDataBaseIT {

    private static DataBaseTestConfig dataBaseTestConfig = new DataBaseTestConfig();
    private static ParkingSpotDAO parkingSpotDAO;
    private static TicketDAO ticketDAO;
    private static DataBasePrepareService dataBasePrepareService;

    @Mock
    private static InputReaderUtil inputReaderUtil;

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
    public void testParkingACar() {
    	

        when(inputReaderUtil.readSelection()).thenReturn(1);
        
		
		when(inputReaderUtil.readVehicleRegistrationNumber()).thenReturn("ABCDEF");
		
		
		ParkingSpot parkingSpot = new ParkingSpot(1, ParkingType.CAR, false);
        
        
        ParkingService parkingService = new ParkingService(inputReaderUtil, parkingSpotDAO, ticketDAO);
        
        parkingService.processIncomingVehicle();
        
        //check that a ticket is actualy saved in DB and Parking table is updated with availability
      
        
        assertNotNull(ticketDAO.getTicket("ABCDEF"));
        
        assertNotNull(parkingSpotDAO.updateParking(parkingSpot));
        
        

        
    }
    

    
    @Test
    public void testParkingLotExit() throws Exception{
    	testParkingACar();
        ParkingService parkingService = new ParkingService(inputReaderUtil, parkingSpotDAO, ticketDAO);
       
        ParkingSpot parkingSpot = new ParkingSpot(1, ParkingType.CAR, false);
        Ticket ticket = new Ticket();
        ticket.setVehicleRegNumber("ABCDEF");
        ticket.setId(1);
        ticket.setParkingSpot(parkingSpot);
        ticket.setPrice(0);
        ticket.setInTime(new Date(System.currentTimeMillis() - (1000 * 60 * 31)));
        
        ticketDAO.saveTicket(ticket);
        parkingSpotDAO.updateParking(parkingSpot);
        
        parkingService.processExitingVehicle();
        
        //check that the fare generated and out time are populated correctly in the database
        
        assertNotNull(ticketDAO.getTicket("ABCDEF").getOutTime());
        
        assertNotEquals(0, ticketDAO.getTicket("ABCDEF").getPrice());
       
    }

}
