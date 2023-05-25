$(document).ready(function() {
    // Agrega el controlador de eventos de teclado a todos los campos de entrada dentro del formulario
    $('form.user').on('keypress', function(event) {
        if (event.which === 13) {
            event.preventDefault(); // Previene el comportamiento por defecto del Enter (enviar el formulario)
            iniciarSesion(); // Llama a la función iniciarSesion()
        }
    });
});

async function iniciarSesion() {
    let datos = {
        email: document.getElementById('txtEmail').value,
        password: document.getElementById('txtPassword').value
    };

    if (datos.email.trim() === '' || datos.password.trim() === '') {
        alert('Por favor, ingresa el correo electrónico y la contraseña.');
        return;
    }

    const request = await fetch('api/login', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
    });

    const respuesta = await request.text();
    if (respuesta != 'FAIL') {
        localStorage.token = respuesta;
        localStorage.email = datos.email;
        window.location.href = 'huertos.html';
    } else {
        alert("Las credenciales son incorrectas");
    }
}