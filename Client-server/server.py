import socket
import threading

# Socket creation.
server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server.bind(('127.0.0.1', 65157))
server.listen()

# List to store clients and their usernames.
clients = []
userNames = []
blocked = []
blocked.append("null")

# Function to forward messages.
def rebroadcast(message):
    for client in clients:
        client.send(message)

# Function to deal with each client.
def dealWith(client):
    while True:

        index1 = clients.index(client)
        user = userNames[index1]
    
        if user not in blocked:
            message = (client.recv(1024)).decode('ascii')
            print('Recieved message from ' + user + '. Message: ' + message)
               
            if message.lower() == "!help":
                helpMenu = "<Server> There are seven commands that can be used to interact with the chat client. They are as follows:\n         1. !Send - The default behavior of the chatroom. no command needed.\n         2. !User - Sends a message to one specific user.\n                  Format as <UserName>-Message\n         3. !List - Prints a list of all users in the chatroom.\n         4. !Quit - Disconnects and closes the chatroom.\n         5. !Help - Displays this message.\n         6. !Block - Displays this message.\n                  Format as !block <UserName>\n         7. !Unblock - Displays this message\n                  Format as !unblock <UserName>"
                client.send('{}'.format(helpMenu).encode('ascii'))

            elif message.lower() == "!user":
                msg = (client.recv(1024)).decode('ascii')
                private = msg.split("-")
                who = clients[userNames.index(private[0])]
                what = private[1]
                who.send('<{}> {}'.format(user, what).encode('ascii'))

            elif message.lower() == "!list":
                client.send('{}'.format(userNames).encode('ascii'))

            elif message.lower() == "!quit":
                clients.remove(client)
                client.close()           
                rebroadcast('{} left!'.format(user).encode('ascii'))
                print('{} left!'.format(user))
                userNames.remove(user)
                break

            elif message.lower().startswith('!block'):
                blockWho = (message.split(" "))[1]
                blocked.append(blockWho)

            elif message.lower().startswith('!unblock'):
                unblockWho = (message.split(" "))[1]
                blocked.remove(unblockWho)

            else:
                forAll = '<{}> {}'.format(user, message)            
                rebroadcast(forAll.encode('ascii'))

        else:
            ignore = (client.recv(1024)).decode('ascii')

# Function that accepts connections and starts a thread for multiple clients.
def main():
    while True:
        client, address = server.accept()
        client.send('test'.encode('ascii'))
        userName = client.recv(1024).decode('ascii')

        userNames.append(userName)
        clients.append(client)

        print("Connected by {} at ".format(userName) + "{}".format(str(address)))
        client.send('<server> Welcome {}'.format(userName).encode('ascii'))

        thread = threading.Thread(target=dealWith, args=(client,))
        thread.start()

if __name__ == "__main__":
    main ()