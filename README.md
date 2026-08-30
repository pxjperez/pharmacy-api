# Pharmacy API

Proyecto demostrativo con Spring Boot, Dev Containers, OpenSpec y OpenCode. Incluye Java 21, Maven, MySQL y kubectl.

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
##⚙️ Paso 1: Revisar y Validar los Artefactos
#Abres opencode
opencode
#Lista los cambios disponibles
openspec list
#Valida la coherencia de todo el contexto
openspec validate --all
#Crear un nuevo cambio
#openspec new change crud-usuario
openspec new change <nombre-de-tu-cambio>

##💻 Paso 2: Aplicar el Cambio (Apply)
#Aplicas los cambios que tienes disponibles de implementar
#openspec apply crud-usuario
openspec apply <nombre-de-tu-cambio>

##📦 Paso 3: Archivar el Cambio (Archive)
#Archivas los cambios realizados para que se quede en el historico
#openspec archive crud-usuario
openspec archive <nombre-de-tu-cambio> --yes
```
OpenCode requieren autenticación propia la primera vez que se ejecutan. Ninguna clave se incluye en el repositorio.

## Comandos útiles
```bash
mvn test
mvn clean package
docker build -t pharmacy-api:local 
kubectl apply -f k8s/mysql.yaml
kubectl apply -f k8s/api.yaml
```

> Las credenciales de MySQL son solo para desarrollo local. Use Secrets administrados en ambientes reales.
