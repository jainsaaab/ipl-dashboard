import { React } from 'react';
import { Link } from 'react-router-dom';

import './YearSelector.scss'

export const YearSelector = ({ teamName, years }) => {
    return (
        <ol className='YearSelector'>
            {
                years.map(year =>
                (
                    <li key={year}>
                        <Link to={`/teams/${teamName}/matches/${year}`}> {year} </Link>
                    </li>
                )
                )
            }
        </ol>
    )
}