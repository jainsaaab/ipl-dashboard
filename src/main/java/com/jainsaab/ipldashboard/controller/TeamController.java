package com.jainsaab.ipldashboard.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jainsaab.ipldashboard.model.Match;
import com.jainsaab.ipldashboard.model.MatchesForTeamByYearResponse;
import com.jainsaab.ipldashboard.model.Team;
import com.jainsaab.ipldashboard.repository.MatchRepository;
import com.jainsaab.ipldashboard.repository.TeamRepository;
import com.jainsaab.ipldashboard.utils.Utility;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequiredArgsConstructor
public class TeamController {
	private final TeamRepository teamRepository;
	private final MatchRepository matchRepository;
	private final Utility utility;

	@GetMapping("/teams")
	public Iterable<Team> getAllTeams() {
		log.info("request came for '/team");
		var response = teamRepository.findAll();
		log.info("response :: {}", () -> utility.writeObjectAsString(response));
		return response;
	}

	@GetMapping("/team/{teamName}")
	public Team getTeam(@PathVariable String teamName) {
		log.info("request came for '/team/{}'", teamName);
		Team team = teamRepository.findByTeamName(teamName).orElseThrow(() -> utility
				.prepareIplDashboardException("team with name '%s' does not exist".formatted(teamName), null));

		team.setMatches(matchRepository.findLatestMatchesByTeam(teamName, 4));

		log.info("response :: {}", () -> utility.writeObjectAsString(team));

		return team;
	}

	@GetMapping("/team/{teamName}/matches")
	public ResponseEntity<MatchesForTeamByYearResponse> getMatchesForTeamByYear(@PathVariable String teamName,
			@RequestParam Optional<Integer> year) {
		log.info("request came for '/team/{}/matches', year = '{}'", teamName, year);

		List<Integer> years = matchRepository.getYearsTeamHasPlayed(teamName);
		
		if (years.isEmpty())
			throw utility.prepareIplDashboardException("No matches exist for team with name '%s'".formatted(teamName),
					null);
		
		years.sort((a, b) -> b - a);
		if (year.isEmpty())
			year = years.stream().max((a, b) -> a - b);

		LocalDate startDate = LocalDate.of(year.get(), 1, 1);
		LocalDate endDate = LocalDate.of(year.get() + 1, 1, 1);

		List<Match> matches = matchRepository.getMatchesByTeamBetweenDates(teamName, startDate, endDate);

		MatchesForTeamByYearResponse response = new MatchesForTeamByYearResponse();

		response.setSelectedYear(year.get());
		response.setAvailableYears(years);
		response.setMatchesForSelectedYear(matches);

		log.debug("response :: {}", () -> utility.writeObjectAsString(response));
		return ResponseEntity.ok(response);
	}
}
