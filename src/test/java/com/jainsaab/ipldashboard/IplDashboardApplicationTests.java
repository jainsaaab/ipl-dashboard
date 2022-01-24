package com.jainsaab.ipldashboard;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jainsaab.ipldashboard.controller.TeamController;

@SpringBootTest
class IplDashboardApplicationTests {
	@Autowired
	TeamController teamController;

	@Test
	void contextLoads() {
		assertThat(teamController).isNotNull();
	}

}
