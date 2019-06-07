const menu_factory = (title, to, icon, children = []) => ({ title, to, icon, children })

// TODO: config file with admin and client and user routes
const menu = [
    // parte admin
    menu_factory("Productos", "/product", "icon-basket", [
      menu_factory("Productos", "/product", "icon-basket"),
      menu_factory("Categorias", "/category", "icon-basket"),
      menu_factory("Proveedores", "/provider", "icon-basket"),
    ]),
    
    menu_factory("Inventario", "/batch", "icon-basket", [
      menu_factory("Abastecer", "/batch/add", "icon-basket"),
      menu_factory("Ver", "/batch", "icon-basket"),
    ]),
  
    menu_factory("Clientes", "/clients", "icon-basket", [
      menu_factory("Ver", "/clients", "icon-basket"),
    ]),
  
  
    menu_factory("Ventas", "", "icon-basket", [
      menu_factory("Ver", "/sale", "icon-basket"),
    ]),
  
    //parte cliente
    menu_factory("Catalogo", "", "icon-basket", [
      menu_factory("Ver", "/car", "icon-basket"),
    ]),
  ];

export default menu;