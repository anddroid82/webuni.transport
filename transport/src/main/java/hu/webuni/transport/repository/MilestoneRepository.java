package hu.webuni.transport.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hu.webuni.transport.model.Milestone;

public interface MilestoneRepository extends JpaRepository<Milestone, Long> {

	@EntityGraph("Milestone.full")
	@Query("select m from Milestone m where id=:id")
	Optional<Milestone> findByIdFull(Long id);
	
}
