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

#### Lợi ích của multi-threading

- Dễ lập trình: nhiều bài toán dễ lập trình hơn nếu chia thành nhiều công việc riêng việt rồi giao cho các thread.

- Xử lý song song: Nếu có nhiều hơn một core, hay nhiều hơn một CPU, các thread có thể được xử lý song song trên nhiều core và cpu đó.

- Tăng khả năng phản hồi của chương trình: nếu một thread đang bận xử lý một chuỗi công việc dài thì ta có thể giao tiếp với user cho một thread khác, 2 thread hoạt động cùng nhau => ta có một chương trình vừa xử lý và giao tiếp với user cùng lúc (concurrenly).

- Blocking I/O: Nếu chỉ  có 1 thread, 1 blocking I/O có thể chặn đứng cả chương trình. Tương tự như khả năng phản hồi được nêu ở trên, nếu có nhiều thread, một số thread có thể bị block nhưng vẫn còn nhiều thread tiếp tục công việc.

- Việc CPU chuyển đổi giữa 2 thread trong cùng một process tốn rất ít chi phí (so sánh với việc chuyển đổi giữa 2 process).

- Minimized resource usage của hệ thống: các thread dùng chung memory, việc giao tiếp giữa các thread trong cùng một process cũng dễ dàng hơn do cơ chế share memory này (so sánh với multi-processing).

#### Các vấn đề với multi-threading

![alan-cox-quote](../images/alan-cox-quote.jpg)

- Khi số lượng thread tăng lên và phần lớn trong chúng bị block, lúc đó hệ thông đang phí phạm một lượng lớn tài nguyên để tạo và duy trì các thread.

- Debug trong multi-threading là rất khó. Vì chuyển đổi giữa các thread là do hệ điều hành quyết định nên việc design và hiểu một chương trình multi-threading không hề dễ dàng.

- Các thread share memory với nhau, đọc ghi trên cùng môt vùng nhớ, cùng data, vì không thể đoán trước CPU sẽ switch giữa các thread như thế nào dẫn tới những vấn đề nhức nhối nhất với multi-threading: các thread không đồng bộ, race condition, deadlock. Dẫn tới output không mong muốn, thực thi sai thậm chí là crash chương trình. 

#### Race condition

#### Deadlock

### 2.3 Synchronizaion

#### Semaphore

#### Reader Writer Problem

### 2.4 Networking

## Phần thực hành

[hay](https://vimentor.com)