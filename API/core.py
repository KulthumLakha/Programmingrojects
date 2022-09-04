import argparse
import requests

parser = argparse.ArgumentParser(description='API Currency Converter.')
parser.add_argument('-f', '--from', dest="FROM", default="USD", help='3 letter currency code to convert from')
parser.add_argument('-t', '--to', dest="TO", default="CAD", help='3 letter currency code to convert to')
parser.add_argument('-a', '--amount', dest="AMOUNT", default="1", help='Amount of currency to convert to')

args = parser.parse_args()

url = f"https://api.apilayer.com/fixer/convert?to={args.TO}&from={args.FROM}&amount={args.AMOUNT}"
headers= {
    "apikey": "waogf8ThfKg1tVKL0ECRB8rQNMbYsGNr"
}    
payload = {}
response = requests.request("GET", url, headers=headers, data = payload).json()

print(f"{args.AMOUNT} {args.FROM} is {response['result']} {args.TO}")