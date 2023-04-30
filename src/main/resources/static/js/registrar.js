// Call the dataTables jQuery plugin
$(document).ready(function() {
  // Agrega el controlador de eventos de teclado a todos los campos de entrada dentro del formulario
  $('form.user').on('keypress', function(event) {
    if (event.which === 13) {
      event.preventDefault(); // previene el comportamiento por defecto del Enter (enviar el formulario)
      registrarUsuarios(); // llama a la función registrarUsuarios()
    }
  });
});

async function registrarUsuarios(){
  let datos = {};
  datos.nombre   = document.getElementById('txtNombre').value;
  datos.apellido = document.getElementById('txtApellido').value;
  datos.email    = document.getElementById('txtEmail').value;
  datos.password = document.getElementById('txtPassword').value;

  let repetirPassword = document.getElementById('txtRepetirPassword').value;
  if(repetirPassword != datos.password){
    alert('La contraseña no coincide');
    document.getElementById('txtPassword').value = '';
    document.getElementById('txtRepetirPassword').value = '';
    return;
  }

  let validacionPassword = false;
  let regexPassword = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%_\-])[0-9a-zA-Z!@#$%_\-]{8,}$/;
  if (regexPassword.test(datos.password)) {
    validacionPassword = true;
  } else {
    alert('La contraseña debe tener al menos 8 caracteres, incluyendo al menos una letra minúscula, una letra mayúscula, un número y uno de los siguientes caracteres especiales: !@#$%');
    document.querySelector('.alert').classList.add('alert');
    document.getElementById('txtPassword').value = '';
    document.getElementById('txtRepetirPassword').value = '';
    return;
  }

  if (validacionPassword == true) {
    fetch('/api/usuarios', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(datos)
    })
        .then(response => {
          if (!response.ok) {
            throw new Error('Error al guardar persona');
          }
          return response.text();
        })
        .then(data => {
          console.log(data);
          alert('Persona guardada correctamente');
          window.location.href = 'login.html'
        })
        .catch(error => {
          console.error(error);
          alert('Error al guardar persona');
        });
  }
}
