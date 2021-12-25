package com.jainsaab.ipldashboard.repository;

import org.springframework.data.repository.CrudRepository;

import com.jainsaab.ipldashboard.model.Team;

public interface TeamRepository extends CrudRepository<Team, Long> {
	Team findByTeamName(String teamName);
}