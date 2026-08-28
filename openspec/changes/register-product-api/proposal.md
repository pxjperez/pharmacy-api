# Propuesta: API de registro de productos

## Por qué
La farmacia necesita digitalizar su catálogo y controlar precios y existencias.

## Qué cambia
- Se agrega un CRUD REST de productos.
- Se persisten productos en MySQL mediante JPA.
- Se validan nombre, precio y stock.

## Impacto
- Código: capas entity, repository, service y controller.
- Contrato: endpoints `/api/productos`.
