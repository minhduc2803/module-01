#include <stdio.h>
#include <sys/socket.h>
#include <string.h> 
#include <arpa/inet.h>
#include <unistd.h>

#define PORT 2000

int main(int argc, char const *argv[])
{

    struct sockaddr_in server_address;

    int client_socket = socket(AF_INET, SOCK_STREAM, 0);
    if(client_socket < 0)
    {
        printf("Client initializing run into error !!!");
        return -1;
    }

    server_address.sin_family = AF_INET;
    server_address.sin_port = htons(PORT);

    if(inet_pton(AF_INET, "127.0.0.1", &server_address.sin_addr) <= 0)
    {
        printf("\nInvalid address or address not supported !!!");
        return -1;
    }

    if(connect(client_socket, (struct sockaddr *)&server_address, sizeof(server_address)) < 0)
    {
        printf("\nConnection failed \n");
        return -1;
    }

    
    while(1)
    {
        char * hello = "Hello from client";
        send(client_socket,hello, strlen(hello),0);
        char massage[1024];
        int massage_len = read(client_socket, massage, 1024);
        massage[massage_len] = 0;
        printf("%s\n",massage);
    }

    return 0;
}