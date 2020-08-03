# Linux System Programming

### 2.1 File và File System

triết lý "everything is a file"

[link](https://www.youtube.com/watch?v=dDwXnB6XeiA)

### 2.2 Process & Thread

#### Multi-threading

Thread là chuỗi nhỏ nhất các lệnh lập trình đang được thực thi, được quản lý bởi bộ định thời của hệ điều hành.

Các khái niệm về thread và process có thể dễ bị lẫn lộn. Một chương trình máy tính đang chạy được gọi là một process. Process và Thread có những sự khác biệt cơ bản như sau:

| Process | Thread |
|---------|--------|
| Là một chương trình đang chạy | Là một phần của process |
| Process là một cá thể thực thi riêng biệt và không dùng chung data, information với các process khác | Threads share data giữa các threads trong cùng một process |
| Dùng cơ chế IPC (inter-process communication) để giao tiếp với các process khác, đòi hỏi nhiều system calls | Giao tiếp giữa các thread sử dụng shared memory |
| Tạo nhiều process đòi hỏi các system calls riêng biệt cho mỗi process | Một system call có thể tạo nhiều thread |

Multi-threading là khả năng của một CPU (hoặc một core trong CPU) xử lý nhiều thread cùng một lúc (concurrently), hỗ trợ bởi hệ điều hành.


### 2.3 Synchronizaion

### 2.4 Networking

## Phần thực hành

[hay](https://vimentor.com)