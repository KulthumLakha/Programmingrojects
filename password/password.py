import argparse
import time

def shuffle(anyList):
    for i in range(len(anyList)):
        j = int((int(str(time.time())[-1]))/2)
        k = int((int(str(time.time())[-3]))/2)
        if j < len(anyList):
            anyList[i], anyList[j] = anyList[j], anyList[i]
        if k < len(anyList):
            anyList[i], anyList[k] = anyList[k], anyList[i]

def shuffleList(anyList):
    for i in range(len(anyList)*5):
        shuffle(anyList)

def main():
    parser = argparse.ArgumentParser()
    parser.add_argument('-l', dest="lower", action='store_true', default=False)
    parser.add_argument('-u', dest="upper", action='store_true', default=False)
    parser.add_argument('-n', dest="number", action='store_true', default=False)
    parser.add_argument('-s', dest="symbol", action='store_true', default=False)
    parser.add_argument('-o', dest="file", default="None")
    args = parser.parse_args()

    lowercase = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z']
    uppercase = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z']
    numbers = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9']
    symbols = ['!', '"', '#', '$', '%', '&', "'", '*', '+', ',', '-', '.', '/', ':', ';', '<', '=', '>', '?', '@', '\\', '^', '_', '`', '|', '~']
    mainList =[]

    if args.lower:
        mainList += lowercase
    if args.upper:
        mainList += uppercase
    if args.number:
        mainList += numbers
    if args.symbol:
        mainList += symbols
    if not args.lower and not args.upper and not args.number and not args.symbol:
        mainList = lowercase + uppercase + numbers + symbols

    shuffleList(mainList)

    numofchar = input("How many characters do you want in your password(min 8)? ")
    password = ""
    for x in range(int(numofchar)):
        password += mainList[x] 

    if args.file == "None":
        print(password)
    else:
        f = open(args.file, "w")
        f.write(password)
    return password

if __name__ == "__main__":
    main()