# Pharmacy API

Proyecto demostrativo con Spring Boot, Dev Containers, OpenSpec y OpenCode. Incluye Java 21, Maven, MySQL, Docker, kubectl y Claude Code.

## Inicio recomendado

1. Instale Docker Desktop y VS Code con **Dev Containers**.
2. Abra esta carpeta en VS Code.
3. Ejecute **Dev Containers: Reopen in Container**.
4. Espere a que finalice `postCreateCommand`.
5. Ejecute `mvn spring-boot:run`.
6. Abra `http://localhost:8080/actuator/health`.

MySQL se inicia automáticamente como servicio de Docker Compose. No necesita instalar Java, Maven, Node ni MySQL en Windows.

## Probar el CRUD

```bash
curl -i -X POST http://localhost:8080/api/productos \
  -H 'Content-Type: application/json' \
  -d '{"nombre":"Paracetamol","descripcion":"Tabletas 500 mg","precio":5.90,"stock":20}'

curl http://localhost:8080/api/productos
curl http://localhost:8080/api/productos/1
```

## Trabajo guiado por especificaciones

```bash
openspec list
openspec validate --all
opencode
claude
```

OpenCode y Claude requieren autenticación propia la primera vez que se ejecutan. Ninguna clave se incluye en el repositorio.

## Comandos útiles

```bash
mvn test
mvn clean package
docker build -t pharmacy-api:local .
kubectl apply -f k8s/mysql.yaml
kubectl apply -f k8s/api.yaml
```

> Las credenciales de MySQL son solo para desarrollo local. Use Secrets administrados en ambientes reales.
