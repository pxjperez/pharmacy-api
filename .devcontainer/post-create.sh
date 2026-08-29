#!/usr/bin/env bash
set -euo pipefail

echo "Configurando Git..."
git config --global user.name "Tu Nombre"
git config --global user.email "PXJPEREZ@cibertec.edu.pe"

echo "Instalando herramientas de desarrollo asistido por IA..."
npm install --global @fission-ai/openspec@latest opencode-ai@latest @anthropic-ai/claude-code@latest

echo "Descargando dependencias Maven..."
mvn -q -DskipTests dependency:go-offline

echo "Entorno listo. Ejecuta: mvn spring-boot:run"
