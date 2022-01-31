import { React } from 'react';
import { Link } from 'react-router-dom';

import './NavigationBar.scss'

export const NavigationBar = () => {
    return (
        <div>
            <Link to={"/"}>Home</Link>
        </div>
    );
}