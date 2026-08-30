#!/usr/bin/env bash
set -euo pipefail

echo "Configurando Git..."
git config --global user.name "Tu Nombre"
git config --global user.email "Tu correo git"

echo "Configuando herramientas de desarrollo asistido por IA..."
npm install --global @fission-ai/openspec@latest opencode-ai@latest
openspec init --tools opencode --profile core

echo "Configuando de Git y Herramientas de desarrollo asistido por IA completada."