package be.occam.velo.domain.exception;

import be.occam.utils.spring.web.ErrorCode;

@SuppressWarnings("serial")
public class VeloException extends RuntimeException {
	
	protected ErrorCode errorCode;
	protected String actor;
	
	public VeloException( String message ) {
		super( message );
	}
	
	public VeloException( ErrorCode errorCode ) {
		super();
		this.errorCode = errorCode;
	}
	
	public VeloException( ErrorCode errorCode, String message ) {
		super( message );
		this.errorCode = errorCode;
	}
	
	public VeloException( String actor, ErrorCode errorCode ) {
		super();
		this.actor = actor;
		this.errorCode = errorCode;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}

}
