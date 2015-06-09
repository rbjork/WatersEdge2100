package org.watersedge.relayrun;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.watersedge.relayrun.repository.Runner;
import org.watersedge.relayrun.repository.RunnerRepository;

import com.google.common.collect.Lists;

@Controller
public class RunnerController {
	
	@Autowired
	private RunnerRepository runners;
	
	@RequestMapping(value=RelayRunSvcApi.RUNNER_SVC_PATH + "/add", method=RequestMethod.POST)
	public @ResponseBody Runner submitRunner(@RequestBody Runner runner){
		runner = runners.save(runner);
		return runner;
	}
	
	@RequestMapping(value=RelayRunSvcApi.RUNNER_SVC_PATH, method=RequestMethod.GET)
	public @ResponseBody Collection<Runner> getRunners(){
		return Lists.newArrayList(runners.findAll());
	}
	
	@RequestMapping(value=RelayRunSvcApi.RUNNER_SVC_PATH + "/{runnerID}", method=RequestMethod.GET)
	public @ResponseBody boolean removeRunner(@PathVariable("runnerID") long runnerID){
		return true;
	}
	
	@RequestMapping(value=RelayRunSvcApi.RUNNER_SVC_PATH + "/clear", method=RequestMethod.GET)
	public @ResponseBody boolean clearRunners(){
		runners.deleteAll();
		return true;
	}
	
}
