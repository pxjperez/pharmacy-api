# Tareas: Contrato OpenAPI persistente

## 1. Especificación

- [x] 1.1 Confirmar la regla en `AGENTS.md` (ya aplicada) y verificar que se lee el requisito
- [x] 1.2 Mantener la delta spec `specs/system-architecture/spec.md` del requisito y verificar `openspec validate --changes contrato-openapi-persistente`

## 2. Sincronización

- [x] 2.1 Sincronizar la delta a `openspec/specs/system-architecture.md` (archivar el cambio) y verificar que el requisito queda en la spec maestra
- [x] 2.2 Verificar `openspec validate --all` tras la sincronización
- [x] 2.3 Ajustar `openspec/specs/openapi-contract.yaml` con los endpoints de `usuarios` pendientes del cambio archivado y verificar que el contrato sigue siendo un YAML válido