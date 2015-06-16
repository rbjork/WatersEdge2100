package org.watersedge.relayrun.repository;

import java.sql.*; 
import java.util.*; 
import java.lang.*; 


import javax.sql.DataSource;
import org.postgresql.*;

import org.postgis.*; 

public class SLRandElevationData implements SLRandElevationInterface{

	private DataSource ds;
	private java.sql.Connection conn;
	
	public SLRandElevationData(DataSource ds){
		this.ds = ds;    
	}
	
	public Polygon findPolygonContainingPoint(Point p){
		Polygon pg = null;
		try{
			// Class.forName("org.postgresql.Driver"); 
		    // String url = "jdbc:postgresql://localhost/relayrundb"; 
			// conn = DriverManager.getConnection(url, "postgres", "");
			 conn = ds.getConnection(); 
			 ((org.postgresql.PGConnection)conn).addDataType("point", org.postgis.Point.class);
			 ((org.postgresql.PGConnection)conn).addDataType("polygon", org.postgis.Polygon.class);
			 Statement s = conn.createStatement();
			 ResultSet r = s.executeQuery("SELECT polygon.id, point.id FROM slrtable3ft WHERE ST_Contains(point.geom, polygon.geom)"); 
			 while( r.next() ) { 
			      PGgeometry geom = (PGgeometry)r.getObject(1); 
			      if(geom.getGeoType() == org.postgis.GeometryCollection.POLYGON){
			    	  pg = (Polygon)geom.getGeometry();
			    	  break;
			      }
			 } 
			s.close(); 
			conn.close(); 
		}catch(Exception e){
			
		}
		return pg; // stub
	}
	
	public Polygon findPolygonIntersectingLine(LineString line){
		Polygon pg = null;
		try{
			conn = ds.getConnection(); 
			((org.postgresql.PGConnection)conn).addDataType("line", org.postgis.LineString.class);
			((org.postgresql.PGConnection)conn).addDataType("polygon", org.postgis.Polygon.class);
			Statement s = conn.createStatement();
			ResultSet r = s.executeQuery("SELECT polygon.id, line.id FROM slrtable3ft WHERE ST_Intersects(line.geom, polygons.geom)");
			while( r.next() ) { 
			      PGgeometry geom = (PGgeometry)r.getObject(1); 
			      if(geom.getGeoType() == org.postgis.GeometryCollection.POLYGON){
			    	  pg = (Polygon)geom.getGeometry();
			    	  break;
			      }
			 } 
			s.close(); 
			conn.close(); 
		}catch(Exception e){
			
		}
		return pg; // stub
	}
}
