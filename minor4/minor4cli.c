/*
 * Kulthum Lakha kyl0029
 * 11/20/2021
 * CSCE 3600.002
 * The client will send 10 automated PING messages to the server on a UDP socket
 */

/*
 * Compile:	gcc -o minor4cli minor4cli.c -Wall -lpthread
 * Run: 	./minor4cli <hostname> <portno> 
 */

// Include necessary libraries.
#include <sys/socket.h>
#include <sys/types.h>
#include <netinet/in.h>
#include <netdb.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#include <errno.h>
#include <arpa/inet.h>
#include <sys/time.h>
#include <float.h>
#include <netdb.h>
#include <pthread.h>
#include <time.h>

#define NUM_PACKETS 10						// Define constant NUM_PACKETS

int clientfd;								// Store file descriptor returned by socket.
struct addrinfo *resolution;				// Address information
pthread_mutex_t mutex;						// Declare mutex lock.
pthread_t thread_sleep, thread_ping;		// Create threads.
double times[NUM_PACKETS];					// Declare times array of size 10.



// Thread to wait for 1second.
void *thread_sleep_main(void *arg)
{
	int *i = (int *) arg;								// i is a pointer to the parameter. 
	sleep(1);											// Sleep for one second.
	pthread_mutex_lock(&mutex);							// Lock mutex.
	pthread_cancel(thread_ping);						// Cancel thread_ping if message is not recieved in 1second.
	pthread_mutex_unlock(&mutex);						// Unlock mutex.
	printf("%*d: Sent... Timed Out\n", 2, i[0] + 1);	// Print timed out message.
	return NULL;
}



// Thread to send ping message.
void *thread_ping_main(void *arg)
{
	int *i = (int *) arg;							// i is a pointer to the parameter. 
	char recvBuff[4096];							// Initiailize buffer to store message.
	memset(recvBuff, '\0', sizeof(recvBuff));		// Initiailize/set as 0.


	clock_t start = clock();						// Record time before sending and recieving.
	 
	// Send PING message to server.
	sendto(clientfd, "PING", sizeof("PING"), 0, resolution->ai_addr, resolution->ai_addrlen);

	// Recieve message from server.
	recvfrom(clientfd, recvBuff, sizeof(recvBuff), 0, NULL, NULL);
	
	clock_t stop = clock();							// Record time after sending and recieving.


	pthread_mutex_lock(&mutex);			// Lock mutex.
	pthread_cancel(thread_sleep);		// Cancel thread_sleep if message is recieved instantly.
	pthread_mutex_unlock(&mutex);		// Unlock mutex.


	// If message recieved is "PONG", calculate and print round trip time.
	if (!strcmp(recvBuff, "PONG"))
	{
		times[i[0]] = (double) (stop - start) / CLOCKS_PER_SEC * 1000;	// Calculate round trip time and store in times.
		printf("%*d: Sent... RTT=%f ms\n", 2, i[0] + 1, times[i[0]]);	// Print round trip time.
	}
	else
		printf("%*d: Sent... Invalid Message\n", 2, i[0] + 1);			// Else, print invalid message.

	return NULL;
}



// Main function
int main(int argc, char **argv)
{

	// Check for correct arguments.
	if (argc != 3)
	{
		fprintf(stderr, "usage: ./minor4cli <hostname> <port>\n");
		exit(EXIT_FAILURE);
	}


	// Create socket and check for error.
	if ((clientfd = socket(AF_INET, SOCK_DGRAM, 0)) < 0)
	{
		printf("socket error\n");
		exit(EXIT_FAILURE);
	}


	// Find the server address using command line arguments and check for error.
	if (getaddrinfo(argv[1], argv[2], NULL, &resolution) != 0)
	{
		fprintf(stderr, "%s.\n", strerror(errno));
		exit(EXIT_FAILURE);
	}


	pthread_mutex_init(&mutex, NULL);									// Initialize mutex
	for (int i = 0; i < NUM_PACKETS; i++)								// Loop until 10 "PING" messages are sent.
	{
		times[i] = -1;													// Let times[i] be -1 initially
		pthread_create(&thread_sleep, NULL, thread_sleep_main, &i);		// Create thread_sleep for i.
		pthread_create(&thread_ping, NULL, thread_ping_main, &i);		// Create thread_ping for i.
		pthread_join(thread_sleep, NULL);								// Wait for thread_sleep to end.
		pthread_join(thread_ping, NULL);								// Wait for thread_ping to end.
	}
	pthread_mutex_destroy(&mutex);										// Destroy mutex


	// Calculate results
	int packets_lost = 0;											// Initially, 0packets were lost.
	double average = 0, min = DBL_MAX, max = DBL_MIN;				// Initialize average, min and max doubles.

	for (int i = 0; i < NUM_PACKETS; i++)							// Loop for each of the 10messages.
		if (times[i] == -1)											// if key at i is -1, increments packets_lost.	
			packets_lost++;												
		else                                                        // else,
		{				
			if (times[i] < min)										// If rtt is less than min, set min to it.
				min = times[i];
			if (times[i] > max)										// If rtt is greater than max, set max to it.
				max = times[i];
			average += times[i];									// Calculate sum of the rtts and store as average.
		}

	average /= (NUM_PACKETS - packets_lost);						// Calculate average (sum/packets_recieved)


	// Print results
	printf("%d pkts xmited, %d pkts rcvd, %d%% pkt loss\n", NUM_PACKETS, NUM_PACKETS - packets_lost,
		   (int) ((double) packets_lost / NUM_PACKETS * 100));
	printf("min: %f ms, max: %f ms, avg: %f ms\n", min, max, average);


	// Close socket.
	close(clientfd);

	// Return 0 on success.
	return 0;
}