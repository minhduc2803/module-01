# DOCKER

## Containers

### What are containers ?

Container là một phần mềm đóng gói application's code, cô lập application với toàn bộ runtime environment sao cho application có thể chạy như nhau trong nhiều môi trường khác nhau. 

Để làm được điều đó, Container tận dụng một dạng của operating system virtualization (ví dụ như namespace và cgroups primitives của Linux kernel) để cô lâp các processes và kiểm soát những phần mà các processes đó truy cập tới (như CPU, memory, disk).

Container rất nhanh và gọn nhẹ, không giống như virtual machine, container không ảo hóa cả một hệ điều hành mà tận dung luôn các tính năng và nguồn lực của host OS.

### Containers vs. Virtual machine

Virtual machine (VM) là một phần mềm mô phỏng một hệ thống máy tính (như mô phỏng một hệ điều hành). VM với các guest OS (như Linux hay Windows) chạy trên nền host OS, sau đó các ứng dụng sẽ chạy trên các guest OS này. VM cung cấp khả năng cô lập rất cao giữa application và environment. Containers cũng cung cấp khả năng này nhưng gọn nhẹ hơn nhiều. 

![](../images/container-vs-vm.jpg)

Container có nhiều điểm khác biệt so với VM nhưng khác nhau cơ bản nhất là:
- Container đo bằng megabyte. Container không đóng gói thứ gì lớn hơn một application và tất cả những gì cần thiết để để run application đó. Containers share operating system (chỉ dùng một OS) nên rất gọn nhẹ và dễ dàng di chuyển giữa nhiều môi trường.
- VM đo bằng gigabyte. VM chứa hệ điều hành của chính nó, cho phép giả lập nhiều functions và hệ thống phức tạp (như các hệ điều hành, desktop, database, networks).

### Benefits of Containers ?

- Lightweight: Containers share the OS kernel, không cần ảo hóa cả một hệ điều hành cho mỗi application làm cho Container files nhỏ gọn và hoạt động nhanh hơn.

- Isolation: Container ảo hóa CPU, memory, storage và resources ở OS-level, cung cấp một khả năng cô lập application so với các application khác.

- Consistent Environment: Containers cung cấp một môi trường như nhau trên nhiều platform khác nhau, dễ dàng hơn cho developers để config, code và dubugging.

- Portable and platform independent: Application có thể được viết một lần và chạy trên nhiều lần mà không cần re-configured trên nhiều môi trường như laptops, destop, cloud,...

## Docker

### What is Docker ?

### How Docker works 

## Làm quen với Docker

### Installation

## Các lệnh cơ bản

