@echo off
chcp 65001 >nul
title Управление библиотекой
cd /d "%~dp0"
LibraryManagement.exe
pause
