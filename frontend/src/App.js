import React from 'react';
import MovieRow from './components/MovieRow';
import './App.css';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <h1>Fakeflix</h1>
      </header>
      {/* Ejemplo de filas por categoría */}
      <MovieRow title="Popular" categoryName="Popular" />
    </div>
  );
}

export default App;
