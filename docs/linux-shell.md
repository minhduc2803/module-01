# Linux Shell

## 3.1 Processing texts

### Count the number of lines satisfying a specific pattern in a log file


## 3.2 System

Kill multiple processes following a patterns:

    kill $(ps -e | awk '/code/ {print $1}')   

Kill processes opening a specific port:

    