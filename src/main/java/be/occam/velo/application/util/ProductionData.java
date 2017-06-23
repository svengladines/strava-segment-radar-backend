package be.occam.velo.application.util;

import static be.occam.utils.javax.Utils.list;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.occam.utils.spring.web.Result;
import be.occam.velo.RideDTO;
import be.occam.velo.domain.exception.ErrorCodes;
import be.occam.velo.domain.service.RideService;
import be.occam.velo.web.util.VeloUtil;

public class ProductionData {
	
	protected final Logger logger 
		= LoggerFactory.getLogger( this.getClass() );
	
	@Resource
	RideService rideService;
	
	
	@PostConstruct
	public void makeSureProductionDataExists( ) {
		
		{
		
			
		}
		
		
	}

}
