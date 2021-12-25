package com.jainsaab.ipldashboard.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.jainsaab.ipldashboard.model.Match;

public interface MatchRepository extends CrudRepository<Match, Long> {
	List<Match> findByTeam1OrTeam2OrderByDateDesc(String teamName1, String teamName2, Pageable pagable);

	@Query("select m from Match m where (m.team1 = :teamName or m.team2 = :teamName) and m.date between :startDate and :endDate order by date desc")
	List<Match> getMatchesByTeamBetweenDates(String teamName, LocalDate startDate, LocalDate endDate);

	List<Match> getByTeam1AndDateBetweenOrTeam2AndDateBetweenOrderByDateDesc(String teamName1, LocalDate date1,
			LocalDate date2, String teamName2, LocalDate date3, LocalDate date4);

	default List<Match> findLatestMatchesByTeam(String teamName, int count) {
		return findByTeam1OrTeam2OrderByDateDesc(teamName, teamName, PageRequest.of(0, count));
	}
}
