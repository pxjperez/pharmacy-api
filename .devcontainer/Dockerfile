FROM mcr.microsoft.com/devcontainers/java:1-21-bookworm

USER root
RUN rm -f /etc/apt/sources.list.d/yarn.list \
    && apt-get update \
    && apt-get install -y --no-install-recommends curl git jq unzip maven default-mysql-client \
    && rm -rf /var/lib/apt/lists/*
USER vscode
