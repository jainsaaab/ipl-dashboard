package com.jainsaab.ipldashboard.batch.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchInput {
	private String id;
	private String city;
	private String date;
	private String playerOfMatch;
	private String venue;
	private String neutralVenue;
	private String team1;
	private String team2;
	private String tossWinner;
	private String tossDecision;
	private String winner;
	private String result;
	private String resultMargin;
	private String eliminator;
	private String method;
	private String umpire1;
	private String umpire2;
}
