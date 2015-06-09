package org.watersedge.relayrun.repository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Marker {
	
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	private long id;
	
	private long runnerID;
	
	private double longitude;
	private double latitude;
	private long timeMarked;
	
	private float elevation;
	private float waterDepth;
	

	public Marker(){
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
	
	public long getTimeMarked() {
		return timeMarked;
	}

	public void setTimeMarked(long timeMarked) {
		this.timeMarked = timeMarked;
	}
	
	public long getRunnerID() {
		return runnerID;
	}

	public void setRunnerID(long runnerID) {
		this.runnerID = runnerID;
	}

	public float getElevation() {
		return elevation;
	}

	public void setElevation(float elevation) {
		this.elevation = elevation;
	}

	public float getWaterDepth() {
		return waterDepth;
	}

	public void setWaterDepth(float waterDepth) {
		this.waterDepth = waterDepth;
	}

}
