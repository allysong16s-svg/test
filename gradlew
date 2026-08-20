#!/bin/sh
set -e
BASE_DIR=$(CDPATH= cd -- "$(dirname "$0")" && pwd)
DIST="$BASE_DIR/.gradle-dist"
GRADLE_VERSION=8.2
ZIP="$DIST/gradle-$GRADLE_VERSION-bin.zip"
if [ ! -x "$DIST/gradle-$GRADLE_VERSION/bin/gradle" ]; then
  mkdir -p "$DIST"
  if command -v curl >/dev/null 2>&1; then
    curl -fsSL -o "$ZIP" "https://services.gradle.org/distributions/gradle-$GRADLE_VERSION-bin.zip"
  else
    wget -q -O "$ZIP" "https://services.gradle.org/distributions/gradle-$GRADLE_VERSION-bin.zip"
  fi
  unzip -q -o "$ZIP" -d "$DIST"
fi
exec "$DIST/gradle-$GRADLE_VERSION/bin/gradle" "$@"
