num = input("Please enter a four digit number. ")

x = True
count = 0
while x:
    count +=1
    number = [str(x) for x in num]
    number.sort()
    ascending = ''.join(number)
    number.reverse()
    descending = ''.join(number)
    num = str(int(descending)- int(ascending))
    print(f">> {num}")
    if int(num) == 6174:
        break
    
print(f"It took {count} iterations!")

