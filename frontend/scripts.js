document.addEventListener('DOMContentLoaded', function () {
    init();
});

function init() {
    setupEventListeners();
    showLoginPage();
}

function setupEventListeners() {
    document.getElementById('register-form').addEventListener('submit', handleRegister);
    document.getElementById('login-form').addEventListener('submit', handleLogin);
    document.getElementById('search-form').addEventListener('submit', handleSearch);

    document.querySelectorAll('.nav-link').forEach(link => {
        link.addEventListener('click', handleTabClick);
    });
}

async function handleRegister(e) {
    e.preventDefault();
    const username = document.getElementById('register-username').value;
    const password = document.getElementById('register-password').value;
    const loading = document.getElementById('register-loading');

    showLoading(loading);

    setTimeout(async () => {
        try {
            const response = await fetch('http://localhost:8080/api/v1/auth/register', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ username, password })
            });

            hideLoading(loading);

            if (response.ok) {
                alert('Registration successful! Please login.');
                showLoginPage();
            } else {
                alert('Registration failed. Please try again.');
            }
        } catch (error) {
            hideLoading(loading);
            handleError(error);
        }
    }, 2000); // Simulating API call delay
}

async function handleLogin(e) {
    e.preventDefault();
    const username = document.getElementById('login-username').value;
    const password = document.getElementById('login-password').value;
    const loading = document.getElementById('login-loading');

    showLoading(loading);

    setTimeout(async () => {
        try {
            const response = await fetch('http://localhost:8080/api/v1/auth/login', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ username, password })
            });

            hideLoading(loading);

            if (response.ok) {
                const data = await response.json();
                localStorage.setItem('token', data.token);
                showMoviesPage();
            } else {
                alert('Login failed. Please try again.');
            }
        } catch (error) {
            hideLoading(loading);
            handleError(error);
        }
    }, 2000); // Simulating API call delay
}

async function handleSearch(e) {
    e.preventDefault();
    const title = document.getElementById('search-title').value;
    const loading = document.getElementById('search-loading');

    showLoading(loading);

    setTimeout(async () => {
        try {
            const token = localStorage.getItem('token');
            if (!token) {
                alert('No token found. Please login again.');
                showLoginPage();
                return;
            }

            const response = await fetch(`http://localhost:8080/api/v1/tmdb/movies?title=${title}`, {
                method: 'GET',
                headers: { 'Authorization': 'Bearer ' + token }
            });

            hideLoading(loading);

            if (response.ok) {
                const movies = await response.json();
                console.log('Movies response:', movies);

                const moviesList = document.getElementById('movies-list');
                moviesList.innerHTML = '';

                if (movies.length === 0) {
                    moviesList.innerHTML = '<p>Ainda não há filmes aqui</p>';
                } else {
                    movies.forEach(movie => {
                        const movieDiv = document.createElement('div');
                        movieDiv.classList.add('col-md-6', 'mb-4');
                        movieDiv.innerHTML = `
                            <div class="card" data-id="${movie.id}">
                                <img src="${movie.poster_path}" class="card-img-top" alt="${movie.title}">
                                <div class="card-body">
                                    <h5 class="card-title">${movie.title}</h5>
                                    <button class="btn btn-primary" onclick="setMovieStatus(${movie.id}, 'ASSISTIDO', event)">Assistido</button>
                                    <button class="btn btn-secondary" onclick="setMovieStatus(${movie.id}, 'FAVORITOS', event)">Favorito</button>
                                    <button class="btn btn-success" onclick="setMovieStatus(${movie.id}, 'PRETENDE_ASSISTIR', event)">Pretende Assistir</button>
                                </div>
                            </div>
                        `;
                        movieDiv.querySelector('.card').addEventListener('click', () => showMovieDetails(movie.id));
                        moviesList.appendChild(movieDiv);
                    });
                }
            } else {
                alert('Failed to load movies. Please try again.');
            }
        } catch (error) {
            hideLoading(loading);
            handleError(error);
        }
    }, 2000); // Simulating API call delay
}

async function handleTabClick(e) {
    const targetId = e.target.getAttribute('href').substring(1);
    if (targetId !== 'search-movies') {
        const status = targetId === 'watched-movies' ? 'ASSISTIDO' : targetId === 'favorite-movies' ? 'FAVORITOS' : 'PRETENDE_ASSISTIR';
        await loadMoviesByStatus(status, targetId);
    }
}

