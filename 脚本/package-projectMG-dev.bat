@echo off
@mode con cols=120 lines=30
color 0A 
cd ../progectMG
call mvn clean
call mvn package 
echo 
echo. & pause
