FROM mcr.microsoft.com/devcontainers/java:1-21-bookworm

USER root
RUN apt-get update \
    && apt-get install -y --no-install-recommends curl git jq unzip default-mysql-client \
    && rm -rf /var/lib/apt/lists/*
USER vscode
