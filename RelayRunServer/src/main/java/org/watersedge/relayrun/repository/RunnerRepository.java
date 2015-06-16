package org.watersedge.relayrun.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RunnerRepository extends CrudRepository<Runner, Long> {
	public Runner findByName(String name);
	public Runner findByUsername(String username);
}
