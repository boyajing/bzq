@echo off
@mode con cols=120 lines=30
color 0A 
cd ..
call mvn clean 
call mvn install
echo 
echo. & pause
