package be.occam.velo.application.util;

import com.google.apphosting.api.ApiProxy;
import com.google.apphosting.api.ApiProxy.Environment;

public class DevGuard extends DataGuard {
	
	ThreadLocal<Boolean> done;
	protected Environment environment;
	
	public DevGuard( Environment environment ) {
		
		done = new ThreadLocal<Boolean>() {

			@Override
			protected Boolean initialValue() {
				return false;
			}
			
		};
		
		this.environment = environment;
		
	}
	
	@Override
	public void guard( ) {
		
		if ( ! done.get() ) {
			
			ApiProxy.setEnvironmentForCurrentThread( this.environment );
			done.set( true );
			
		}

	}

}
