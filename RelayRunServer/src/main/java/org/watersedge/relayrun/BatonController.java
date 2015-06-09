package org.watersedge.relayrun;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.watersedge.relayrun.repository.Baton;
import org.watersedge.relayrun.repository.BatonRepository;

import com.google.common.collect.Lists;

@Controller
public class BatonController {
	
	@Autowired
	private BatonRepository batonData;
	
	@RequestMapping(value=RelayRunSvcApi.BATON_SVC_PATH + "/add", method=RequestMethod.POST)
	public @ResponseBody boolean submitBaton(@RequestBody Baton baton){
		batonData.save(baton);
		return true;
	}
	
	@RequestMapping(value=RelayRunSvcApi.BATON_SVC_PATH, method=RequestMethod.GET)
	public @ResponseBody Collection<Baton> getBatonData(){
		return Lists.newArrayList(batonData.findAll());
	}
	
	@RequestMapping(value=RelayRunSvcApi.BATON_SVC_PATH + "/clear", method=RequestMethod.GET)
	public @ResponseBody boolean clearBatonData(){
		batonData.deleteAll();
		return true;
		
	}
}
