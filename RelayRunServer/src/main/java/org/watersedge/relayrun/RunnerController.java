package org.watersedge.relayrun;

import java.security.Principal;
import java.util.Collection;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.watersedge.relayrun.repository.Runner;
import org.watersedge.relayrun.repository.RunnerRepository;
import org.springframework.ui.Model;

import com.google.common.collect.Lists;

@Controller
public class RunnerController {
	
	@Autowired
	private RunnerRepository runners;
	
	// User: admin only
	@RequestMapping(value=RelayRunSvcApi.RUNNER_SVC_PATH + "/add", method=RequestMethod.POST)
	public @ResponseBody Runner submitRunner(@RequestBody Runner runner, Principal p){
		if(p.getName().equals("admin")){
			runner = runners.save(runner);
			return runner;
		}else{
			return runner;
		}
		
	}
	
	// Users: admin, runners and public
	@RequestMapping(value=RelayRunSvcApi.RUNNER_SVC_PATH, method=RequestMethod.GET)
	public @ResponseBody Collection<Runner> getRunners(Principal p){
		Runner runner = runners.findByUsername(p.getName());
		if(p.getName().equals("admin") || runner != null){
			return Lists.newArrayList(runners.findAll());
		}else{
			return null;
		}
	}
	
	
	// Users: admin only
	@RequestMapping(value=RelayRunSvcApi.RUNNER_SVC_PATH + "/{runnerID}", method=RequestMethod.GET)
	public @ResponseBody boolean removeRunner(@PathVariable("runnerID") long runnerID, Principal p, HttpServletResponse response){
		if(p.getName().equals("admin")){
			runners.delete(runnerID);
			return true;
		}else{
			return true;
		}
	}
	
	// Users: admin only
	@RequestMapping(value=RelayRunSvcApi.RUNNER_SVC_PATH + "/clear", method=RequestMethod.GET)
	public @ResponseBody boolean clearRunners(Principal p, HttpServletResponse response){
		if(p.getName().equals("admin")){
			runners.deleteAll();
			return true;
		}else{
			//response.setStatus(HttpStatus.BAD_REQUEST.value());
			return true;
		}
	}
	
}
