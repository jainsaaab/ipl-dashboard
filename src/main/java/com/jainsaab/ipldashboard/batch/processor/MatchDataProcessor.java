package com.jainsaab.ipldashboard.batch.processor;

import java.time.LocalDate;

import org.springframework.batch.item.ItemProcessor;

import com.jainsaab.ipldashboard.batch.data.MatchInput;
import com.jainsaab.ipldashboard.model.Match;

public class MatchDataProcessor implements ItemProcessor<MatchInput, Match> {

	@Override
	public Match process(final MatchInput matchInput) throws Exception {
		Match match = new Match();

		match.setId(Long.valueOf(matchInput.getId()));
		match.setCity(matchInput.getCity());
		match.setDate(LocalDate.parse(matchInput.getDate()));
		match.setPlayerOfMatch(matchInput.getPlayerOfMatch());
		match.setVenue(matchInput.getVenue());

		// set team 1 and team 2 depending on innings order
		{
			String firstInningsTeam, secondInningsTeam;

			if ("bat".equalsIgnoreCase(matchInput.getTossDecision())) {
				firstInningsTeam = matchInput.getTossWinner();
				secondInningsTeam = firstInningsTeam.equals(matchInput.getTeam1()) ? matchInput.getTeam2()
						: matchInput.getTeam1();
			} else {
				secondInningsTeam = matchInput.getTossWinner();
				firstInningsTeam = secondInningsTeam.equals(matchInput.getTeam1()) ? matchInput.getTeam2()
						: matchInput.getTeam1();
			}

			match.setTeam1(firstInningsTeam);
			match.setTeam2(secondInningsTeam);
		}

		match.setTossWinner(matchInput.getTossWinner());
		match.setTossDecision(matchInput.getTossDecision());
		match.setMatchWinner(matchInput.getWinner());
		match.setResult(matchInput.getResult());
		match.setResultMargin(matchInput.getResultMargin());
		match.setUmpire1(matchInput.getUmpire1());
		match.setUmpire2(matchInput.getUmpire2());

		return match;
	}

}
