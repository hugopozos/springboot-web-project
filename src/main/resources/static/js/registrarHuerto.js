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

function registrarHuertos() {
    // Obtener los valores del formulario
    var nombreHuerto = document.getElementById('txtNombreHuerto').value;
    var descripcion = document.getElementById('txtDescripcion').value;

    // Obtener el ID del usuario del token JWT
    obtenerTokenJWT()
        .then(function(key) {
            var idUsuario = key;

            // Crear el objeto de datos a enviar
            var datos = {
                nombreHuerto: nombreHuerto,
                descripcion: descripcion,
                idUsuario: idUsuario
            };

            // Realizar la petición POST usando fetch
            fetch('/api/registrarHuertos', {
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
                    // Redireccionar a huertos.html
                    window.location.href = 'huertos.html';
                })
                .catch(function(error) {
                    // Manejar los errores de la petición
                    console.error('Error:', error);
                });
        })
        .catch(function(error) {
            console.error('Error:', error);
            // Aquí puedes manejar el error y mostrar un mensaje al usuario si es necesario
        });
}
function obtenerTokenJWT() {
    return fetch('api/obtenerToken', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(function(response) {
            if (response.ok) {
                return response.text(); // Parsear la respuesta como texto
            }
            throw new Error('Error al obtener el token JWT del servidor');
        })
        .then(function(data) {
            // Obtener el token key del texto de respuesta
            var key = data.replace('Token key: ', '');

            // Mostrar el token key en la consola
            console.log('Token key:', key);

            // Devolver el token key
            return key;
        })
        .catch(function(error) {
            console.error('Error:', error);
            // Aquí puedes manejar el error y mostrar un mensaje al usuario si es necesario
        });
}
