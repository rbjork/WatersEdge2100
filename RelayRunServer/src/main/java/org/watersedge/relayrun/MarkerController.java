package org.watersedge.relayrun;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.watersedge.relayrun.repository.Marker;
import org.watersedge.relayrun.repository.MarkerRepository;

import com.google.common.collect.Lists;


@Controller
public class MarkerController {
	
	@Autowired
	private MarkerRepository markers;
	
	@RequestMapping(value=RelayRunSvcApi.MARKER_SVC_PATH + "/add", method=RequestMethod.POST)
	public @ResponseBody boolean submitMarker(@RequestBody Marker marker){
		markers.save(marker);
		return true;
	}
	
	@RequestMapping(value=RelayRunSvcApi.MARKER_SVC_PATH, method=RequestMethod.GET)
	public @ResponseBody Collection<Marker> getMarkers(){
		return Lists.newArrayList(markers.findAll());
	}
	
	@RequestMapping(value=RelayRunSvcApi.MARKER_SVC_PATH + "/{runnerID}", method=RequestMethod.GET)
	public @ResponseBody Collection<Marker> getRunnersMarkers(@PathVariable("runnerID") long runnerID){
		return Lists.newArrayList(markers.findByRunnerID(runnerID));
	}
	
	@RequestMapping(value=RelayRunSvcApi.MARKER_SVC_PATH + "/clear", method=RequestMethod.GET)
	public @ResponseBody boolean clearMarkers(){
		markers.deleteAll();
		return true;
	}
}
