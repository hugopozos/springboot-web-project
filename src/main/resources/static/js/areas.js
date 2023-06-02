// Call the dataTables jQuery plugin
$(document).ready(function() {
    obtenerNombreArea();
    cargarPlantas()
    $('#areas').DataTable();
    actualizarEmailDelUsuario();
});

function actualizarEmailDelUsuario() {
    document.getElementById('txt-email-usuario').outerHTML = localStorage.email;
}

function logout() {
    localStorage.removeItem('token'); // Elimina el token de la sesión
    window.location.href = 'login.html'; // Redirige al usuario a login.html
}

// Función para obtener el nombre del huerto correspondiente al ID
async function obtenerNombreArea() {
    try {
        // Obtener el ID de la URL
        var url = new URL(window.location.href);
        var params = new URLSearchParams(url.search);
        var id = params.get("id");
        console.log(id);

        const request = await fetch('api/miarea/' + id, {
            method: 'GET',
            headers: getHeaders()
        });
        const area = await request.json();
        console.log(area);

        let areaHtml = area.nombre;
        let descHtml = area.descripcion;
        console.log(areaHtml);

        document.querySelector('#nombre-area').innerHTML = areaHtml;
        document.querySelector('#descripcion-area').innerHTML = descHtml;

        /*let listadoHtml = '';
        let descripcionHtml = '';

        for (let area of areas) {
            let areaHtml = area.nombre ;
            let descHtml = area.descripcion;
            console.log(areaHtml);
            listadoHtml += areaHtml;
            descripcionHtml += descHtml;
            console.log(listadoHtml);
        }
        document.querySelector('#nombre-area').innerHTML = listadoHtml;
        document.querySelector('#descripcion-area').innerHTML = descripcionHtml;*/

        const tituloArea = document.querySelector('#nombre-area');
        const nombreArea = area.nombre;
        tituloArea.textContent = 'Area ' + nombreArea;
        console.log(nombreArea);

    } catch (error) {
        console.error('Error al obtener el nombre del area:', error);
        return '';
    }
}

async function cargarPlantas() {
    try {
        // Obtener el ID del Huerto
        var idArea = localStorage.getItem('areaId');
        console.log(idArea)

        const request = await fetch('api/plantas/' + idArea, {
            method: 'GET',
            headers: getHeaders()
        });
        const plantas = await request.json();

        let listadoPlantas = '';
        for (let planta of plantas) {
            let botonEliminar = '<a href="#" onclick="eliminarArea(' + planta.id + ')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';

            let plantaHtml = '<tr data-planta-id="' + planta.id + '"><td>' + planta.id + '</td><td>' + planta.nombre + '</td><td>' + botonEliminar + '</td></tr>';
            listadoPlantas += plantaHtml;
            console.log(planta.nombre)
            console.log(listadoPlantas);
        }

        document.querySelector('#plantas tbody').innerHTML = listadoPlantas;

        // Agregar evento de clic a cada fila de huertos
        const filasPlantas = document.querySelectorAll('#plantas tbody tr');
        filasPlantas.forEach((fila) => {
            const plantaId = fila.getAttribute('data-planta-id');
            fila.addEventListener('click', function() {
                // Modificar la URL con el ID del huerto
                const url = 'planta.html?id=' + plantaId;
                localStorage.setItem('plantaId',plantaId);
                history.pushState(null, null, url);

                // Redireccionar a la página del huerto
                window.location.href = url;
            });
        });
    } catch (error) {
        console.error('Error al cargar las plantas:', error);
    }
}


function getHeaders() {
    return {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': localStorage.token
    };
}
