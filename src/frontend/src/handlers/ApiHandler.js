import { v4 as uuidv4 } from "uuid";

const getApiCall = async (endpoint) => {
    const response = await fetch(endpoint, {
        method: "GET",
        headers: {
            "external-ref-id": `react-frontend-${uuidv4()}`
        }
    })

    return {ok: response.ok, body: await response.json()};
}

const getAllTeams = async () => {
    return await getApiCall(`${process.env.REACT_APP_DATA_API_ROOT_URL}/teams`);
}

const getTeamDetails = async (teamName) => {
    return await getApiCall(`${process.env.REACT_APP_DATA_API_ROOT_URL}/team/${teamName}`);
}

const getMatches = async (teamName, year) => {
    return await getApiCall(`${process.env.REACT_APP_DATA_API_ROOT_URL}/team/${teamName}/matches?year=${year}`);
}

export const ApiHandler = { getAllTeams, getTeamDetails, getMatches }