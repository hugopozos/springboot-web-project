$(document).ready(function() {
    // Agrega el controlador de eventos de teclado a todos los campos de entrada dentro del formulario
    $('form.user').on('keypress', function(event) {
        if (event.which === 13) {
            event.preventDefault(); // Previene el comportamiento por defecto del Enter (enviar el formulario)
            iniciarSesion(); // Llama a la función iniciarSesion()
        }
    });
});

async function iniciarSesion(){
    let datos = {};
    datos.email    = document.getElementById('txtEmail').value;
    datos.password = document.getElementById('txtPassword').value;
    
    const request = await fetch('api/login', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
    });

    const respuesta = await request.text();
    if (respuesta != 'FAIL'){
        // Guardar la respuesta del lado del browser
        localStorage.token = respuesta;
        localStorage.email = datos.email;
        window.location.href = 'usuarios.html'
    }else{
        alert("Las credenciales son incorrectas");
    }
}