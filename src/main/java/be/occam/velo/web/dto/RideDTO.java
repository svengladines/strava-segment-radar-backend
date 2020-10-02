package be.occam.velo.web.dto;

import java.util.Date;
import java.util.List;


public class RideDTO {
	
	public static enum Status {
		
		READY_TO_ROLL, ROLLIN_IN_THE_DEEP, PAUZED, FINITO
		
	};
	
	protected String uuid;
	protected String title;
	protected String description;
	protected Date moment;
	protected CoordinateDTO start;
	protected Status status;
	protected Integer length;
	protected List<String> riders;
	
	public String getUuid() {
		return uuid;
	}
	
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getMoment() {
		return moment;
	}

	public void setMoment(Date moment) {
		this.moment = moment;
	}

	public CoordinateDTO getStart() {
		return start;
	}

	public void setStart(CoordinateDTO start) {
		this.start = start;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public List<String> getRiders() {
		return riders;
	}

	public void setRiders(List<String> riders) {
		this.riders = riders;
	}
	
}
