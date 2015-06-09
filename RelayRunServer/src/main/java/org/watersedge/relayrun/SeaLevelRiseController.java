package org.watersedge.relayrun;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

public class SeaLevelRiseController {
	
	// Three approaches for data storage and querying for PostGIS: 
	//	1) Use raster image file of slr to get point water depth at assumed SLR
	//	2) Having used raster2pgsql to convert slr tiff to table data in PostGIS, query database with lat and long
	// 	3) Use geom intersection of point with slr polygons of interests to test intercept
	
	@RequestMapping(value=RelayRunSvcApi.GEO_SVC_PATH + "/waterdepth/{longitude}/{latitude}", method=RequestMethod.GET)
	public @ResponseBody int getWaterDepthAt(@PathVariable("longitude") long longitude, @PathVariable("latitude") long latitude){
		
		
		return 0;
	}
	
	// This feature requires a compass reading - perhaps as an accessory to the baton. Baton is pointed in direction of interest
	@RequestMapping(value=RelayRunSvcApi.GEO_SVC_PATH + "/waterdepth/direction100/{longitude}/{latitude}/{compass}", method=RequestMethod.GET)
	public @ResponseBody int getWaterDeptForDirectionAt100Meters(@PathVariable("longitude") long longitude, @PathVariable("latitude") long latitude, float compass){
		
		return 1;
	}
	
	// This feature requires a compass reading - perhaps as an accessory to the baton.  Baton is pointed in direction of interest
	@RequestMapping(value=RelayRunSvcApi.GEO_SVC_PATH + "/waterdepth/direction/{longitude}/{latitude}/{compass}", method=RequestMethod.GET)
	public @ResponseBody int getDistanceToWatersEdgeForDirection(@PathVariable("longitude") long longitude, @PathVariable("latitude") long latitude, float compass){
		
		return 1;
	}
}
