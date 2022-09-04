import argparse
from time import time
import string
import urllib2


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
symbols = ['!', '"', '#', '$', '%', '&', "'", '(', ')', '*', '+', ',', '-', '.', '/', ':', ';', '<', '=', '>', '?', '@', '[', '\\', ']', '^', '_', '`', '{', '|', '}', '~']
l_u = lowercase + uppercase
l_n = lowercase + numbers
l_s = lowercase + symbols
u_n = uppercase + numbers
u_s = uppercase + symbols
n_s = numbers + symbols
l_u_n = lowercase + uppercase + numbers
l_u_s = lowercase + uppercase + symbols
l_n_s = lowercase + numbers + symbols
u_n_s = uppercase + numbers + symbols
l_u_n_s = lowercase + uppercase + numbers + symbols

#def gen_random_range(min, max):
 #return int((time() - float(str(time()).split('.')[0])) * (max - min) + min)

def get_random(x,y):
    url = 'http://www.random.org/integers/?num=1&min=[min]&max=[max]&col=1&base=10&format=plain&rnd=new'
    url = url.replace("[min]", str(x))  
    url = url.replace("[max]", str(y))  
    response = urllib2.urlopen(url)
    num = response.read()
    return num.strip()


numofchar = input("How many characters do you want in your password(min 8)? ")
password = ""
for x in range(int(numofchar)):
    if args.lower and not args.upper and not args.number and not args.symbol:
        password += lowercase[get_random] 
    elif not args.lower and not args.upper and args.number and not args.symbol:
        password += numbers[x] 
    elif not args.lower and args.upper and not args.number and not args.symbol:
        password += uppercase[x] 
    elif not args.lower and not args.upper and args.number and not args.symbol:
        password += numbers[x] 
    elif not args.lower and not args.upper and not args.number and args.symbol:
        password += symbols[x] 
    elif args.lower and args.upper and not args.number and not args.symbol:
        password += l_u[x] 
    elif args.lower and not args.upper and args.number and not args.symbol:
        password += l_n[x] 
    elif args.lower and not args.upper and not args.number and args.symbol:
        password += l_s[x] 
    elif not args.lower and args.upper and args.number and not args.symbol:
        password += u_n[x] 
    elif not args.lower and args.upper and not args.number and args.symbol:
        password += u_s[x] 
    elif not args.lower and not args.upper and args.number and args.symbol:
        password += n_s[x] 
    elif args.lower and args.upper and args.number and not args.symbol:
        password += l_u_n[x] 
    elif args.lower and args.upper and not args.number and args.symbol:
        password += l_u_s[x] 
    elif args.lower and not args.upper and args.number and args.symbol:
        password += l_n_s[x] 
    elif not args.lower and args.upper and args.number and args.symbol:
        password += u_n_s[x] 
    else:
        password += l_u_n_s[x] 
print(password)
