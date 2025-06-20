const API_CATEGORIA = 'http://localhost:8585/api/categorias';
const API_PRODUCTO = 'http://localhost:8585/api/productos';
const API_CARRITO_COMPRA = 'http://localhost:8282/api/carrito-compra';

// Cargar categorías
async function loadCategories() {
  try {
    const response = await fetch(`${API_CATEGORIA}/categorias`, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${localStorage.getItem('token')}`, // Enviar token almacenado
      },
    });

    if (!response.ok) {
      throw new Error('Error al cargar categorías');
    }

    const categories = await response.json();
    const categoryList = document.getElementById('categoryList');
    categories.forEach(category => {
      const div = document.createElement('div');
      div.textContent = category.nombre;
      div.addEventListener('click', () => loadProducts(category.id));
      categoryList.appendChild(div);
    });
  } catch (error) {
    console.error('Error al cargar categorías:', error);
    alert('No se pudieron cargar las categorías. Intenta nuevamente más tarde.');
  }
}

// Cargar productos
async function loadProducts(categoryId) {
  try {
    const response = await fetch(`${API_PRODUCTO}/productos?categoriaId=${categoryId}`);
    if (!response.ok) {
      throw new Error('Error al cargar productos');
    }
    const products = await response.json();
    const productList = document.getElementById('productList');
    productList.innerHTML = ''; // Limpiar productos anteriores
    products.forEach(product => {
      const div = document.createElement('div');
      div.innerHTML = `
        <h3>${product.nombre}</h3>
        <p>${product.descripcion}</p>
        <p>Precio: $${product.precio}</p>
        <button class="addToCartButton" data-product-id="${product.id}">Agregar al carrito</button>
      `;
      productList.appendChild(div);
    });

    // Agregar eventos a los botones de carrito
    document.querySelectorAll('.addToCartButton').forEach(button => {
      button.addEventListener('click', () => {
        const productId = button.getAttribute('data-product-id');
        addToCart(productId);
      });
    });
  } catch (error) {
    console.error('Error al cargar productos:', error);
    alert('No se pudieron cargar los productos. Intenta nuevamente más tarde.');
  }
}

// Agregar producto al carrito
function addToCart(productId) {
  const isLoggedIn = localStorage.getItem('isLoggedIn') === 'true'; // Verificar estado de sesión
  if (!isLoggedIn) {
    alert('Debes iniciar sesión o registrarte para agregar productos al carrito.');
    return;
  }

  // Lógica para agregar al carrito (si está logueado)
  fetch(`${API_CARRITO_COMPRA}/agregar`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${localStorage.getItem('token')}`, // Enviar token si es necesario
    },
    body: JSON.stringify({ productId }),
  })
    .then(response => {
      if (response.ok) {
        alert('Producto agregado al carrito.');
      } else {
        alert('Error al agregar producto al carrito.');
      }
    })
    .catch(error => {
      console.error('Error al agregar producto al carrito:', error);
      alert('No se pudo agregar el producto al carrito. Intenta nuevamente más tarde.');
    });
}

// Mostrar mensaje de carga
function showLoadingMessage(containerId, message) {
  const container = document.getElementById(containerId);
  if (container) {
    container.innerHTML = `<p>${message}</p>`;
  } else {
    console.error(`Elemento con id "${containerId}" no encontrado.`);
  }
}

// Inicializar la página
document.addEventListener('DOMContentLoaded', () => {
  showLoadingMessage('categoryList', 'Cargando categorías...');
  showLoadingMessage('productList', 'Cargando productos...');
  loadCategories();
});