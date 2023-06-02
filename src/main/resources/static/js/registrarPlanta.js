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

function registrarPlanta() {
    // Obtener los valores del formulario
    // Obtener el valor seleccionado del dropdown
    var dropdown = document.getElementById('dropdown-item');
    var planta = dropdown.textContent.trim();
    console.log(planta);

    var idArea = localStorage.getItem('areaId');
    console.log(idArea);

            // Crear el objeto de datos a enviar
            var datos = {
                planta: planta,
                idArea: idArea
            };

            // Realizar la petición POST usando fetch
            fetch('/api/registrarPlantas', {
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
        })
}