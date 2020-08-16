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
#include <unistd.h>
#include <fcntl.h>

#define PORT 2000

using namespace std;

struct event{
    int type;
    int client_socket;
	int sum, number_of_balls, quantity_rank, quality_rank;
	event(int client_socket, int type){
		this->type = type;
		this->client_socket = client_socket;
		this->quantity_rank = 0;
		this->quality_rank = 0;
	}
	~event(){};
};

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

int get_ball(int * balls, int & n)
{
	int k = rand()%(n--);
	int ball = balls[k];
	balls[k] = balls[n];
	return ball;
}
char* receive(int client_socket)
{
	char * result = new char[4096];
	char *temp = result;
	while(1){

		int len = read(client_socket,temp,1024);
		if(len == 0 && temp == result)
			return NULL;

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
void finish(queue<event*> event_queue)
{
	int size = event_queue.size();
	event ** event_array = new event*[size];

	for(int i = 0;i<size;i++){
		event_array[i] = event_queue.front();
		event_queue.pop();
	}

	for(int i = 0;i<size-1;i++){
		int qt = i;
		for(int j = i+1;j<size;j++){
			if(event_array[qt]->sum < event_array[j]->sum)
				qt = j;
			
		}
		event * temp = event_array[i];
		event_array[i] = event_array[qt];
		event_array[qt] = temp;
		event_array[i]->quantity_rank = i+1;
	}
	event_array[size-1]->quantity_rank = size;

	for(int i = 0;i<size-1;i++){
		int ql = i;
		for(int j = i+1;j<size;j++){
			if(event_array[ql]->number_of_balls < event_array[j]->number_of_balls)
				ql = j;
			
		}
		event * temp = event_array[i];
		event_array[i] = event_array[ql];
		event_array[ql] = temp;
		event_array[i]->quality_rank = i+1;
	}
	event_array[size-1]->quality_rank = size;

	for(int i=0;i<size;i++){
		stringstream ss;
		ss << "Quality rank: " << event_array[i]->quality_rank << endl;
		ss << "Sum of balls: " << event_array[i]->sum << endl;
		ss << "Quantity rank: " << event_array[i]->quantity_rank << endl;
		ss << "Number of balls: " << event_array[i]->number_of_balls << endl;
	
		string s = ss.str();
		const char * message = s.c_str();

		printf("\nclient: %d\n%s\n",event_array[i]->client_socket,message);
		sendFull(event_array[i]->client_socket, message);
	}

}
void handle_server(int server_socket, struct sockaddr_in *address, int *address_len)
{
	queue<event*> event_queue;

	srand(time(0));
	int n = rand()%901+100;
	int origin_n = n, collect_balls = 0;
	cout << "n = " << n << endl;
	int * balls = create_random_balls(n);
	while(1)
	{
		int client_socket = accept(server_socket, (struct sockaddr *)address, (socklen_t *)address_len);
		if(client_socket >= 0)
		{
			cout << "Connection from " << client_socket << endl;
			event_queue.push(new event(client_socket,1));
		}
		
		if(!event_queue.empty())
		{
			int queue_len = event_queue.size();
			for(int i=0;i<queue_len;i++)
			{
				event * e = event_queue.front();
				event_queue.pop();
	
				int type = e->type;
				int client_socket = e->client_socket;
				
				switch(type)
				{
					case 1:
					{
						char * state = receive(client_socket);
						if(state == NULL)
							break;

						if(n<=0)
						{
							send_number(client_socket, -1);
							e->type = 2;
						}
						else
						{
							int ball = get_ball(balls, n);
							cout << "ball: " << ball << " to " << client_socket << endl;
					
							send_number(client_socket, ball);
							
						}
						event_queue.push(e);
						break;
					}
					case 2:
					{
            			char * result = receive(client_socket);

						stringstream ss;
						ss << result;
						int sum = 0, number_of_balls = 0, temp;

						while(ss >> temp){
							sum += temp;
							number_of_balls++;
							collect_balls++;
						}
						
						if(result != NULL){
							e->type = 3;
							e->sum = sum;
							e->number_of_balls = number_of_balls;
						}

						event_queue.push(e);
						
						break;
					}
					case 3:
					{
						event_queue.push(e);
						if(collect_balls == origin_n){
							finish(event_queue);
							return;
						}
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
	fcntl(server_socket, F_SETFL, O_NONBLOCK);
	bool flag = start_server(server_socket, &address, address_len, &opt);

	if(server_socket < 0)
		return -1;

	print_IP();
	
	handle_server(server_socket, &address, &address_len);

	return 0;
}
