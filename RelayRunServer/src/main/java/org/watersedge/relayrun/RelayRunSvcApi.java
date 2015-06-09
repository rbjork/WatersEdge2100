package org.watersedge.relayrun;

// This is for a client like Android

import java.util.Collection;

import org.watersedge.relayrun.repository.Baton;
import org.watersedge.relayrun.repository.Marker;
import org.watersedge.relayrun.repository.Runner;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

public interface RelayRunSvcApi {
	
	public static final String RUNNER_SVC_PATH = "/runner";
	public static final String MARKER_SVC_PATH = "/marker";
	public static final String BATON_SVC_PATH = "/baton";
	public static final String GEO_SVC_PATH = "/geo";
	
	// Runner API
	@POST(RUNNER_SVC_PATH + "/add")
	public Runner submitRunner(@Body Runner runner);
	
	@GET(RUNNER_SVC_PATH + "/remove/{runnerID}")
	public boolean removeRunner(@Path("runnerID") long runnerID);
	
	@GET(RUNNER_SVC_PATH)
	public Collection<Runner> getRunners();
	
	@GET(RUNNER_SVC_PATH + "/clear")
	public boolean clearRunners();
	
	
	// Marker API
	@POST(MARKER_SVC_PATH + "/add")
	public boolean submitMarker(@Body Marker marker);
	
	@GET(MARKER_SVC_PATH)
	public Collection<Marker> getMarkers();
	
	@GET(MARKER_SVC_PATH + "/{markerID}")
	public Collection<Marker> getRunnersMarkers(@Path("markerID") long markerID);
	
	@GET(MARKER_SVC_PATH + "/clear")
	public boolean clearMarkers();
	
	
	// Baton API
	@POST(BATON_SVC_PATH + "/add")
	public boolean submitBaton(@Body Baton baton);
	
	@GET(BATON_SVC_PATH)
	public Collection<Baton> getBatonData();
	
	@GET(BATON_SVC_PATH + "/clear")
	public boolean clearBatonData();
	
	
	// Geo Data Requests 
	@GET(GEO_SVC_PATH + "/waterdepth/{logitude}/{latitude}")
	public int getWaterDepthAt(@Path("logitude") long longitude, @Path("latitude") long latitude);
	
	@GET(GEO_SVC_PATH + "/elevation/{logitude}/{latitude}")
	public int getElevationAt(@Path("logitude") long longitude, @Path("latitude") long latitude);
	
	@GET(GEO_SVC_PATH + "/waterdepth/direction100/{logitude}/{latitude}/{compass}")
	public int getWaterDeptForDirectionAt100Meters(@Path("logitude") long longitude, @Path("latitude") long latitude, @Path("compass") float compass);
	
	@GET(GEO_SVC_PATH + "/waterdepth/direction/{logitude}/{latitude}/{compass}")
	public int getDistanceToWatersEdgeForDirection(@Path("logitude") long longitude, @Path("latitude") long latitude, @Path("compass") float compass);
	
}
