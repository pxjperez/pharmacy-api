#!/usr/bin/env bash
set -euo pipefail

echo "Verificando entorno..."

java_version="$(java --version 2>&1)"
maven_version="$(mvn --version 2>&1)"
node_version="$(node --version)"
npm_version="$(npm --version)"

echo "Java: $(printf '%s\n' "$java_version" | sed -n '1p')"
echo "Maven: $(printf '%s\n' "$maven_version" | sed -n '1p')"
echo "Node.js: $node_version"
echo "NPM: $npm_version"

if command -v kubectl >/dev/null 2>&1; then
  echo "kubectl: $(kubectl version --client --output=yaml | sed -n 's/^gitVersion: //p')"
fi

if command -v helm >/dev/null 2>&1; then
  helm_version="$(helm version --short)"
  echo "Helm: $helm_version"
fi

echo "Entorno verificado correctamente."