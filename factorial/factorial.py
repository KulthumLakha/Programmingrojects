import argparse
import math

def main():
    parser = argparse.ArgumentParser()
    parser.add_argument("integer", type=int)
    args = parser.parse_args()
    n = math.factorial(args.integer)
    print(n)

main()