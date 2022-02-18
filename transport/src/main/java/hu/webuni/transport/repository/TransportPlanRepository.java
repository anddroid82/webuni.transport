package hu.webuni.transport.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hu.webuni.transport.model.TransportPlan;

public interface TransportPlanRepository extends JpaRepository<TransportPlan, Long> {

	@EntityGraph("TransportPlan.full")
	@Query("select t from TransportPlan t where id=:id")
	Optional<TransportPlan> findByIdFull(Long id);
	
}
