# Propuesta: Sistema de login

## Why
La farmacia necesita proteger el catálogo de productos y garantizar que solo usuarios autenticados puedan gestionarlo.

## Qué cambia
- Se agrega autenticación de usuarios mediante usuario y contraseña.
- Se emiten tokens de sesión para las solicitudes autenticadas.
- Se protegen los endpoints de productos para usuarios autenticados.
- Se agrega el endpoint `POST /api/auth/login`.

## Impacto
- Código: capas entity, repository, service y controller para autenticación.
- Contrato: endpoint `/api/auth/login`, protección de `/api/productos`.
- Dependencias: seguridad y emisión de tokens.
