package org.watersedge.relayrun;

import org.postgis.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.watersedge.relayrun.repository.SLRandElevationInterface;



@Controller
public class SeaLevelRiseController {
	
	java.sql.Connection conn; 
	
	@Autowired
	private SLRandElevationInterface slrdata;
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
	public @ResponseBody String getWaterDeptForDirectionAt100Meters(@PathVariable("longitude") long longitude, @PathVariable("latitude") long latitude, float compass){
		
		Point p1 = new Point();
		p1.setX(longitude);
		p1.setY(latitude);
		Point p2 = new Point();
		p2.setX(p1.getX() + Math.cos(compass)/70); // one degree is roughly 70 miles
		p2.setY(p2.getY() + Math.sin(compass)/70);
		Point[] points = new Point[2];
		points[0] = p1;
		points[1] = p2;	
		
		Polygon plg = slrdata.findPolygonContainingPoint(p2);
		return plg.getValue();
	}
	
	// This feature requires a compass reading - perhaps as an accessory to the baton.  Baton is pointed in direction of interest
	@RequestMapping(value=RelayRunSvcApi.GEO_SVC_PATH + "/waterdepth/direction/{longitude}/{latitude}/{compass}", method=RequestMethod.GET)
	public @ResponseBody String getDistanceToWatersEdgeForDirection(@PathVariable("longitude") long longitude, @PathVariable("latitude") long latitude, float compass){
		Point p1 = new Point();
		
		p1.setX(longitude);
		p1.setY(latitude);
		
		Point p2 = new Point();
		
		p2.setX(p1.getX() + Math.cos(compass)/70); // one degree is roughly 70 miles
		p2.setY(p2.getY() + Math.sin(compass)/70);
		
		Point[] points = new Point[2];
		
		points[0] = p1;
		points[1] = p2;	
		
		LineString ls = new LineString(points);
		Polygon plg = slrdata.findPolygonIntersectingLine(ls);
		return plg.getValue();
	}
}
