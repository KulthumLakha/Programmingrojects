/*
 * Kulthum Lakha kyl0029
 * 11/20/2021
 * CSCE 3600.002
 * This program sets up a UDP socket on the internet domain and then waits in an infinite loop 
 * listening for incoming UDP packets, specifically PING messages from a client.
 * If PING is recieved, the server sends a message (PONG) back to the client.
 */

/*
 * Complie:	gcc -o minor4svr minor4svr.c -Wall
 * Run: 	./minor4svr <portno> 
 */

// Include necessary libraries.
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <errno.h>
#include <string.h>
#include <sys/types.h>
#include <time.h>


// Main function
int main(int argc, char *argv[])
{

	// Declare variables.
	int serverfd = 0;										// Store file descriptor returned by socket.
	struct sockaddr_in server_addr;							// IP address and port of machine.
	struct sockaddr client_addr;							// Address of client.
	socklen_t client_addr_len = sizeof(client_addr);		// size of client's address.
	char sendBuff[4096];									// Initiailize buffer to store message.
	srand(time(0));											// Seed random generator

	// Check for correct arguments.
	if(argc != 2)
	{
		fprintf(stderr, "usage: ./minor4svr <port>\n");
		exit(EXIT_FAILURE);
	}


	// Create socket and check for error.
	if ((serverfd = socket( AF_INET, SOCK_DGRAM, 0 )) == -1)
	{
		printf("socket error\n");
		exit(EXIT_FAILURE);
	}
		

	memset(&server_addr, '0', sizeof(server_addr));				// Initiailize/set as 0.
	memset(sendBuff, '0', sizeof(sendBuff));					// Initiailize/set as 0.

	server_addr.sin_family = AF_INET;							// Set address family.
	server_addr.sin_addr.s_addr = htonl(INADDR_ANY);			// Set IP address, any local address.
	int portno = atoi(argv[1]);									// Set argument as portno.
	server_addr.sin_port = htons(portno);						// Set port as portno.


	// Set socket to re-use the INET address.
	int on = 1;									
    setsockopt(serverfd, SOL_SOCKET, SO_REUSEADDR, &on, sizeof(on));


	// Bind a name to the socket and check for error.
	if (bind(serverfd, (const struct sockaddr*) &server_addr, sizeof(server_addr)) == -1)
	{
		printf("bind error\n");
		exit(EXIT_FAILURE);
	}


	// Display status of server ready to accept messages.
	printf("[server]: ready to accept data...\n");		
	
	// Infinite loop for receiving and sending messages(Packets).
	while(1)
	{
		// Recieve message on socket.
		recvfrom(serverfd, &sendBuff, sizeof(sendBuff), 0, &client_addr, &client_addr_len);

		/* 
		 * Simulate 30% packet loss through generation of a seeded, randomized integer
		 * that will determine whether a particular incoming PING message is lost or not
		 */
		if (rand() % 10 >= 3)
		{
			// Print the message received from client.
			printf("[client]: %s\n", sendBuff);

			// Send message on socket.
			sendto(serverfd, "PONG", sizeof("PONG"), 0, &client_addr, client_addr_len);
		}
		else
			// Print message to show packet was dropped (Message wasn't recieved).
			printf("[server]: dropped packet\n");		
	}
	

	// Return 0 on success.
	return 0;
}