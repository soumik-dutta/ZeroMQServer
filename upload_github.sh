#!/bin/bash
clear
echo "------------Setting up variables username and email---------"
echo "-------------Starting-----------------"
echo
git config --global user.name "soumik652"
git config --global user.email "soumik652@hotmail.com"
echo 
echo "-----------End -----"
echo 
echo "---------------Adding up files folders in local github repsitory--------------"
echo "----------------Starting-----------------"
echo
git init
git add *
git add src/
git add bin/
git commit -m "date"
git status
echo "-----------End -----"
echo 
echo "---------------------connecting github account---------------------"
echo "---------------Starting-------------------------"
ssh -vT git@github.com
echo "------------End-------------------"
echo
echo "---------------------push request to github repository----------------"
echo
echo "-------------Starting-----------------"
git remote add origin git@github.com:soumik652/ZeroMQServer.git
git push origin master
echo "------------End-------------------"
