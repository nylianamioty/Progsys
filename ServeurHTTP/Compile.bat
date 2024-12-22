@echo off

set src=src\
set lib=lib\
set bin=bin
set libname=SERVEUTHTTP
set resources=resources
set temp_src=temp_src

mkdir %temp_src%

cd "%src%"

for /r %%F in (*.java) do (
    copy "%%F" "..\%temp_src%"
)

javac -cp ../%lib%*;%resources% -d ../%bin% ../%temp_src%/*.java

cd ../

rmdir /S /Q %temp_src%

pause