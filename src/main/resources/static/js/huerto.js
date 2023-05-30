// Call the dataTables jQuery plugin
$(document).ready(function() {
    obtenerNombreHuerto();
    cargarAreas();
    $('#areas').DataTable();
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

        let listadoHtml = '';
        let descripcionHtml = '';
        for (let huerto of huertos) {
            let huertoHtml = huerto.nombreHuerto ;
            let descHtml = huerto.descripcion;
            listadoHtml += huertoHtml;
            descripcionHtml += descHtml;
            console.log(listadoHtml);
        }
        document.querySelector('#nombre-huerto').innerHTML = listadoHtml;
        document.querySelector('#descripcion-huerto').innerHTML = descripcionHtml;

        //const tituloHuerto = document.querySelector('#nombre-huerto');

        const nombreHuerto = huertos.NombreHuerto;
        tituloHuerto.textContent = 'Huerto ' + nombreHuerto;
        console.log(nombreHuerto);

    } catch (error) {
        console.error('Error al obtener el nombre del huerto:', error);
        return '';
    }
}

async function cargarAreas() {
    try {
        // Obtener el ID del Huerto
        var idHuerto = localStorage.getItem('huertoId');
        console.log(idHuerto)

        const request = await fetch('api/areas/' + idHuerto, {
            method: 'GET',
            headers: getHeaders()
        });
        const areas = await request.json();

        let listadoAreas = '';
        for (let area of areas) {
            let botonEliminar = '<a href="#" onclick="eliminarArea(' + area.id + ')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';

            let areaHtml = '<tr data-area-id="' + area.id + '"><td>' + area.id + '</td><td>' + area.nombre + '</td><td>' + area.descripcion + '</td><td>' + botonEliminar + '</td></tr>';
            listadoAreas += areaHtml;
            console.log(area.nombre)
            console.log(listadoAreas);
        }

        document.querySelector('#areas tbody').innerHTML = listadoAreas;

        // Agregar evento de clic a cada fila de huertos
        const filasAreas = document.querySelectorAll('#areas tbody tr');
        filasAreas.forEach((fila) => {
            const areaId = fila.getAttribute('data-area-id');
            fila.addEventListener('click', function() {
                // Modificar la URL con el ID del huerto
                const url = 'area.html?id=' + areaId;
                localStorage.setItem('areaId',areaId);
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


// Función para mostrar el nombre del huerto en el título
async function mostrarNombreHuerto() {

    const tituloHuerto = document.querySelector('.h3.mb-2.text-gray-800');

    const nombreHuerto = await obtenerNombreHuerto(huertoId);
    tituloHuerto.textContent = 'Huerto ' + nombreHuerto;
}