import { React, useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { MatchDetailCard } from '../components/MatchDetailCard';
import { NavigationBar } from '../components/NavigationBar';
import { YearSelector } from '../components/YearSelector';

import './MatchPage.scss';
import { ApiHandler } from '../handlers/ApiHandler';

export const MatchPage = () => {
    const [matches, setMatches] = useState({selectedYear: 0, availableYears: [], matchesForSelectedYear: []});
    let { teamName, year } = useParams();

    if (undefined === year) year = "";

    useEffect(() => ApiHandler.getMatches(teamName, year).then(setMatches), [teamName, year]);

    return (
        <div>
            <NavigationBar />
            <div className="MatchPage">
                <div className='year-selector'>
                    <h3>Select Year</h3>
                    <YearSelector teamName={teamName} years={matches.availableYears} />
                </div>
                <div>
                    <h1 className='page-heading'>{teamName} matches in {matches.selectedYear}</h1>
                    {
                        matches.matchesForSelectedYear
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
