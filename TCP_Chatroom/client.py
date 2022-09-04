import socket
import threading
import sys

def main(username):
    client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    client.connect(('127.0.0.1', 65157))

    def receive():
        while True:
            try:
                message = client.recv(1024).decode('ascii')
                if message == 'test':
                    client.send(username.encode('ascii'))
                else:
                    print(message)
            except:
                print("An error occured!")
                client.close()
                exit()

    def write():
        while True:
            message = '{}'.format(input(''))
            client.send(message.encode('ascii'))

    receive_thread = threading.Thread(target=receive)
    receive_thread.start()

    write_thread = threading.Thread(target=write)
    write_thread.start()

if __name__ == "__main__":
    if len(sys.argv) != 2:
        print ("Usage: client.py <Username>")
        exit()
    else:
        main(str(sys.argv[1]))