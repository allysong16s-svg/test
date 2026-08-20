@echo off
setlocal
set BASE=%~dp0
set DIST=%BASE%.gradle-dist
set VERSION=8.2
if not exist "%DIST%\gradle-%VERSION%\bin\gradle.bat" (
  mkdir "%DIST%" 2>nul
  powershell -NoProfile -ExecutionPolicy Bypass -Command "$u='https://services.gradle.org/distributions/gradle-%VERSION%-bin.zip'; Invoke-WebRequest $u -OutFile '%DIST%\gradle.zip'; Expand-Archive -Force '%DIST%\gradle.zip' '%DIST%'"
)
call "%DIST%\gradle-%VERSION%\bin\gradle.bat" %*
