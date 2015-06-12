package org.watersedge.test;

import java.util.Collection;
import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.watersedge.relayrun.RelayRunSvcApi;
import org.watersedge.relayrun.repository.Baton;
import org.watersedge.relayrun.repository.Marker;
import org.watersedge.relayrun.repository.Runner;
import org.watersedge.test.client.SecuredRestBuilder;

import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;


import retrofit.client.ApacheClient;
import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RelayRunSvcTest {
	
	private final String USERNAME = "admin";
	private final String PASSWORD = "pass";
	private final String CLIENT_ID = "mobile";
	private final String READ_ONLY_CLIENT_ID = "mobileReader";
	
//	private final String TEST_URL = "http://localhost:8080";
//
//	
//	private RelayRunSvcApi relayRunSvcApi = new RestAdapter.Builder()
//		.setEndpoint(TEST_URL)
//		.setLogLevel(LogLevel.FULL).build()
//		.create(RelayRunSvcApi.class);
	
	// Secure version
	private final String TEST_URL_SECURE = "https://localhost:8443";
	
	private RelayRunSvcApi relayRunSvcApi = new SecuredRestBuilder()
		.setLoginEndpoint(TEST_URL_SECURE + RelayRunSvcApi.TOKEN_PATH)
		.setUsername(USERNAME)
		.setPassword(PASSWORD)
		.setClientId(CLIENT_ID)
		.setClient(new ApacheClient(UnsafeHttpsClient.createUnsafeClient()))
		.setEndpoint(TEST_URL_SECURE).setLogLevel(LogLevel.FULL).build()
		.create(RelayRunSvcApi.class);
	
	private RelayRunSvcApi invalidSymptomManagementService = new SecuredRestBuilder()
		.setLoginEndpoint(TEST_URL_SECURE + RelayRunSvcApi.TOKEN_PATH)
		.setUsername(UUID.randomUUID().toString())
		.setPassword(UUID.randomUUID().toString())
		.setClientId(UUID.randomUUID().toString())
		.setClient(new ApacheClient(UnsafeHttpsClient.createUnsafeClient()))
		.setEndpoint(TEST_URL_SECURE).setLogLevel(LogLevel.FULL).build()
		.create(RelayRunSvcApi.class);
	
	private Runner createRunner(String name, String color, String location){
		Runner runner = new Runner();
		runner.setColor(color);
		runner.setName(name);
		runner.setStartLocation(location);
		return runner;
	}
	
	private Marker createMarker(float waterdepth, float elevation, double lat, double longitude, long timemarked, long runnerID){
		Marker marker = new Marker();
		marker.setWaterDepth(waterdepth);
		marker.setElevation(elevation);
		marker.setLatitude(lat);
		marker.setLongitude(longitude);
		marker.setTimeMarked(timemarked);
		marker.setRunnerID(runnerID);
		return marker;
	}

	private Baton createBaton(double latitude, double longitude, long time, long runnerID){
		Baton baton = new Baton();
		baton.setLatitude(latitude);
		baton.setLongitude(longitude);
		baton.setTime(time);
		baton.setRunnerID(runnerID);
		return baton;
	}
	
	
	@Before
	public void setup(){
		
	}
	
	@After
	public void teardown(){
		relayRunSvcApi.clearBatonData();
		relayRunSvcApi.clearMarkers();
		relayRunSvcApi.clearRunners();
	}
	
	@Test
	public void testBatonAPI() throws Exception{
		Runner runner1 = createRunner("Tom","blue","CVS Drugs");
		runner1 = relayRunSvcApi.submitRunner(runner1);
		Baton baton1 = createBaton(100.0,38.71, 1001000L, runner1.getId());
		Baton baton2 = createBaton(100.0,38.72, 1002000L, runner1.getId());
		relayRunSvcApi.submitBaton(baton1);
		relayRunSvcApi.submitBaton(baton2);
		Collection<Baton> batons = relayRunSvcApi.getBatonData();
		assertTrue(batons.size() > 1);
	}
	
	@Test
	public void testMarkerAPI() throws Exception{
		Runner runner1 = createRunner("Tom","blue","CVS Drugs");
		Runner runner2 = createRunner("Phil","orange","Strawberry Village");
		Marker marker1 = createMarker(1.0f, 0.0f, 100.0, 38.71, 10001000L, runner1.getId());
		Marker marker2 = createMarker(0.0f, 2.0f, 100.0, 38.72, 10002000L, runner1.getId());
		relayRunSvcApi.submitMarker(marker1);
		relayRunSvcApi.submitMarker(marker2);
		Marker marker3 = createMarker(1.0f, 0.0f, 100.0, 38.73, 10003000L, runner2.getId());
		Marker marker4 = createMarker(0.0f, 2.0f, 100.0, 38.74, 10004000L, runner2.getId());
		relayRunSvcApi.submitMarker(marker3);
		relayRunSvcApi.submitMarker(marker4);
		Collection<Marker> markers = relayRunSvcApi.getMarkers();
		assertTrue(markers.size() > 3);
	}
	
	
	@Test
	public void testSubmitRunner() throws Exception{
		Runner runner1 = createRunner("Tom","blue","CVS Drugs");
		Runner runner2 = createRunner("Phil","orange","Strawberry Village");
		relayRunSvcApi.submitRunner(runner1);
		relayRunSvcApi.submitRunner(runner2);
		assertTrue(true);
		Collection<Runner> runners = relayRunSvcApi.getRunners();
		assertTrue(runners.size() > 1);
	}
	
	
	
}