async function loadMoviesByStatus(status, targetId) {
    const loading = document.getElementById('loading');
    showLoading(loading);

    try {
        const token = localStorage.getItem('token');
        if (!token) {
            alert('No token found. Please login again.');
            showLoginPage();
            return;
        }

        const response = await fetch(`http://localhost:8080/api/v1/movies/user?status=${status}`, {
            method: 'GET',
            headers: { 'Authorization': 'Bearer ' + token }
        });

        hideLoading(loading);

        if (response.ok) {
            const movies = await response.json();
            console.log('Movies by status response:', movies);

            const targetList = document.getElementById(`${targetId.replace('-movies', '')}-list`);
            displayMovies(movies.map(movie => movie.movie), targetList);
        } else {
            alert('Failed to load movies. Please try again.');
        }
    } catch (error) {
        hideLoading(loading);
        handleError(error);
    }
}

function showMoviesPage() {
    toggleVisibility('register-page', false);
    toggleVisibility('login-page', false);
    toggleVisibility('movies-page', true);
}

function showRegisterPage() {
    toggleVisibility('register-page', true);
    toggleVisibility('login-page', false);
    toggleVisibility('movies-page', false);
}

function showLoginPage() {
    toggleVisibility('register-page', false);
    toggleVisibility('login-page', true);
    toggleVisibility('movies-page', false);
}

function toggleVisibility(elementId, visible) {
    document.getElementById(elementId).classList.toggle('d-none', !visible);
}

function showLoading(loadingElement) {
    loadingElement.classList.remove('d-none');
}

function hideLoading(loadingElement) {
    loadingElement.classList.add('d-none');
}

function handleError(error) {
    alert('An error occurred. Please try again.');
    console.error('Error:', error);
}

function displayMovies(movies, targetListId) {
    const targetList = typeof targetListId === 'string' ? document.getElementById(targetListId) : targetListId;
    targetList.innerHTML = '';

    if (movies.length === 0) {
        targetList.innerHTML = '<p>Ainda não há filmes aqui</p>';
    } else {
        movies.forEach(movie => {
            const movieDiv = document.createElement('div');
            movieDiv.classList.add('col-md-6', 'mb-4');
            movieDiv.innerHTML = `
                <div class="card" data-id="${movie.id}">
                    <img src="${movie.poster_path}" class="card-img-top" alt="${movie.title}">
                    <div class="card-body">
                        <h5 class="card-title">${movie.title}</h5>
                        <button class="btn btn-primary" onclick="setMovieStatus(${movie.id}, 'ASSISTIDO', event)">Assistido</button>
                        <button class="btn btn-secondary" onclick="setMovieStatus(${movie.id}, 'FAVORITOS', event)">Favorito</button>
                        <button class="btn btn-success" onclick="setMovieStatus(${movie.id}, 'PRETENDE_ASSISTIR', event)">Pretende Assistir</button>
                    </div>
                </div>
            `;
            movieDiv.querySelector('.card').addEventListener('click', () => showMovieDetails(movie.id));
            targetList.appendChild(movieDiv);
        });
    }
}

async function showMovieDetails(movieId) {
    const token = localStorage.getItem('token');
    if (!token) {
        alert('No token found. Please login again.');
        showLoginPage();
        return;
    }

    const response = await fetch(`http://localhost:8080/api/v1/tmdb/movies/${movieId}`, {
        method: 'GET',
        headers: { 'Authorization': 'Bearer ' + token }
    });

    if (response.ok) {
        const movie = await response.json();
        document.getElementById('modal-poster').src = movie.poster_path;
        document.getElementById('modal-title').innerText = movie.title;
        document.getElementById('modal-release-date').innerText = `Data de Lançamento: ${movie.release_date}`;
        document.getElementById('modal-overview').innerText = movie.overview;
        $('#movie-details-modal').modal('show');
    } else {
        alert('Failed to load movie details. Please try again.');
    }
}

async function setMovieStatus(movieId, status, event) {
    event.stopPropagation();
    const token = localStorage.getItem('token');
    if (!token) {
        alert('No token found. Please login again.');
        showLoginPage();
        return;
    }

    const response = await fetch(`http://localhost:8080/api/v1/tmdb/movies/${movieId}`, {
        method: 'GET',
        headers: { 'Authorization': 'Bearer ' + token }
    });

    if (response.ok) {
        const movie = await response.json();
        const body = {
            title: movie.title,
            overview: movie.overview,
            release_date: movie.release_date,
            poster_path: movie.poster_path,
            tmdb_movie_id: movie.id
        };

        const postResponse = await fetch(`http://localhost:8080/api/v1/movies?status=${status}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + token
            },
            body: JSON.stringify(body)
        });

        if (postResponse.ok) {
            alert(`Filme marcado como ${status.toLowerCase()}`);
            event.target.disabled = true;
        } else {
            alert('Failed to update movie status. Please try again.');
        }
    } else {
        alert('Failed to load movie details. Please try again.');
    }
}