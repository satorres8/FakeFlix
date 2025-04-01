import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './MovieRow.css';

const MovieRow = ({ title, categoryName }) => {
  const [movies, setMovies] = useState([]);

  useEffect(() => {
    axios
      .get(`http://localhost:8080/movies/category/${categoryName}`)
      .then(response => {
        setMovies(response.data);
      })
      .catch(error => {
        console.error('Error al obtener películas:', error);
      });
  }, [categoryName]);

  return (
    <div className="movieRow">
      <h2>{title}</h2>
      <div className="movieRow__posters">
        {movies.map(movie => (
          <img
            key={movie.id}
            className="movieRow__poster"
            src={movie.posterUrl}
            alt={movie.title}
          />
        ))}
      </div>
    </div>
  );
};

export default MovieRow;
