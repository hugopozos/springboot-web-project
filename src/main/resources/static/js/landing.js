const btnEnviar = document.getElementById('btn-enviar'); 
const modal = document.getElementById('modal');
const btnClose = document.getElementById('btn-modal');

btnEnviar.addEventListener('click',(e)=>{
    e.preventDefault();
    const email = document.getElementById('email').value;
    validarCorreo(email);
});

btnClose.addEventListener('click',(e)=>{
    e.preventDefault();
    modal.classList.remove('modal--show');
});

function validarCorreo(email){
    let expReg = /^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$/;
    let valido = expReg.test(email);
    const texto = document.querySelector('.text-confirmacion'); 
    if(valido){
        modal.classList.add('modal--show');
        texto.classList.remove('show--text')
    }else{
        texto.classList.add('show--text');
    }
}