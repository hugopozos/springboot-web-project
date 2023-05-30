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

function registrarAreas() {
    // Obtener los valores del formulario
    var nombreArea = document.getElementById('txtNombreArea').value;
    var descripcion = document.getElementById('txtDescripcion').value;

    // Obtener el ID del Huerto
    var idHuerto = localStorage.getItem('huertoId');
    console.log(idHuerto)

            // Crear el objeto de datos a enviar
            var datos = {
                nombreArea: nombreArea,
                descripcion: descripcion,
                idHuerto: idHuerto
            };

            // Realizar la petición POST usando fetch
            fetch('/api/registrarAreas', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(datos)
            })
                .then(function(response) {
                    if (response.ok) {
                        return response.json();
                    }
                    throw new Error('Error en la respuesta de la API');
                })
                .then(function(data) {
                    // Manejar la respuesta de la API
                    console.log(data);
                    // Aquí puedes realizar acciones adicionales después del registro exitoso
                })
                .catch(function(error) {
                    // Manejar los errores de la petición
                    console.error('Error:', error);
                    // Aquí puedes mostrar un mensaje de error al usuario
                })

        .catch(function(error) {
            console.error('Error:', error);
            // Aquí puedes manejar el error y mostrar un mensaje al usuario si es necesario
        });
}