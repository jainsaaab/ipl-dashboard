import { React, useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { MatchDetailCard } from '../components/MatchDetailCard';
import { NavigationBar } from '../components/NavigationBar';
import { YearSelector } from '../components/YearSelector';

import './MatchPage.scss';
import { ApiHandler } from '../handlers/ApiHandler';
import { ErrorMsg } from '../components/ErrorMsg';

export const MatchPage = () => {
    const [matches, setMatches] = useState({ selectedYear: 0, availableYears: [], matchesForSelectedYear: [] });
    const [error, setError] = useState(false);
    const [errMsg, setErrMsg] = useState("");

    let { teamName, year } = useParams();

    if (undefined === year) year = "";

    useEffect(() => {
        ApiHandler.getMatches(teamName, year)
            .then(resp => {
                setError(!resp.ok);
                if (resp.ok) setMatches(resp.body);
                else setErrMsg(resp.body);
            })
    }, [teamName, year]);

    if (error) return (
        <div>
            <NavigationBar />
            <ErrorMsg message={errMsg} />
        </div>
    )

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
