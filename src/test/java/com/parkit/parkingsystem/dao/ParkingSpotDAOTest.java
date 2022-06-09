/**
 * 
 */
package com.parkit.parkingsystem.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.integration.config.DataBaseTestConfig;
import com.parkit.parkingsystem.integration.service.DataBasePrepareService;
import com.parkit.parkingsystem.model.ParkingSpot;
import com.parkit.parkingsystem.util.InputReaderUtil;

/**
 * @author new
 *
 */
class ParkingSpotDAOTest {
	
	private static DataBaseTestConfig dataBaseTestConfig = new DataBaseTestConfig();
    private static ParkingSpotDAO parkingSpotDAO;
    private static TicketDAO ticketDAO;
    private static DataBasePrepareService dataBasePrepareService;
    
    @Mock
    private static InputReaderUtil inputReaderUtil;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass()  {
		
		parkingSpotDAO = new ParkingSpotDAO();
        parkingSpotDAO.dataBaseConfig = dataBaseTestConfig;
        ticketDAO = new TicketDAO();
        ticketDAO.dataBaseConfig = dataBaseTestConfig;
        dataBasePrepareService = new DataBasePrepareService();
	}

	

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() {
		
		dataBasePrepareService.clearDataBaseEntries();
	}

	

	/**
	 * Test method for {@link com.parkit.parkingsystem.dao.ParkingSpotDAO#getNextAvailableSlot(com.parkit.parkingsystem.constants.ParkingType)}.
	 */
	@Test
	void testGetNextAvailableSlot() {
		
		ParkingSpot parkingSpot = new ParkingSpot(1, ParkingType.CAR, true);
		
		int actual = parkingSpotDAO.getNextAvailableSlot(parkingSpot.getParkingType());
		
		int expected = 1;
		
		assertEquals(expected, actual);
		
	}
	

	/**
	 * Test method for {@link com.parkit.parkingsystem.dao.ParkingSpotDAO#updateParking(com.parkit.parkingsystem.model.ParkingSpot)}.
	 */
	@Test
	void testUpdateParking() {
		
		ParkingSpot parkingSpot = new ParkingSpot(2, ParkingType.CAR, true);
		
		parkingSpotDAO = new ParkingSpotDAO();
		
		parkingSpotDAO.updateParking(parkingSpot);
		
		assertNotNull(parkingSpotDAO.updateParking(parkingSpot));
		
		
		
		
		
	}

}
