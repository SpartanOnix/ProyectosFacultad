#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <arpa/inet.h>
#include <sys/socket.h>

// Ejemplo de ejecucion: ./cliente 127.0.0.1 1000
int main(int argc, char const *argv[]) {

	char mensaje[1000];
	struct sockaddr_in addr_serv;
	addr_serv.sin_family = AF_INET;
	addr_serv.sin_addr.s_addr = inet_addr(argv[1]);
	addr_serv.sin_port = htons(atoi(argv[2]));
	int cliente = socket(AF_INET, SOCK_STREAM, 0);

	if (connect(cliente, (void*) &addr_serv, sizeof(addr_serv)) != 0) {
		perror("No se pudo conectar");
		return 1;
	}

	while (1) {
		fgets(mensaje, 1000, stdin);
        send(cliente , mensaje , strlen(mensaje) , 0 );
        printf("%s\n", mensaje);
	}

	return 0;
}