#include <stdio.h>
#include <sys/socket.h>
#include <string.h> 
#include <arpa/inet.h>
#include <unistd.h>
#include <iostream>

#define PORT 2000

using namespace std;

int receive(int client_socket)
{
	int state;
	char *message = (char*)&state;

	int message_len = read(client_socket, message, sizeof(state));
	return ntohl(state);
}

int main(int argc, char const *argv[])
{

    struct sockaddr_in server_address;

    int client_socket = socket(AF_INET, SOCK_STREAM, 0);
    if(client_socket < 0)
    {
        cout << "Client initializing run into error !!!" << endl;
        return -1;
    }

    server_address.sin_family = AF_INET;
    server_address.sin_port = htons(PORT);

    if(inet_pton(AF_INET, "127.0.0.1", &server_address.sin_addr) <= 0)
    {
        cout << "Invalid address or address not supported !!!" << endl;
        return -1;
    }

    if(connect(client_socket, (struct sockaddr *)&server_address, sizeof(server_address)) < 0)
    {
        cout << "Connection failed !!!" << endl;
        return -1;
    }

    int state = htonl(1);
    char *data = (char*)&state;
    while(state)
    {
        
        cout << "state: " << state << endl;

        send(client_socket,data, sizeof(state),0);
        
        cout << "done send" << endl;
        int ball;
        char *message = (char*)&ball;

        int message_len = read(client_socket, message, sizeof(ball));
	    ball = ntohl(ball);
        cout << "ball: " <<  ball << " ";
        if(ball > 0)
            cout << ball << " ";
        else
        {
            state = htonl(2);
            send(client_socket,data, sizeof(state),0);
            char message[1024];
            read(client_socket,message,1024);
            cout << endl << message << endl;
            break;
        }
    }

    return 0;
}