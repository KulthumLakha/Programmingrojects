all : minor4svr minor4cli

minor4svr : minor4svr.c
	gcc -o minor4svr minor4svr.c -Wall

minor4cli : minor4cli.c
	gcc -o minor4cli minor4cli.c -Wall -lpthread

.PHONY : clean

clean :
	rm minor4svr minor4cli
