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
#include <ctime>
#include <random>
#include <iostream>

#define PORT 2000

using namespace std;

void print_IP()
{
	char hostbuffer[256]; 
    char *IPbuffer; 
    struct hostent *host_entry; 
    int hostname; 
  
    hostname = gethostname(hostbuffer, sizeof(hostbuffer)); 
    
    host_entry = gethostbyname(hostbuffer); 
   
    IPbuffer = inet_ntoa(*((struct in_addr*)host_entry->h_addr_list[0])); 
	cout << "Server is running on " << IPbuffer << ":" << PORT << endl;
}
bool start_server(int server_socket, struct sockaddr_in * address, int address_len, const int *opt)
{
	if(server_socket == 0)
	{
		cout << "Server creating socket file descriptor run into error !!!" << endl;
		return false;
	}
	int state = setsockopt(server_socket,SOL_SOCKET, SO_REUSEADDR | SO_REUSEPORT, opt, sizeof(*opt));
	if(state)
	{
		cout << "Server attaching socket to port run into error !!!" << endl;
		return false;
	}
	address->sin_family = AF_INET;
	address->sin_addr.s_addr = INADDR_ANY;
	address->sin_port = htons(PORT);

	if(bind(server_socket, (struct sockaddr*)address, address_len)< 0)
	{
		cout << "Server binding run into error !!!" << endl;
		return false;
	}
	if(listen(server_socket, 10) < 0)
	{
		cout << "Server listening run into error !!!" << endl;
		return false;
	}

	return true;
}
int * create_random_balls(int n)
{
	int * balls = new int[n];
	for(int i=0;i<n;i++)
		balls[i] = i+1;
	return balls;
}
int receive(int client_socket)
{
	int state;
	char *message = (char*)&state;

	int message_len = read(client_socket, message, sizeof(state));
	return ntohl(state);
}
void handle_server(int server_socket, struct sockaddr_in *address, int *address_len)
{
	queue<int> event_queue;
	srand(time(0));
	int n = rand()%100;
	cout << "n = " << n << endl;
	int * balls = create_random_balls(n);
	while(1)
	{
		int client_socket = accept(server_socket, (struct sockaddr *)address, (socklen_t *)address_len);
		if(client_socket >= 0)
		{
			cout << "Connection from " << client_socket << endl;
			event_queue.push(client_socket);
		}
		
		if(!event_queue.empty())
		{
			int queue_len = event_queue.size();
			for(int i=0;i<queue_len;i++)
			{
				client_socket = event_queue.front();
				event_queue.pop();
	
				int state = receive(client_socket);
				
				switch(state)
				{
					case 1:
					{
						if(n<=0)
						{
							int ball = htonl(-1);
							char *data = (char*)&ball;
							send(client_socket,data, sizeof(ball),0);
						}
						else
						{
							int ball = htonl(balls[rand()%(n--)]);
							char *data = (char*)&ball;
							send(client_socket,data, sizeof(ball),0);
							
						}
						event_queue.push(client_socket);
						break;
					}
					case 2:
					{
						char const *data = "Done";
						send(client_socket,data, strlen(data)+1,0);

					}
					

				}
			}
		}
	}
}

int main(int argc,  char const *argv[])
{
	const int opt = 1;
	struct sockaddr_in address;
	int address_len = sizeof(address);

	int server_socket = socket(AF_INET, SOCK_STREAM, 0);
	bool flag = start_server(server_socket, &address, address_len, &opt);

	if(server_socket < 0)
		return -1;

	print_IP();
	
	handle_server(server_socket, &address, &address_len);

	return 0;
}
