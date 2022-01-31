package com.jainsaab.ipldashboard.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.jainsaab.ipldashboard.model.Team;

public interface TeamRepository extends CrudRepository<Team, Long> {
	Optional<Team> findByTeamName(String teamName);
}