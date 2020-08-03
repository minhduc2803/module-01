# Linux Shell

## 3.1 Processing texts

### Count the number of lines satisfying a specific pattern in a log file

Count the number of lines satisfying a specific pattern in a log file:

    grep -c 'pattern' logfile

Ví dụ: đếm sô dòng trong file gpu-manager.log có xuất hiện chuỗi 'no'

    grep -c 'no' /var/gpu-manager.log

Đếm sô dòng trong file gpu-manager.log có kết thúc bằng chuỗi 'no'

    grep -c 'no$' /var/gpu-manager.log

### Calculate KLOC of code C/C++ files in a directory:

    find directory -name '*.c' -o -name '*.cpp' | xargs wc -l 

Ví dụ: tìm tất cả các file .c và .cpp trong directory ~/project

    find ~/project -name '*.c' -o -name '*.cpp' | xargs wc -l 

## 3.2 System

### Kill multiple processes following a patterns:

    kill $(ps -e | awk '/pattern/ {print $1}')   

Ví du: kill tất cả các processes thuộc về visual code

    kill $(ps -e | awk '/code/ {print $1}')

Cụ thể hơn có thể chỉ định kill tất cả các processes có trường CMD (trường thứ 4) kết thúc bằng chuỗi 'code':

    kill $(ps -e | awk '$4 ~ /code$/ {print $1}')

### Kill processes opening a specific port:

    kill -9 $(lsof -t -i:port)

Ví dụ: kill bất kỳ process nào listen trên port 1111:

    kill -9 ($lsof -t -i:1111)

Ta có thể list bất kỳ process nào listen trên port 1111 nhờ lệnh lsof:

    lsof -i:1111

### List openned ports, handles

Dùng netstat:

    netstat -lptu

### Find files via regular expressions, and remove them

Tìm file via regex:

    find directory -regex 'pattern'

Xóa file, dùng find, xargs và rm:

    find directory -regex 'pattern' | xargs rm

Ví dụ: Trong directory ~/project, xóa tất cả các file .txt chứa chuỗi 'hello' trong tên file:

    find ~/project -regex '.*hello.*\.*\.txt' | xargs rm




