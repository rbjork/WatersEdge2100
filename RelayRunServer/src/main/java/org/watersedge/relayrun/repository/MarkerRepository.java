package org.watersedge.relayrun.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface MarkerRepository extends CrudRepository<Marker, Long> {
	
	public Collection<Marker> findByRunnerID(long id);
	
}
