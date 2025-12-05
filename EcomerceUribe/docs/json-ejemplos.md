# Ejemplos de JSON por entidad

Los controladores REST aceptan y devuelven objetos basados en las entidades del dominio (`Usuario`, `Cliente`, `Empleado`, `Producto` y `Pedido`). Los POST/PUT reciben el modelo completo y los GET devuelven DTOs con los campos expuestos por cada recurso.

## Usuario (`/api/usuarios`)

**POST (crear)**
```json
{
  "nombres": "Laura Pérez",
  "correo": "laura.perez@example.com",
  "contraseña": "claveSegura",
  "estado": "Activo",
  "fechaNacimiento": "1995-07-12",
  "tipoDocumento": "Cedula",
  "documento": "1234567890"
}
```

**PUT (modificar nombre/correo)**
```json
{
  "nombres": "Laura P. Actualizada",
  "correo": "laura.p.actualizada@example.com"
}
```

**GET (respuesta genérica)**
```json
{
  "nombres": "Laura Pérez",
  "correo": "laura.perez@example.com",
  "estado": "Activo",
  "fechaNacimiento": "1995-07-12",
  "documento": "1234567890"
}
```

## Cliente (`/api/clientes`)

Relación 1:1 con `Usuario` mediante `usuario`.

**POST (crear)**
```json
{
  "direccion": "Calle 123 #45-67",
  "calificacion": 4.8,
  "referenciaPago": "REF-998877",
  "departamento": "ANTIOQUIA",
  "ciudad": "Medellín",
  "usuario": { "id": 1 }
}
```

**PUT (modificar datos)**
```json
{
  "direccion": "Carrera 10 #20-30",
  "calificacion": 4.9,
  "referenciaPago": "REF-998877",
  "departamento": "ANTIOQUIA",
  "ciudad": "Medellín",
  "usuario": { "id": 1 }
}
```

**GET (respuesta especial)**
```json
{
  "direccion": "Calle 123 #45-67",
  "referenciaPago": "REF-998877",
  "calificacion": "4.8",
  "ciudad": "Medellín"
}
```

## Empleado (`/api/empleados`)

Relación 1:1 con `Usuario` mediante `usuario`.

**POST (crear)**
```json
{
  "cargo": "Analista",
  "salario": 3500000,
  "sede": "Guayabal",
  "usuario": { "id": 2 }
}
```

**PUT (modificar)**
```json
{
  "cargo": "Profesional",
  "salario": 4200000,
  "sede": "Milla_oro",
  "usuario": { "id": 2 }
}
```

**GET (respuesta especial)**
```json
{
  "cargo": "Analista",
  "salario": "3500000",
  "sede": "Guayabal",
  "usuario": {
    "nombres": "Carlos Gómez",
    "correo": "carlos.gomez@example.com"
  }
}
```

## Producto (`/api/productos`)

Relación muchos-a-uno con `Pedido` mediante `pedido`.

**POST (crear)**
```json
{
  "nombre": "Camiseta básica",
  "fotografia": "https://example.com/fotos/camiseta.png",
  "descripcion": "Camiseta de algodón cuello redondo",
  "categoria": "CAMISETA",
  "precioUnitario": 89000,
  "marca": "UrbanWear",
  "aplicaDescuento": true,
  "pedido": { "id": 5 }
}
```

**PUT (modificar precio/descuento)**
```json
{
  "precioUnitario": 82000,
  "aplicaDescuento": false
}
```

**GET (respuesta especial)**
```json
{
  "nombre": "Camiseta básica",
  "descripcion": "Camiseta de algodón cuello redondo",
  "categoria": "CAMISETA",
  "precioUnitario": "89000",
  "marca": "UrbanWear",
  "aplicaDescuento": true
}
```

## Pedido (`/api/pedidos`)

Relación uno-a-muchos con `Producto` mediante `productos`.

**POST (crear)**
```json
{
  "montoTotal": 250000,
  "fechaCreacion": "2024-05-01",
  "fechaEntrega": "2024-05-05",
  "costoEnvio": 15000,
  "productos": [
    { "id": 10 },
    { "id": 11 }
  ]
}
```

**PUT (modificar)**
```json
{
  "montoTotal": 240000,
  "fechaEntrega": "2024-05-06",
  "costoEnvio": 12000
}
```

**GET (respuesta especial)**
```json
{
  "montoTotal": "250000",
  "fechaCreacion": "2024-05-01",
  "fechaEntrega": "2024-05-05",
  "costoEnvio": "15000",
  "productos": [
    {
      "nombre": "Camiseta básica",
      "precioUnitario": "89000",
      "marca": "UrbanWear"
    }
  ]
}
```

## Cómo relacionar entidades

- **Cliente ↔ Usuario**: incluye en el JSON de `Cliente` un objeto `usuario` con el `id` del usuario ya creado (`{"usuario": {"id": X}}`).
- **Empleado ↔ Usuario**: mismo patrón usando `usuario` con el `id` de usuario.
- **Producto ↔ Pedido**: al crear o actualizar un producto, coloca `"pedido": {"id": <idPedido>}` para asignarlo a un pedido existente.
- **Pedido ↔ Productos**: al crear/actualizar un pedido puedes incluir un arreglo `productos` con objetos que tengan `id` de productos existentes para asociarlos. También puedes enviar productos nuevos completos en ese arreglo si deseas crearlos en cascada.

### Valores permitidos de los enums
- `estado` de `Usuario`: `Activo`, `Inactivo`
- `tipoDocumento` de `Usuario`: `Cedula`, `Extranjeria`
- `departamento` de `Cliente`: valores definidos en `Departamentos` (ej. `ANTIOQUIA`, `CUNDINAMARCA`, etc.)
- `cargo` de `Empleado`: `Analista`, `Auxiliar`, `Profesional`, `Vendedor`
- `sede` de `Empleado`: `Guayabal`, `Puntos_de_venta`, `Milla_oro`
- `categoria` de `Producto`: `CAMISETA`, `PANTALON`, `VESTIDO`, `INTERIOR`, `ZAPATO`, `CHAQUETA`, `POLO`, `BERMUDA`, `ACCESORIO`
