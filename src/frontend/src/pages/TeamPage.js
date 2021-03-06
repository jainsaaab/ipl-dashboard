import { React, useEffect, useState } from 'react';
import { useParams, Link } from 'react-router-dom'
import { PieChart } from 'react-minimal-pie-chart';

import { MatchDetailCard } from '../components/MatchDetailCard';
import { MatchSmallCard } from '../components/MatchSmallCard';
import { NavigationBar } from '../components/NavigationBar';


import './TeamPage.scss'
import { ApiHandler } from '../handlers/ApiHandler';

export const TeamPage = () => {
  const [team, setTeam] = useState({ matches: [] });
  const { teamName } = useParams();

  useEffect(() => {
    ApiHandler.getTeamDetails(teamName)
      .then(resp => {
        if (resp.ok) setTeam(resp.body)
      })
  }, [teamName]);


  if (!team || !team.teamName) return (
    <div>
      <NavigationBar />
      <h1>Team Not Found</h1>
    </div>
  )

  return (
    <div>
      <NavigationBar />
      <div className="TeamPage">
        <div className="team-name-section">
          <h1 className="team-name">{team.teamName}</h1>
        </div>

        <div className="win-loss-section">
          Wins / Losses
          <PieChart
            data={[
              { title: 'Losses', value: team.totalMatches - team.totalWins, color: '#a34d5d' },
              { title: 'Wins', value: team.totalWins, color: '#4da375' }
            ]}
          />
        </div>

        <div className="match-detail-section">
          <h3>Latest Matches</h3>
          <MatchDetailCard match={team.matches[0]} teamName={team.teamName} />
        </div>

        {team.matches.slice(1).map(match => <MatchSmallCard match={match} teamName={team.teamName} key={match.id} />)}

        <div className='more-link'>
          <Link to={`/teams/${teamName}/matches/`}> more {'>'} </Link>
        </div>
      </div>
    </div>
  );
}