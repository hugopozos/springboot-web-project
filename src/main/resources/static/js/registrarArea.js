$(document).ready(function() {
    //cargarHuertos();
    actualizarEmailDelUsuario();
});

function actualizarEmailDelUsuario() {
    document.getElementById('txt-email-usuario').outerHTML = localStorage.email;
}

function logout() {
    localStorage.removeItem('token'); // Elimina el token de la sesión
    window.location.href = 'login.html'; // Redirige al usuario a login.html
}