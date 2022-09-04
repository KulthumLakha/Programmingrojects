//Boniya Philomon   
//User ID: bbp0036

#include <stdio.h>

#include <string.h>

#include <stdlib.h>

#include <sys/types.h>

#include <unistd.h>

#include <errno.h>

#define TRUE 1

#define FALSE !TRUE

#define MAX_BUFFER_SIZE 512

#define MAX_ARG_LEN 100

#define MAX_ARG_COUNT 10

#define CMD_LEN 50

#define CMDLINE_LEN 512

#define MAX_PATH_LEN 255

char PS1[100];

char PATH[255];

char currentDirectory[MAX_PATH_LEN];

int returnStatus = 0;

char *progName;

typedef enum { CD, EXIT, CMD, PWD, ECHO } CMDTYPE;

void init()

{

strcpy(PS1, "[batchFile] ");

if (getcwd(currentDirectory, MAX_PATH_LEN) == NULL)

{

perror("getcwd");

exit(1);

}

strcpy(PATH, "/bin");

}

void showPrompt()

{

printf("%s:%s", currentDirectory, PS1);

}

void showWelcomeScreen()

{

//printf("Welcome to GAK Shell...\n");

}

void parseCmdline(const char *cmdline, char *cmd, char ***cmd_argv, int *cmd_argc)

{

int i = 0;

int temp_argc = 0;

int start_index;

char **temp_argv = NULL;

while (cmdline[i] != ' ' && cmdline[i] != '\0' && i < CMD_LEN)

{

cmd[i] = cmdline[i];

i++;

}

if (i == CMD_LEN)

{

fprintf(stderr, "Cmd buffer overflow.\n");

exit(1);

}

cmd[i] = '\0';

temp_argv = malloc(sizeof(char*));

temp_argv[temp_argc] = (char *) malloc((strlen(cmd)+1)*sizeof(char));

strcpy(temp_argv[temp_argc], cmd);

temp_argc++;

while (cmdline[i] != '\0')

{

while (cmdline[i] == ' ')

i++;

start_index = i;

while (cmdline[i] != ' ' && cmdline[i] != '\0')

i++;

if (temp_argv == NULL)

temp_argv = malloc(sizeof(char*));

else

temp_argv = realloc(temp_argv, (temp_argc+1)*sizeof(char*));

temp_argv[temp_argc] = (char *) malloc((i - start_index + 1) * sizeof(char));

//temp_argv[temp_argc] = (char *) malloc(MAX_ARG_LEN * sizeof(char));

strncpy(temp_argv[temp_argc], cmdline+start_index, (i - start_index));

temp_argv[temp_argc][i-start_index] = '\0';

temp_argc++;

}

temp_argv = realloc(temp_argv, (temp_argc+1)*sizeof(char*));

temp_argv[temp_argc] = NULL;

(*cmd_argv) = temp_argv;

(*cmd_argc) = temp_argc;

return;

}

void freeCmdArgs(char ***cmd_argv, int *cmd_argc)

{

int i;

for (i = 0; i < (*cmd_argc); i++)

free((*cmd_argv)[i]);

if ((*cmd_argc) != 0)

free((*cmd_argv));

(*cmd_argc) = 0;

}

CMDTYPE getCmdType(const char *cmd)

{

if (strcmp(cmd, "exit") == 0)

return EXIT;

if (strcmp(cmd, "cd") == 0)

return CD;

if (strcmp(cmd, "pwd") == 0)

return PWD;

if (strcmp(cmd, "echo") == 0)

return ECHO;

return CMD;

}

void echoCmd(int argc, char **argv)

{

int i;

char *envVal;

for (i = 1; i < argc; i++)

{

// Print the last exit status

if (strcmp(argv[i], "$?") == 0)

printf("%d", returnStatus);

// Print the shell's pid

else if (strcmp(argv[i], "$$") == 0)

printf("%d", getpid());

else if (argv[i][0] == '$')

{

if ((envVal = getenv(argv[i]+1)) == NULL)

{

perror("getenv");

return;

}

else

printf("%s", envVal);

}

else

printf("%s", argv[i]);

printf(" ");

}

printf("\n");

}

int execCmd (char *cmd, int argc, char **argv)

{

char cmdPath[MAX_PATH_LEN];

pid_t child_pid;

int retStat;

#ifdef DEBUG

printf ("cmd: %s\n", cmd);

printf ("PATH: %s\n", PATH);

#endif

if (cmd[0] == '/')

strcpy(cmdPath, cmd);

else if (cmd[0] == '.')

{

if (realpath(cmd, cmdPath) == NULL)

{

fprintf(stderr, "%s: '%s' not found\n", progName, cmd);

return 1;

}

}

else

{

strcpy(cmdPath, PATH);

strcat(cmdPath, "/");

strcat(cmdPath, cmd);

}

#ifdef DEBUG

printf("cmdPath: %s\n", cmdPath);

#endif

if (access(cmdPath, F_OK) != 0)

{

fprintf(stderr, "%s: '%s' not found\n", progName, cmd);

return 1;

}

else

{

child_pid = fork();

if (child_pid < 0)

{

perror("fork");

return -1;

}

else if (child_pid == 0)

{

if (execv(cmdPath, argv) == -1)

{

perror("execv");

return 1;

}

}

else

{

wait(&retStat);

return retStat;

}

}

}

void changeDirectory(char *target)

{

char tempPath[MAX_PATH_LEN];

char tempCurDir[MAX_PATH_LEN];

if (target[0] == '/')

strcpy(tempPath, target);

else

{

strcpy(tempPath, currentDirectory);

strcat(tempPath, "/");

strcat(tempPath, target);

}

if (realpath(tempPath, tempCurDir) == NULL)

{

fprintf(stderr, "%s: cd: '%s': %s\n", progName, tempPath, strerror(errno));

return;

}

else

{

if (chdir(tempCurDir) == -1)

{

perror("chdir");

return;

}

strcpy(currentDirectory, tempCurDir);

}

}

void printCurrentWorkingDirectory()

{

char tempPath[MAX_PATH_LEN];

if (getcwd(tempPath, MAX_PATH_LEN) == NULL)

perror("getcwd");

else

printf("%s\n", tempPath);

int main(int argc, char **argv, char **envp)

{

char cmdline[CMDLINE_LEN];

char cmd[CMD_LEN];

char **cmd_argv = NULL;

int cmd_argc;

int i;

int closeShell = FALSE;

char tempPath[MAX_PATH_LEN];

progName = argv[0];

init();

showWelcomeScreen();

while (closeShell != TRUE)

{

showPrompt();

gets(cmdline);

parseCmdline(cmdline, cmd, &cmd_argv, &cmd_argc);

#ifdef DEBUG

printf("++++++++++++++++++++++++++++++++++++++++++++++\n");

for (i = 0; i < cmd_argc; i++)

printf("argv[%d] = %s\n", i, cmd_argv[i]);

printf("++++++++++++++++++++++++++++++++++++++++++++++\n");

#endif

switch(getCmdType(cmd))

{

case ECHO:

echoCmd(cmd_argc, cmd_argv);

break;

case PWD:

printf("%s\n", currentDirectory);

break;

case EXIT:

closeShell = TRUE;

break;

case CD:

changeDirectory(cmd_argv[1]);

break;

case CMD:

returnStatus = execCmd(cmd, cmd_argc, cmd_argv);

break;

}

freeCmdArgs(&cmd_argv, &cmd_argc);

}

return 0;

}
