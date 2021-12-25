import './App.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { TeamPage } from './pages/TeamPage';

function App() {
  return (
    <div className="App">
      <Router>
        <Routes>
          <Route path="/teams" element={<TeamPage />} />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
