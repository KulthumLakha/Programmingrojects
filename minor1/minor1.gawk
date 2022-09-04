#Kulthum Lakha
#kyl0029

NR>1 { 
	if ( $1 >= 0 ) 
		{ printf "%-5s %-5s\n", $1, $1 %360 }
  	else 
		{ printf "%-5s %-5s\n", $1, 360 - (($1*-1) %360) }
     }