package be.occam.velo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface MapRepository extends JpaRepository<MapEntity, String>{
	
	/*
	@Query("select a from be.pirlewiet.digitaal.model.Application a where a.organisationUuid = :1 AND a.year = :2")
	public List<Location> findByOrganisationUuidAndYear( String organisationUuid, int year );
	
	public Application findByUuid( String uuid );
	public List<Application> findByYear( int year );
	public List<Application> findByOrganisationUuid( String organisationUuid );
	*/
	
	public MapEntity findOneByUuid( String uuid );
	public MapEntity findOneByUserID( String userID );

}
