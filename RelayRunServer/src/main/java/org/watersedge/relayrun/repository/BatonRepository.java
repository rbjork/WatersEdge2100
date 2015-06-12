package org.watersedge.relayrun.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BatonRepository extends CrudRepository<Baton, Long> {

}
