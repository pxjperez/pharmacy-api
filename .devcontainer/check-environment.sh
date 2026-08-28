#!/usr/bin/env bash
set -euo pipefail
printf 'Java: '; java -version 2>&1 | head -n 1
printf 'Maven: '; mvn -version | head -n 1
printf 'Node: '; node --version
printf 'kubectl: '; kubectl version --client=true --output=yaml | awk '/gitVersion:/{print $2; exit}'
printf 'OpenSpec: '; openspec --version
printf 'OpenCode: '; opencode --version
printf 'Claude: '; claude --version
