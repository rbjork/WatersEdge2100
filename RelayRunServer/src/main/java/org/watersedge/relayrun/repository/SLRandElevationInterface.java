package org.watersedge.relayrun.repository;

import org.postgis.Point;
import org.postgis.Polygon;
import org.postgis.*;
public interface SLRandElevationInterface {
	public Polygon findPolygonContainingPoint(Point p);
	public Polygon findPolygonIntersectingLine(LineString line);
	
}
