// Programa 2C Ernesto Crespo
#include <cstdio>
#include <unistd.h>
int main() {
    	char* argv[2];
	argv[0] = (char*) "prog1";
	argv[1] = nullptr;

	print("PID %d running prog2\n", getpid());
	pid_t p = fork();
	pid_t q = fork();
	if (p == 0 || q == 0) {
		int r = execv("./prog1", argv);
	} else { 	
		print("PID %d exiting from prog2\n", getpid());
	}
}