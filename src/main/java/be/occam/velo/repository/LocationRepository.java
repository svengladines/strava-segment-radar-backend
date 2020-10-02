package be.occam.velo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface LocationRepository extends JpaRepository<LocationEntity, String>{
	
	public LocationEntity findOneByUuid( String uuid );
	//public List<LocationEntity> findByRideID( String rideID, Pageable pageable );
	public List<LocationEntity> findByRideID( String rideID );

}
