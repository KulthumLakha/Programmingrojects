Group members:

    Jack Henderson
    Lluviana Vasquez
    Kulthum Lakha
    Boniya Philomon

Project Information:

    Focuses on shell and system calls. This shell should read the line from the standard input (interactive mode)
    or a file (batch mode), parse the line with command and arguments, execute the commands with arguments, then 
    prompt for more input when it is finished. There is interactive mode where it displays a prompt (string of your 
    choosing) and the user of the shell will type in a command at the prompt. There is also a batch mode where the 
    shell is started by specifying a batch file on its command line. It contains a list of commands that need to be 
    executed. There is also another portion of this project that focuses on each person working on something 
    individually. There are built in commands that are: cd, exit, path, and myhistory. There is also redirection, 
    pipeline, signal control, and alias support. 


Project organization:

    Jack Henderson: 
        - Primarily responsible for: Built-in command ->    myhistory    
                                     Functionality ->       Alias support
        - myhistory data structuring
        
    Lluviana Vasquez
        - Primarily responsible for: Built-in command ->    exit
                                     Functionality ->       I/O redirection
        - Documentation of readme file

    Kulthum Lakha
        - Primarily responsible for: Built-in command ->    cd
                                     Functionality ->       Signal handling
        - Command line handling in main
        - Multiple commands
        - Makefile and batchfile 
        - Header file
        - Interactive mode

    Boniya Philomon
        - Primarily responsible for: Built-in command ->    path
                                     Functionality ->       Pipelining

Design Overview:

    Lluviana Vasquez: In charge of exit and I/O redirection.
    Exit command consists of a simple code of about 10 lines.
    While I/O redirection, was more lengthy. Redirection will
    support stdin and stdout to files. There was a redirection
    for in, and a redirection for out. In and Out are similar, 
    both take a file name and open it. If the command is able 
    to open it, then it is duplicated to stdin or stdout 
    respectively. 

    Jack Henderson:

    Kulthum Iakha: In charge of cd and signal handling.
    Also worked on multiple commands. One bug is double 
    execution of built-in commands; specifically cd and 
    myhistory. Signal Handling works well but I did not 
    get enough time to rewrite the code where it would 
    get parsed and executed only once.

    Boniya Philomon:

Complete Specification:

    


Known Bugs or Problems:

    - I/O has a problem with files. 

