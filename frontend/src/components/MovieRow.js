import React, { useState, useEffect, useRef } from 'react';
import axios from 'axios';
import './MovieRow.css';

const MovieRow = ({ title, categoryName }) => {
  const [movies, setMovies] = useState([]);
  const [currentIndex, setCurrentIndex] = useState(0);
  const itemsPerSlide = 5;
  // Ancho de cada item (imagen + margen); ajusta si es necesario (200px + 10px margen = 210px)
  const itemWidth = 210;
  
  // Referencia al contenedor para controlar el ancho (opcional, para futuras mejoras)
  const containerRef = useRef(null);

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

  const handleNext = () => {
    let newIndex = currentIndex + itemsPerSlide;
    if (newIndex >= movies.length) {
      // Si nos pasamos, volvemos al inicio
      newIndex = 0;
    }
    setCurrentIndex(newIndex);
  };

  const handlePrev = () => {
    let newIndex = currentIndex - itemsPerSlide;
    if (newIndex < 0) {
      // Si retrocedemos de 0, vamos al "final"
      const remainder = movies.length % itemsPerSlide;
      if (remainder === 0) {
        newIndex = movies.length - itemsPerSlide;
      } else {
        newIndex = movies.length - remainder;
      }
    }
    setCurrentIndex(newIndex);
  };

  return (
    <div className="movieRow">
      <h2>{title}</h2>
      <div className="movieRow__wrapper">
        {/* Flecha Izquierda */}
        <button className="movieRow__arrow movieRow__arrow--left" onClick={handlePrev}>
          &#10094;
        </button>

        {/* Contenedor de películas */}
        <div className="movieRow__container" ref={containerRef}>
          <div
            className="movieRow__posters"
            style={{ transform: `translateX(-${currentIndex * itemWidth}px)` }}
          >
            {movies.map(movie => (
              <div key={movie.id} className="movieRow__item">
                <img
                  className="movieRow__poster"
                  src={movie.posterUrl}
                  alt={movie.title}
                />
              </div>
            ))}
          </div>
        </div>

        {/* Flecha Derecha */}
        <button className="movieRow__arrow movieRow__arrow--right" onClick={handleNext}>
          &#10095;
        </button>
      </div>
    </div>
  );
};

export default MovieRow;
