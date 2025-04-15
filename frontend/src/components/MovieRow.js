import React, { useState, useEffect, useRef } from 'react';
import axios from 'axios';
import './MovieRow.css';

const MovieRow = ({ title, categoryName }) => {
  const [movies, setMovies] = useState([]);
  const [currentIndex, setCurrentIndex] = useState(0);
  const itemsPerSlide = 5;
  const itemWidth = 210; // Ancho + margen (ajústalo a tus necesidades)

  // Estado para mostrar u ocultar el reproductor
  const [showPlayer, setShowPlayer] = useState(false);

  // Referencia al contenedor (por si quieres calcular ancho dinámico, etc.)
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

  // Maneja la flecha de siguiente
  const handleNext = () => {
    let newIndex = currentIndex + itemsPerSlide;
    if (newIndex >= movies.length) {
      newIndex = 0; // vuelve al inicio si te pasas
    }
    setCurrentIndex(newIndex);
  };

  // Maneja la flecha de anterior
  const handlePrev = () => {
    let newIndex = currentIndex - itemsPerSlide;
    if (newIndex < 0) {
      // si retrocedemos más allá del inicio
      const remainder = movies.length % itemsPerSlide;
      newIndex = remainder === 0
        ? movies.length - itemsPerSlide
        : movies.length - remainder;
    }
    setCurrentIndex(newIndex);
  };

  // Cuando se hace clic en cualquier portada
  const handlePosterClick = () => {
    // Abrimos el reproductor de video
    setShowPlayer(true);
  };

  // Para cerrar el reproductor
  const closePlayer = () => {
    setShowPlayer(false);
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
                  onClick={handlePosterClick} // Al hacer clic, abrimos el video
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

      {/* Renderiza el reproductor si showPlayer es true */}
      {showPlayer && (
        <div className="videoModal">
          <div className="videoModal__overlay" onClick={closePlayer}></div>
          <div className="videoModal__content">
            <button className="videoModal__closeBtn" onClick={closePlayer}>
              X
            </button>
            <video
              className="videoModal__player"
              src="/VIDEO.mp4"
              autoPlay
              controls
            />
          </div>
        </div>
      )}
    </div>
  );
};

export default MovieRow;
