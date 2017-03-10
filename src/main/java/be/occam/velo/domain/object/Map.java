package be.occam.velo.domain.object;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Key;

@Entity
@Cacheable(value=false)
public class Map {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Key key;

	protected String uuid;
	protected String userID;
	protected double radius;

}
