#!/usr/bin/env bash
set -euo pipefail

echo "Verificando entorno..."

# Java
if command -v java >/dev/null 2>&1; then
    java_version="$(java --version 2>&1)"
    echo "Java: ${java_version%%$'\n'*}"
else
    echo "Java: no instalado"
fi

# Maven
if command -v mvn >/dev/null 2>&1; then
    maven_version="$(mvn --version 2>&1)"
    echo "Maven: ${maven_version%%$'\n'*}"
else
    echo "Maven: no instalado"
fi

# Node.js
if command -v node >/dev/null 2>&1; then
    echo "Node.js: $(node --version)"
else
    echo "Node.js: no instalado"
fi

# NPM
if command -v npm >/dev/null 2>&1; then
    echo "NPM: $(npm --version)"
else
    echo "NPM: no instalado"
fi

# kubectl
if command -v kubectl >/dev/null 2>&1; then
    kubectl_output="$(kubectl version --client --output=yaml 2>&1)"
    kubectl_version="$(
        printf '%s\n' "$kubectl_output" |
        awk '/gitVersion:/ {print $2}'
    )"
    echo "kubectl: $kubectl_version"
else
    echo "kubectl: no instalado"
fi

# Helm
if command -v helm >/dev/null 2>&1; then
    echo "Helm: $(helm version --short)"
else
    echo "Helm: no instalado"
fi

# OpenSpec
if command -v openspec >/dev/null 2>&1; then
    echo "OpenSpec: $(openspec --version)"
else
    echo "OpenSpec: no instalado"
fi

# OpenCode
if command -v opencode >/dev/null 2>&1; then
    echo "OpenCode: $(opencode --version)"
else
    echo "OpenCode: no instalado"
fi

echo "Entorno verificado correctamente."