package com.parkit.parkingsystem;

import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.dao.ParkingSpotDAO;
import com.parkit.parkingsystem.dao.TicketDAO;
import com.parkit.parkingsystem.model.ParkingSpot;
import com.parkit.parkingsystem.model.Ticket;
import com.parkit.parkingsystem.service.ParkingService;
import com.parkit.parkingsystem.util.InputReaderUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ParkingServiceTest {

    private static ParkingService parkingService;

    @Mock
    private static InputReaderUtil inputReaderUtil;
    @Mock
    private static ParkingSpotDAO parkingSpotDAO;
    @Mock
    private static TicketDAO ticketDAO;
    
    private static Ticket ticket ;

    @BeforeEach
    private void setUpPerTest() {
        try {
            //when(inputReaderUtil.readVehicleRegistrationNumber()).thenReturn("ABCDEF");   
        	

            ParkingSpot parkingSpot = new ParkingSpot(1, ParkingType.CAR,false);   
            
            ticket = new Ticket();
            ticket.setInTime(new Date(System.currentTimeMillis() - (60*60*1000)));
            ticket.setParkingSpot(parkingSpot); 
            ticket.setVehicleRegNumber("ABCDEF");
            

            parkingService = new ParkingService(inputReaderUtil, parkingSpotDAO, ticketDAO);
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to set up test mock objects");
        }
    }

   
    @Test
    public void processIncomingVehicleTest() throws Exception{
    	
    	
    	when(inputReaderUtil.readSelection()).thenReturn(1);
    	
    	when(inputReaderUtil.readVehicleRegistrationNumber()).thenReturn("ABCDEF");
    	
    	when(parkingSpotDAO.getNextAvailableSlot(any(ParkingType.class))).thenReturn(1);
    	
    	parkingService.processIncomingVehicle();
    	
    	
    	verify(ticketDAO, Mockito.times(1)).saveTicket(any(Ticket.class));
    	
    	
    	
    }
    
    @Test
    public void getNextParkingNumberIfAvailable() {
    	
    	//when(parkingSpotDAO.getNextAvailableSlot(anyInt())).thenReturn()
    	//when(parkingSpotDAO.getNextAvailableSlot()).thenReturn(CAR);
    	
    	ParkingSpot parkingSpot = new ParkingSpot(1, ParkingType.CAR, true);
    	
    	
    	parkingSpotDAO.updateParking(parkingSpot);
    	
    	assertNotNull(parkingSpotDAO.getNextAvailableSlot(ParkingType.CAR));
    	
    }
     
    
    @Test 
    public void getValidVehicleTypeTest() {
    	
    	
    	//int actual = inputReaderUtil.readSelection();
    	
    	//int expected = 1;
    	
    	//assertEquals(1, actual);
    	//ParkingSpot parkingSpot = new ParkingSpot(1, ParkingType.CAR, false);
    	
    	//assertEquals(ParkingType.CAR, parkingSpot.getParkingType());
    	
    	
    }
    
    @Test
    public void testInvalidVehicleType(){
    	
    	ParkingSpot parkingSpot = new ParkingSpot(1, null, false);
    	
    	System.out.println("parkingSpot.getParkingType() : " + parkingSpot.getParkingType());
    	
    	//assertThrows(IllegalArgumentException.class, () -> parkingSpot.getParkingType());
    	//assertThrows(IllegalArgumentException.class, () -> );
    }
  
    
    @Test
    public void processExitingVehicleTest(){
    	
		try {
			when(inputReaderUtil.readVehicleRegistrationNumber()).thenReturn("ABCDEF");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	

        when(ticketDAO.getTicket(anyString())).thenReturn(ticket);
        
        when(ticketDAO.updateTicket(any(Ticket.class))).thenReturn(true);
        when(parkingSpotDAO.updateParking(any(ParkingSpot.class))).thenReturn(true);  
        
    	
        parkingService.processExitingVehicle();
        
        verify(parkingSpotDAO, Mockito.times(1)).updateParking(any(ParkingSpot.class));
        
    }

}
