#!/usr/bin/env bash
set -euo pipefail

echo "Configurando Git..."
git config --global user.name "Juan Carlos Pérez Gil"
git config --global user.email "PXJPEREZ@cibertec.edu.pe"

echo "Instalando herramientas de desarrollo asistido por IA..."
npm install --global \
  @fission-ai/openspec@latest \
  opencode-ai@latest \
  @anthropic-ai/claude-code@latest

echo "Verificando herramientas..."
java --version
mvn --version
node --version
npm --version
kubectl version --client
helm version

if [[ -f "pom.xml" ]]; then
  echo "Descargando dependencias Maven..."
  mvn --batch-mode -DskipTests dependency:go-offline
fi

echo "Entorno listo. Ejecuta: mvn spring-boot:run"