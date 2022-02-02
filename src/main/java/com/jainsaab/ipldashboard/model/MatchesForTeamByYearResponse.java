package com.jainsaab.ipldashboard.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MatchesForTeamByYearResponse {
	private int selectedYear;
	private List<Integer> availableYears;
	private List<Match> matchesForSelectedYear;
}