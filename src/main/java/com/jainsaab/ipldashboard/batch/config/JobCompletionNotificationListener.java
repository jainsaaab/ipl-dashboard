package com.jainsaab.ipldashboard.batch.config;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.jainsaab.ipldashboard.model.Team;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {
	private final EntityManager em;

	@Override
	@Transactional
	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			log.info("!!! Job Finished !!!");

			Map<String, Team> teamData = new HashMap<>();

			em.createQuery("select m.team1, count(*) from Match m group by m.team1", Object[].class).getResultList()
					.stream().map(e -> new Team((String) e[0], (long) e[1]))
					.forEach(team -> teamData.put(team.getTeamName(), team));

			em.createQuery("select m.team2, count(*) from Match m group by m.team2", Object[].class).getResultList()
					.stream().forEach(e -> {
						Team team = teamData.get((String) e[0]);

						if (null != team) {
							team.setTotalMatches(team.getTotalMatches() + (long) e[1]);
						} else {
							teamData.put((String) e[0], new Team((String) e[0], (long) e[1]));
						}
					});

			em.createQuery("select m.matchWinner, count(*) from Match m group by m.matchWinner", Object[].class)
					.getResultList().stream().forEach(e -> {
						Team team = teamData.get((String) e[0]);
						if (null != team)
							team.setTotalWins((long) e[1]);
						else
							log.info("team '{}' is not present in map", e[0]);
					});

			teamData.values().forEach(team -> em.persist(team));

//			em.createQuery("select t from Team t", Team.class)
//			.getResultList()
//			.forEach(team -> log.info("{}", team));
		}
	}
}
