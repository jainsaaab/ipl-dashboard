import { React, useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { MatchDetailCard } from '../components/MatchDetailCard';
import { NavigationBar } from '../components/NavigationBar';
import { YearSelector } from '../components/YearSelector';
import { v4 as uuidv4 } from 'uuid';

import './MatchPage.scss';
import { ApiHandler } from '../handlers/ApiHandler';

export const MatchPage = () => {
    const [matches, setMatches] = useState([]);
    const { teamName, year } = useParams();

    useEffect(() => ApiHandler.getMatches(teamName, year).then(setMatches), [teamName, year]);

    return (
        <div>
            <NavigationBar />
            <div className="MatchPage">
                <div className='year-selector'>
                    <h3>Select Year</h3>
                    <YearSelector teamName={teamName} />
                </div>
                <div>
                    <h1 className='page-heading'>{teamName} matches in {year}</h1>
                    {
                        matches
                            .map(match => <MatchDetailCard
                                match={match}
                                teamName={teamName}
                                key={match.id} />)
                    }
                </div>
            </div>
        </div>
    );
}
