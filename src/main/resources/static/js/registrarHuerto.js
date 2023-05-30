$(document).ready(function() {
    actualizarEmailDelUsuario();
    // Agrega el controlador de eventos de teclado a todos los campos de entrada dentro del formulario
    $('form.huerto').on('keypress', function(event) {
        if (event.which === 13) {
            event.preventDefault(); // previene el comportamiento por defecto del Enter (enviar el formulario)
            registrarHuertos(); // llama a la función registrarUsuarios()
        }
    });
});

function actualizarEmailDelUsuario() {
    document.getElementById('txt-email-usuario').outerHTML = localStorage.email;
}

async function registrarHuertos() {
    let datos = {};
    const token = localStorage.getItem('token');

    datos.nombreHuerto = document.getElementById('txtNombreHuerto').value;
    datos.descripcion  = document.getElementById('txtDescripcion').value;

        fetch('/api/registrarHuertos', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
            },
            body: JSON.stringify(datos)
        })
         .then(response => {
            if (!response.ok) {
                throw new Error('Error al guardar huerto');
            }
            return response.text();
        })
            .then(data => {
                console.log(data);
                alert('huerto guardada correctamente');
                window.location.href = 'registrarHuertos.html'
            })
            .catch(error => {
                console.error(error);
                alert('Error al guardar huerto');
            });

}
