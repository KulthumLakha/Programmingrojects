#Kulthum Lakha
#kyl0029
#10/1/2021
#This bash script monitors who logs in and out of the CSE linux machine where the script is running.

#Shebang.
#!/bin/bash



# Initialize number of users as 0.
userNo=0

# Function that reports users logged in initially.
initialUsers()
{
	# Report time at which monitoring begins.
	echo `date` ") initial users logged in "

	# for loop to report userIDs of users who are already logged in.
	for x in $(users)
	do
		# Report hostname along with userID.
		echo "> $x logged in to" $(hostname)
		
		# Increment number of users
		userNo=$((userNo+1))
	done
}



# Initialize counter variable to 0 for trap.
counter=0

# Function
signal()
{

	#Increment counter
	let counter++

	# If counter is one, then print prompt.
	if [ $counter -eq 1 ]
	then
		echo "(SIGINT) ignored. enter ^C one more time to terminate program."
		
	# Else if counter is 2, then terminate program.
	elif [ $counter -eq 2 ]
	then
		exit
	fi	
}

# Call initialUsers function to print logged in users at start of script.
initialUsers

# Report number of users already logged in at the start of the script.
echo `date` ") # of users: ${userNo}"









# Call signal function when SIGINT detected
trap signal 2

# Report number of user logged in to the machine every 10seconds.
while true
do
	# Store logged in users in temporary file, file1.
	who | cut -d' ' -f1 | sort -u > file1

	# Wait for 10seconds.
	sleep 10

	# Store still logged in users in temorary file; file2.
	who | cut -d' ' -f1 | sort -u > file2


	# If the difference between the two files has the character '>'
	# Store the userID in temporary file; loggedIn
	diff file1 file2 | grep ^\> | cut -d' ' -f2 > loggedIn

	# If the difference between the two files has the character '<'
	# Store the userID in temporary file; loggedOut
	diff file1 file2 | grep ^\< | cut -d' ' -f2 > loggedOut

	# If loggedIn file isn't empty,
	# Report userIDs with machines they are logged in to.
	if [[ -s loggedIn ]]
	then
		for i in `cat loggedIn`
		do 	
			# Increment userNo.	
			userNo=$((userNo+1))
			echo "> $i logged in to" $(hostname)
		done
	fi

	# If loggedOut file isn't empty,
	# Report userIDs with machines they are logged out of.
	if [[ -s loggedOut ]]
	then
		for i in `cat loggedOut`
		do 
			# Decrement userNo.
			userNo=$((userNo-1))
			echo "> $i logged out of" $(hostname)
		done
	fi


	echo `date` ") # of users:" ${userNo} 
	sleep 10
done


# Delete temprary files.
rm file1
rm file2
rm loggedIn
rm loggedOut


