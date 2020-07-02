@echo off
title Generating run files for Eclipse...
echo Please note that the Windows batch files may be removed in a later commit.
echo Instructions on how to use Gradle with this project are kept in GRADLE.md.
echo.
cd ..
call gradlew.bat genEclipseRuns
pause
