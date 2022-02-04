import { React } from 'react';
import './ErrorMsg.scss'

export const ErrorMsg = ({ message }) => <p className="errorMsg">{JSON.stringify(message, null, 2)}</p>