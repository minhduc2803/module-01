#include <stdio.h>
#include <sys/socket.h>
#include <string.h> 
#include <arpa/inet.h>
#include <unistd.h>
#include <iostream>
#include <sstream>

#define PORT 2000

using namespace std;



char* receive(int client_socket)
{
	char * result = new char[4096];
	char *temp = result;
	while(1){

		int len = read(client_socket,temp,1024);

		if(temp[len]==0)
			break;
		else
			temp = temp+len;
	}
	return result;
}
void sendFull(int client_socket, const char * message){
	int len = strlen(message) + 1;
	int s = 0;
	while(1){
		s += send(client_socket,message, len-s,0);
		if(s >= len)
			break;
	}
}
void send_number(int client_socket, int number){
	string s = to_string(number);
	const char * message = s.c_str();
	sendFull(client_socket,message);
}
int receive_number(int client_socket){
    int number = -1;
	char * message = receive(client_socket);
    if(message != NULL){
        stringstream ss;
        ss << message;
        ss >> number;
    }
	return number;
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

    int state = 1;
    stringstream ss;
    while(1)
    {
        
        send_number(client_socket, state);
        
        int ball = receive_number(client_socket);

        if(ball > 0)
            ss << ball << " ";
        else if(ball < 0)
        {
            string temp = ss.str();
            const char* message = temp.c_str();
            sendFull(client_socket, message);

            char * result = receive(client_socket);
            printf("result:\n%s\n",result);
            break;
        }
    }

    return 0;
}