#!/usr/bin/env bash
set -euo pipefail

echo "Instalando herramientas de desarrollo asistido por IA..."
npm install --global @fission-ai/openspec@latest opencode-ai@latest @anthropic-ai/claude-code@latest

echo "Descargando dependencias Maven..."
mvn -q -DskipTests dependency:go-offline

echo "Entorno listo. Ejecuta: mvn spring-boot:run"
