package com.parkit.parkingsystem.service;

import java.time.Duration;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import com.parkit.parkingsystem.constants.Fare;
import com.parkit.parkingsystem.dao.TicketDAO;
import com.parkit.parkingsystem.model.Ticket;

public class FareCalculatorService {


	TicketDAO ticketDAO = new TicketDAO();
	

	
	public void calculateFare(Ticket ticket){
        
		if( (ticket.getOutTime() == null) || (ticket.getOutTime().before(ticket.getInTime())) ){
            throw new IllegalArgumentException("Out time provided is incorrect:"+ticket.getOutTime().toString());
        }

        
		double inHour = ticket.getInTime().getTime();
		
		double outHour = ticket.getOutTime().getTime();
		

        //TODO: Some tests are failing here. Need to check if this logic is correct
       
        double duration = ((outHour - inHour) / (60 * 60 * 1000));
        
        int isRecuringCustomer = ticketDAO.getTicketsReccurentUser(ticket.getVehicleRegNumber());
        
        float ratio = 1;
        
        if( isRecuringCustomer>1 ) {
        	
        	ratio = 0.95F;
        }
        
        if(duration <= 0.5) {
        	ticket.setPrice(duration * 0);
        }
       
        else {
        	
        switch (ticket.getParkingSpot().getParkingType()){
            case CAR: {
                ticket.setPrice(duration * Fare.CAR_RATE_PER_HOUR * ratio);  
                
                break;
            }
            
            case BIKE: {
                ticket.setPrice(duration * Fare.BIKE_RATE_PER_HOUR * ratio);
                break;
            }
            default: throw new NullPointerException("Unkown Parking Type");
        }
        }
    }
	
	
	
	

}