const API_EMPLEADO = 'http://localhost:94/api/empleados';

// Función para cargar empleados
async function loadEmployees() {
  try {
    const response = await fetch(`${API_EMPLEADO}/empleados`, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${localStorage.getItem('token')}`,
      },
    });

    if (!response.ok) {
      throw new Error('Error al cargar empleados');
    }

    const employees = await response.json();
    const employeeList = document.getElementById('employeeList');
    employeeList.innerHTML = '';
    employees.forEach(employee => {
      const div = document.createElement('div');
      div.innerHTML = `
        <h3>${employee.nombre}</h3>
        <p>RUT: ${employee.rut}</p>
        <p>Rol: ${employee.rol}</p>
      `;
      employeeList.appendChild(div);
    });
  } catch (error) {
    console.error('Error al cargar empleados:', error);
    alert('No se pudieron cargar los empleados.');
  }
}

// Función para registrar empleado
async function addEmployee() {
  const nombre = prompt('Ingrese el nombre del empleado:');
  const rut = prompt('Ingrese el RUT del empleado:');
  const rol = prompt('Ingrese el rol del empleado (vendedor, bodeguero, contador):');

  try {
    const response = await fetch(`${API_EMPLEADO}/agregar`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${localStorage.getItem('token')}`,
      },
      body: JSON.stringify({ nombre, rut, rol, password: rut }),
    });

    if (!response.ok) {
      throw new Error('Error al registrar empleado');
    }

    alert('Empleado registrado exitosamente.');
    loadEmployees();
  } catch (error) {
    console.error('Error al registrar empleado:', error);
    alert('No se pudo registrar el empleado.');
  }
}

// Función para cargar productos
async function loadProducts() {
  const response = await fetch('/api/admin/productos', {
    method: 'GET',
    headers: {
      'Authorization': `Bearer ${localStorage.getItem('token')}`
    }
  });
  const products = await response.json();
  console.log(products);
}

// Función para cargar categorías
async function loadCategories() {
  const response = await fetch('/api/admin/categorias', {
    method: 'GET',
    headers: {
      'Authorization': `Bearer ${localStorage.getItem('token')}`
    }
  });
  const categories = await response.json();
  console.log(categories);
}

// Inicializar la página
document.addEventListener('DOMContentLoaded', () => {
  loadProducts();
  loadCategories();
  loadEmployees();

  document.getElementById('addProductButton').addEventListener('click', addProduct);
  document.getElementById('addEmployeeButton').addEventListener('click', addEmployee);
});