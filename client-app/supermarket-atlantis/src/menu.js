import { isAdmin } from './common/auth'
const menu_factory = (title, to, icon, children = []) => ({ title, to, icon, children })

let menu = []

if (isAdmin()) {
  menu =
    [menu_factory("Productos", "/product", "icon-basket", [
      menu_factory("Productos", "/product", "icon-basket"),
      menu_factory("Categorias", "/category", "icon-basket"),
      menu_factory("Proveedores", "/provider", "icon-basket"),
    ]),

    menu_factory("Inventario", "/batch", "icon-basket", [
      menu_factory("Lotes", "/batch", "icon-basket"),
    ]),

    menu_factory("Clientes", "/clients", "icon-basket", [
      menu_factory("Ver", "/clients", "icon-basket"),
    ]),

    menu_factory("Ventas", "", "icon-basket", [
      menu_factory("Ver", "/sale", "icon-basket"),
    ])]
} else {
  menu = [
    menu_factory("Compras", "", "icon-basket", [
      menu_factory("Catalogo", "/shopping", "icon-basket"),
      menu_factory("Carrito", "/basket", "icon-basket"),
    ]),
    menu_factory("Ventas", "", "icon-basket", [
      menu_factory("Ver", "/sales", "icon-basket"),
    ]),
  ]
}

export default menu;