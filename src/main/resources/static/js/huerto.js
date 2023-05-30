// Call the dataTables jQuery plugin
$(document).ready(function() {
    obtenerNombreHuerto();
    $('#huertos').DataTable();
    actualizarEmailDelUsuario();
});

function actualizarEmailDelUsuario() {
    document.getElementById('txt-email-usuario').textContent = localStorage.email;
}

function logout() {
    localStorage.removeItem('token'); // Elimina el token de la sesión
    window.location.href = 'login.html'; // Redirige al usuario a login.html
}


// Función para obtener el nombre del huerto correspondiente al ID
async function obtenerNombreHuerto() {
    try {
        // Obtener el ID de la URL
        var url = new URL(window.location.href);
        var params = new URLSearchParams(url.search);
        var id = params.get("id");
        console.log(id);

        const request = await fetch('api/mihuerto/' + id, {
            method: 'GET',
            headers: getHeaders()
        });
        const huertos = await request.json();
        console.log(huertos);

        const tituloHuerto = document.querySelector('#nombre-huerto');

        const nombreHuerto = huertos.NombreHuerto;
        tituloHuerto.textContent = 'Huerto ' + nombreHuerto;
        console.log(nombreHuerto);

    } catch (error) {
        console.error('Error al obtener el nombre del huerto:', error);
        return '';
    }
}

function getHeaders() {
    return {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': localStorage.token
    };
}


// Función para mostrar el nombre del huerto en el título
async function mostrarNombreHuerto() {

    const tituloHuerto = document.querySelector('.h3.mb-2.text-gray-800');

    const nombreHuerto = await obtenerNombreHuerto(huertoId);
    tituloHuerto.textContent = 'Huerto ' + nombreHuerto;
}