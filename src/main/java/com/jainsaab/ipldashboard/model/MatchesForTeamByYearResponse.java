package com.jainsaab.ipldashboard.model;

import java.util.List;
import java.util.TreeSet;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MatchesForTeamByYearResponse {
	private int selectedYear;
	private TreeSet<Integer> availableYears;
	private List<Match> matchesForSelectedYear;
}