#include <stdio.h>
#include <sys/socket.h>
#include <stdlib.h> 
#include <netinet/in.h>
#include <string.h> 
#include <unistd.h>
#include <sys/types.h>
#include <netdb.h> 
#include <arpa/inet.h> 
#include <queue>
#include <sstream>
#include <string>

#define PORT 2000

using namespace std;

struct event{
	int type;
	int client_socket;
	event(int type, int client_socket)
	{
		this->type = type;
		this->client_socket = client_socket;
	}
};

int main(int argc,  char const *argv[])
{
	const int opt = 1;
	struct sockaddr_in address;
	int address_len = sizeof(address);

	int server_socket = socket(AF_INET, SOCK_STREAM | SOCK_NONBLOCK, 0);

	if(server_socket == 0)
	{
		printf("Server creating socket file descriptor run into error !!!");
		exit(EXIT_FAILURE);
	}
	int state = setsockopt(server_socket,SOL_SOCKET, SO_REUSEADDR | SO_REUSEPORT, &opt, sizeof(opt));
	if(state)
	{
		printf("Server attaching socket to port run into error !!!");
		exit(EXIT_FAILURE);
	}
	address.sin_family = AF_INET;
	address.sin_addr.s_addr = INADDR_ANY;
	address.sin_port = htons(PORT);

	if(bind(server_socket, (struct sockaddr*)&address,sizeof(address)) < 0)
	{
		printf("Server binding run into error !!!");
		exit(EXIT_FAILURE);
	}
	if(listen(server_socket, 10) < 0)
	{
		printf("Server listening run into error !!!");
		exit(EXIT_FAILURE);
	}


	char hostbuffer[256]; 
    char *IPbuffer; 
    struct hostent *host_entry; 
    int hostname; 
  
    hostname = gethostname(hostbuffer, sizeof(hostbuffer)); 
    
    host_entry = gethostbyname(hostbuffer); 
   
    IPbuffer = inet_ntoa(*((struct in_addr*)host_entry->h_addr_list[0])); 
	printf("Server is running on %s:%d\n",IPbuffer,PORT);

	queue<event> event_queue;

	while(1)
	{
		int client_socket = accept(server_socket, (struct sockaddr *)&address, (socklen_t *)&address_len);
		if(client_socket < 0)
		{
			
			
		}
		else
		{
			event e = event(1,client_socket);
			event_queue.push(e);
		}
		
		if(!event_queue.empty())
		{
			event e = event_queue.front();
			event_queue.pop();
			switch(e.type)
			{
				case 1:
				
				default:
				{
					std::stringstream ss;
					int client_socket = e.client_socket;
					char massage[1024];
					int massage_len = read(client_socket, massage, 1024);
					massage[massage_len] = 0;
					printf("%s\n",massage);
					
					send(client_socket,massage, strlen(massage),0);
				}

			}
		}
	}

	
  
	
	
	return 0;
}
