package com.jainsaab.ipldashboard.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jainsaab.ipldashboard.model.Match;
import com.jainsaab.ipldashboard.model.RequestContext;
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
	private final RequestContext requestContext;
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
		Team team = teamRepository.findByTeamName(teamName)
				.orElseThrow(() -> utility.prepareIplDashboardException("team with name '%s' does not exist".formatted(teamName), null));
		
		team.setMatches(matchRepository.findLatestMatchesByTeam(teamName, 4));
		
		log.info("response :: {}", () -> utility.writeObjectAsString(team));

		return team;
	}

	@GetMapping("/team/{teamName}/matches")
	public ResponseEntity<List<Match>> getMatchesForTeam(@PathVariable String teamName, @RequestParam Integer year) {
		log.info("request came for '/team/{}/matches', year = '{}'", teamName, year);
		log.debug("request context :: {}", requestContext);

		LocalDate startDate = LocalDate.of(year, 1, 1);
		LocalDate endDate = LocalDate.of(year + 1, 1, 1);

		List<Match> matches = matchRepository.getMatchesByTeamBetweenDates(teamName, startDate, endDate);

		ResponseEntity<List<Match>> response = new ResponseEntity<List<Match>>(matches, HttpStatus.OK);

		log.debug("response :: {}", () -> utility.writeObjectAsString(matches));
		return response;
	}
}
