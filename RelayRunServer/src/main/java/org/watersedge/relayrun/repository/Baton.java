package org.watersedge.relayrun.repository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Baton {
	
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	private long id;
	private long runnerID;
	public long getRunnerID() {
		return runnerID;
	}
	public void setRunnerID(long runnerID) {
		this.runnerID = runnerID;
	}
	private long time;
	private double longitude;
	private double latitude;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
}
