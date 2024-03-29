// Call the dataTables jQuery plugin
$(document).ready(function() {
    cargarHuertos();
    $('#huertos').DataTable();
    actualizarEmailDelUsuario();
});

function actualizarEmailDelUsuario() {
    document.getElementById('txt-email-usuario').outerHTML = localStorage.email;
}

function logout() {
    localStorage.removeItem('token'); // Elimina el token de la sesión
    window.location.href = 'login.html'; // Redirige al usuario a login.html
}


async function cargarHuertos() {
    try {
        const idUsuario = await obtenerTokenJWT(); // Obtener el ID del usuario del token JWT

        const request = await fetch('api/huertos/' + idUsuario, {
            method: 'GET',
            headers: getHeaders()
        });
        const huertos = await request.json();

        let listadoHtml = '';
        for (let huerto of huertos) {
            let botonEliminar = '<a href="#" onclick="eliminarHuerto(' + huerto.codigoHuerto + ')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';

            let huertoHtml = '<tr data-huerto-id="' + huerto.codigoHuerto + '"><td>' + huerto.codigoHuerto + '</td><td>' + huerto.nombreHuerto + '</td><td>' + huerto.descripcion + '</td><td>' + botonEliminar + '</td></tr>';
            listadoHtml += huertoHtml;
        }

        document.querySelector('#huertos tbody').innerHTML = listadoHtml;

        // Agregar evento de clic a cada fila de huertos
        const filasHuertos = document.querySelectorAll('#huertos tbody tr');
        filasHuertos.forEach((fila) => {
            const huertoId = fila.getAttribute('data-huerto-id');
            fila.addEventListener('click', function() {
                // Modificar la URL con el ID del huerto
                const url = 'huerto.html?id=' + huertoId;
                localStorage.setItem('huertoId', huertoId);
                history.pushState(null, null, url);

                // Redireccionar a la página del huerto
                window.location.href = url;
            });
        });
    } catch (error) {
        console.error('Error al cargar los huertos:', error);
    }
}


function getHeaders() {
    return {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': localStorage.token
    };
}


async function eliminarHuerto(codigoHuerto) {
    if (!confirm('¿Desea eliminar este huerto?')) {
        return;
    }

    const request = await fetch('api/eliminarHuerto/' + codigoHuerto, {
        method: 'DELETE',
        headers: getHeaders()
    });

    location.reload()
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

