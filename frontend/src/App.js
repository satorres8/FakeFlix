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
      <MovieRow title="Creemos que te podría gustar" categoryName="Creemos que te podría gustar" />
      <MovieRow title="Películas aclamadas por la crítica" categoryName="Películas aclamadas por la crítica" />
      <MovieRow title="Novedades en Fakeflix" categoryName="Novedades en Fakeflix" />
      <footer className="App-footer">
        <p>&copy; 2023 Fakeflix. Derechos reservados.</p>
      </footer>
    </div>
  );
}

export default App;
