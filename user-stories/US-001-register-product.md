# US-001 — Registrar producto

Como encargado de farmacia, quiero registrar un producto con nombre, descripción, precio y stock, para mantener actualizado el catálogo.

## Criterios de aceptación
- Al enviar datos válidos, la API responde `201 Created` y devuelve el producto con ID.
- El nombre es obligatorio y tiene como máximo 120 caracteres.
- El precio no puede ser negativo.
- El stock debe ser un entero mayor o igual que cero.
- Los datos inválidos responden `400 Bad Request`.
