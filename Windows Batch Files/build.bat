@echo off
title Building The Midnight...
echo Please note that the Windows batch files may be removed in a later commit.
echo Instructions on how to use Gradle with this project are kept in GRADLE.md.
echo.
cd ..
call gradlew.bat build
pause
