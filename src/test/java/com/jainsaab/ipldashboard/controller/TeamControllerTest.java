package com.jainsaab.ipldashboard.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jainsaab.ipldashboard.model.MatchesForTeamByYearResponse;
import com.jainsaab.ipldashboard.model.Team;

@SpringBootTest
@AutoConfigureMockMvc
class TeamControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper mapper;

	private JacksonTester<List<Team>> allTeamsResponse;
	private JacksonTester<Team> getTeamResponse;
	private JacksonTester<MatchesForTeamByYearResponse> getMatchesForTeamByYearResponse;
	
	@BeforeEach
	void init() {
		JacksonTester.initFields(this, mapper);
	}
	
	// @GetMapping("/team/{teamName}/matches")
	// public List<Match> getMatchesForTeam(@PathVariable String teamName, @RequestParam int year) {
	@Test
	void test_getMatchesForTeam_BadRequest() throws Exception {
		MockHttpServletResponse responseObj = mockMvc
				.perform(get("/team/Kolkata Knight Riders/matches")
						.accept(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();
	
		assertEquals(HttpStatus.OK.value(), responseObj.getStatus());
		MatchesForTeamByYearResponse response = getMatchesForTeamByYearResponse.readObject(new ByteArrayInputStream(responseObj.getContentAsByteArray()));
		
		assertThat(response).isNotNull();
		assertThat(response.getSelectedYear()).isEqualTo(2020);
	}
	
	@Test
	void test_getMatchesForTeam_valid() throws Exception {
		MockHttpServletResponse responseObj = mockMvc
				.perform(get("/team/Kolkata Knight Riders/matches")
						.accept(MediaType.APPLICATION_JSON)
						.queryParam("year", "2018"))
				.andReturn().getResponse();
		
		assertEquals(HttpStatus.OK.value(), responseObj.getStatus());
		MatchesForTeamByYearResponse response = getMatchesForTeamByYearResponse.readObject(new ByteArrayInputStream(responseObj.getContentAsByteArray()));
		
		assertThat(response).isNotNull();
		assertThat(response.getMatchesForSelectedYear()).hasSizeGreaterThan(10);
	}
	
	@Test
	void test_getMatchesForTeam_invalidYear() throws Exception {
		MockHttpServletResponse responseObj = mockMvc
				.perform(get("/team/Kolkata Knight Riders/matches")
						.accept(MediaType.APPLICATION_JSON)
						.queryParam("year", "2000"))
				.andReturn().getResponse();
		
		assertEquals(HttpStatus.OK.value(), responseObj.getStatus());
		MatchesForTeamByYearResponse response = getMatchesForTeamByYearResponse.readObject(new ByteArrayInputStream(responseObj.getContentAsByteArray()));
	
		assertThat(response).isNotNull();
		assertThat(response.getMatchesForSelectedYear()).hasSize(0);
	}

	// public Iterable<Team> getAllTeams()
	@Test
	void test_getAllTeams_size() throws Exception {
		MockHttpServletResponse responseObj = mockMvc.perform(get("/teams").accept(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();

		assertEquals(HttpStatus.OK.value(), responseObj.getStatus());

		List<Team> response = allTeamsResponse
				.readObject(new ByteArrayInputStream(responseObj.getContentAsByteArray()));

		assertThat(response).hasSizeGreaterThanOrEqualTo(10);
	}

	@Test
	void test_getAllTeams_elements() throws Exception {
		MockHttpServletResponse responseObj = mockMvc.perform(get("/teams").accept(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();

		assertEquals(HttpStatus.OK.value(), responseObj.getStatus());

		List<Team> response = allTeamsResponse
				.readObject(new ByteArrayInputStream(responseObj.getContentAsByteArray()));

		boolean hasChennaiSuperKings = response.stream()
				.map(Team::getTeamName)
				.anyMatch("Chennai Super Kings"::equals);

		assertTrue("response does not contain 'Chennai Super Kings' team", hasChennaiSuperKings);
	}

//	@GetMapping("/team/{teamName}")
//	public Team getTeam(@PathVariable String teamName) {
	@Test
	void test_getTeam() throws Exception {
		MockHttpServletResponse responseObj = mockMvc.perform(get("/team/Kolkata Knight Riders").accept(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();

		assertEquals(HttpStatus.OK.value(), responseObj.getStatus());
		
		Team teamKkr = getTeamResponse.readObject(new ByteArrayInputStream(responseObj.getContentAsByteArray()));
		
		assertThat(teamKkr).hasFieldOrPropertyWithValue("teamName", "Kolkata Knight Riders");
	}
	
	@Test
	void test_getTeam_notFound() throws Exception {
		MockHttpServletResponse responseObj = mockMvc.perform(get("/team/Kolkata Knight Riders").accept(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();

		assertEquals(HttpStatus.OK.value(), responseObj.getStatus());
		
		Team teamKkr = getTeamResponse.readObject(new ByteArrayInputStream(responseObj.getContentAsByteArray()));
		
		assertThat(teamKkr).hasFieldOrPropertyWithValue("teamName", "Kolkata Knight Riders");
	}
}
