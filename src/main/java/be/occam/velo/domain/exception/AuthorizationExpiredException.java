package be.occam.velo.domain.exception;

public class AuthorizationExpiredException extends RuntimeException {
	public AuthorizationExpiredException(String msg) {
		super(msg);
	}
}
