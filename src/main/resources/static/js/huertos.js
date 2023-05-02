// Call the dataTables jQuery plugin
$(document).ready(function() {
    cargarHuertos();
    $('#tb_huertos').DataTable();
    actualizarEmailDelUsuario();
});

function actualizarEmailDelUsuario() {
    document.getElementById('txt-email-usuario').outerHTML = localStorage.email;
}


async function cargarHuertos() {
    const request = await fetch('api/huertos', {
        method: 'GET',
        headers: getHeaders()
    });
    const usuarios = await request.json();


    let listadoHtml = '';
    for (let huerto of huertos) {
        let botonEliminar = '<a href="#" onclick="eliminarHuerto(' + huerto.id + ')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';

        let huertoHtml = '<tr><td>'+huerto.id+'</td><td>' + huerto.nombre +  '</td><td>'
            + usuario.email+ '</td><td>' + botonEliminar + '</td></tr>';
        listadoHtml += huertoHtml;
    }

    document.querySelector('#usuarios tbody').outerHTML = listadoHtml;

}

function getHeaders() {
    return {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': localStorage.token
    };
}


async function eliminarHuerto(id) {

    if (!confirm('¿Desea eliminar este huerto?')) {
        return;
    }

    const request = await fetch('api/huertos/' + id, {
        method: 'DELETE',
        headers: getHeaders()
    });

    location.reload()
}