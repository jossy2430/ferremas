// URLs para clientes y empleados
const CLIENT_API_URL = 'http://localhost:8282/api/auth/login';
const EMPLOYEE_API_URL = 'http://localhost:9494/api/auth/login';

// Obtener el formulario de inicio de sesión
const loginForm = document.getElementById('loginForm');
if (loginForm) {
  loginForm.addEventListener('submit', async (e) => {
    e.preventDefault();

    const correo = document.getElementById('correo').value; // Usuario o correo
    const password = document.getElementById('password').value;

    try {
      // Determinar si es cliente o empleado según el correo ingresado
      const isEmployee = correo.includes('@empresa.com'); // Ejemplo: correos corporativos para empleados

      const API_URL = isEmployee ? EMPLOYEE_API_URL : CLIENT_API_URL;

      const response = await fetch(API_URL, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ usuario: correo, password }), // Cambiar "correo" por "usuario" si el backend lo requiere
      });

      if (!response.ok) {
        if (response.status === 404) {
          throw new Error('El usuario no está registrado. Por favor, contacte al administrador.');
        } else {
          throw new Error('Credenciales inválidas');
        }
      }

      const data = await response.json();

      // Almacenar el token en localStorage
      localStorage.setItem('token', data.token);

      if (isEmployee) {
        // Almacenar el rol para empleados
        localStorage.setItem('rol', data.rol);

        // Redirigir según el rol del empleado
        switch (data.rol) {
          case 'ADMINISTRADOR':
            window.location.href = 'admin.html'; // Vista del administrador
            break;
          case 'VENDEDOR':
            window.location.href = 'vendedor.html'; // Vista del vendedor
            break;
          case 'BODEGUERO':
            window.location.href = 'bodeguero.html'; // Vista del bodeguero
            break;
          case 'CONTADOR':
            window.location.href = 'contador.html'; // Vista del contador
            break;
          default:
            throw new Error('Rol no reconocido.');
        }
      } else {
        // Redirigir al dashboard de clientes
        alert(`Inicio de sesión exitoso. Bienvenido, ${correo}`);
        window.location.href = 'paginaPrincipal.html';
      }
    } catch (error) {
      document.getElementById('errorMessage').textContent = error.message;
    }
  });
}