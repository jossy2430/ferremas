const REGISTER_API_URL = 'http://localhost:8282/api/auth/registro';

// Formatear el RUT automáticamente
document.getElementById('rut').addEventListener('input', (e) => {
  let value = e.target.value.replace(/\D/g, ''); // Eliminar caracteres no numéricos
  if (value.length > 1) {
    value = value.replace(/^(\d{1,2})(\d{3})(\d{3})(\d{1})$/, '$1.$2.$3-$4'); // Formato RUT
  }
  e.target.value = value;
});

const registerForm = document.getElementById('registerForm');
if (registerForm) {
  registerForm.addEventListener('submit', async (e) => {
    e.preventDefault();

    const telefono = document.getElementById('telefono').value;
    // Validar que tenga 9 dígitos y empiece con 9
    if (!/^[9]\d{8}$/.test(telefono)) {
      alert('El teléfono debe tener 9 dígitos y comenzar con 9');
      return;
    }

    const rut = document.getElementById('rut').value;
    // Validar que el RUT tenga el formato correcto
    if (!/^\d{1,2}\.\d{3}\.\d{3}-\d{1}$/.test(rut)) {
      alert('El RUT debe tener el formato correcto (Ej: 12.345.678-9)');
      return;
    }

    const customerData = {
      nombres: document.getElementById('nombres').value,
      apellidos: document.getElementById('apellidos').value,
      correo: document.getElementById('correo').value,
      password: document.getElementById('password').value,
      direccion: document.getElementById('direccion').value,
      telefono: document.getElementById('telefono').value,
      recibirNotificacion: document.getElementById('recibirNotificacion').checked,
      rut: rut, // Agregar el RUT al objeto
    };

    try {
      const response = await fetch(REGISTER_API_URL, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(customerData),
      });

      if (!response.ok) {
        if (response.status === 409) {
          throw new Error('El correo ya está registrado. Por favor, inicie sesión.');
        } else {
          throw new Error('Error al registrar cliente. Por favor, contacte al administrador.');
        }
      }

      alert('Registro exitoso. Ahora puedes iniciar sesión.');
      window.location.href = 'login.html'; // Redirigir al inicio de sesión
    } catch (error) {
      alert(error.message); // Mostrar mensaje de error
    }
  });
}