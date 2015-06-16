package org.watersedge.relayrun;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.watersedge.relayrun.repository.Baton;
import org.watersedge.relayrun.repository.BatonRepository;
import org.watersedge.relayrun.repository.Runner;
import org.watersedge.relayrun.repository.RunnerRepository;
import org.springframework.ui.Model;

import com.google.common.collect.Lists;

@Controller
public class BatonController {
	
	@Autowired
	private BatonRepository batonData;
	
	@Autowired
	private RunnerRepository runners;
	
	// Users: Runners only
	@RequestMapping(value=RelayRunSvcApi.BATON_SVC_PATH + "/add", method=RequestMethod.POST)
	public @ResponseBody boolean submitBaton(@RequestBody Baton baton, Principal p){
		String name = p.getName();
		Runner runner = runners.findByUsername(name);
		if(runner != null){
			batonData.save(baton);
		}
		return true;
	}
	
	// Users: admin only
	@RequestMapping(value=RelayRunSvcApi.BATON_SVC_PATH + "/clear", method=RequestMethod.GET)
	public @ResponseBody boolean clearBatonData(Principal p){
		if(p.getName().equals("admin")){
			batonData.deleteAll();
		}
		return true;
	}
	/** GET BATON DATA **/
	// Users: runners
	@RequestMapping(value=RelayRunSvcApi.BATON_SVC_PATH, method=RequestMethod.GET)
	public @ResponseBody Collection<Baton> getBatonData(){
		return Lists.newArrayList(batonData.findAll());
	}
	
	// Users: admin, public
	@RequestMapping(value=RelayRunSvcApi.BATON_SVC_PATH + "/web", method=RequestMethod.GET)
	public Map<String,Object> getBatonDataWeb(){
		Map<String,Object> model = new HashMap<String,Object>();
		ArrayList<Baton> batons = Lists.newArrayList( batonData.findAll());
		model.put("batons",batons.toString());
		return model;
	}
	
	
}
