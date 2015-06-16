package org.watersedge.relayrun;

import java.security.Principal;
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
import org.watersedge.relayrun.repository.Runner;
import org.watersedge.relayrun.repository.RunnerRepository;

import com.google.common.collect.Lists;


@Controller
public class MarkerController {
	
	@Autowired
	private MarkerRepository markers;
	
	@Autowired
	private RunnerRepository runners;
	
	// Users: Runner only
	@RequestMapping(value=RelayRunSvcApi.MARKER_SVC_PATH + "/add", method=RequestMethod.POST)
	public @ResponseBody boolean submitMarker(@RequestBody Marker marker, Principal p){
		String name = p.getName();
		Runner runner = runners.findByUsername(name);
		if(runner != null){
			markers.save(marker);
		}
		return true;
	}
	
	// Users: admin
	@RequestMapping(value=RelayRunSvcApi.MARKER_SVC_PATH + "/clear", method=RequestMethod.GET)
	public @ResponseBody boolean clearMarkers(Principal p){
		if(p.getName().equals("admin")){
			markers.deleteAll();
		}
		return true;
	}
	
	
	// Users: Anybody
	@RequestMapping(value=RelayRunSvcApi.MARKER_SVC_PATH, method=RequestMethod.GET)
	public @ResponseBody Collection<Marker> getMarkers(){
		return Lists.newArrayList(markers.findAll());
	}
	
	// Users: Anybody
	@RequestMapping(value=RelayRunSvcApi.MARKER_SVC_PATH + "/{runnerID}", method=RequestMethod.GET)
	public @ResponseBody Collection<Marker> getRunnersMarkers(@PathVariable("runnerID") long runnerID){
		return Lists.newArrayList(markers.findByRunnerID(runnerID));
	}
}
