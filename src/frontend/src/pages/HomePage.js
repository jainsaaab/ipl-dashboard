import { React, useState, useEffect } from 'react';

import { ApiHandler } from '../handlers/ApiHandler';

import { TeamTile } from '../components/TeamTile';
import { NavigationBar } from '../components/NavigationBar';

import './HomePage.scss'

export const HomePage = () => {
    const [teams, setTeams] = useState([]);

    useEffect(() => ApiHandler.getAllTeams().then(setTeams), []);

    return (
        <div>
            <NavigationBar />
            <div className='HomePage'>
                <div className='header-section'>
                    <h1 className='app-name'>IPL Dashboard</h1>
                </div>
                <div className='team-grid'>
                    {
                        teams.map(
                            team => <TeamTile teamName={team.teamName} key={team.id} />
                        )
                    }
                </div>
            </div>
        </div>
    );
}