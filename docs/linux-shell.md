# Linux Shell

## 3.1 Processing texts

### Count the number of lines satisfying a specific pattern in a log file

Count the number of lines satisfying a specific pattern in a log file:

    grep -c

Calculate KLOC of code C/C++ files in a directory:

[link](https://unix.stackexchange.com/questions/291225/count-the-number-of-lines-found-by-grep)

    expr $(grep -r "icon" . | wc -l) / 1000  

## 3.2 System

Kill multiple processes following a patterns:

    kill $(ps -e | awk '/code/ {print $1}')   

Kill processes opening a specific port:


