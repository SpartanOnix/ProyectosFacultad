#include <stdlib.h>
#include <stdio.h>
#include <stdint.h>
#include <arpa/inet.h>
#include <sys/socket.h>

// Ejemplo de ejecucion: ./servidor 1000
int main(int argc, char const *argv[]) {

	struct sockaddr_in addr_serv;
	addr_serv.sin_family = AF_INET;
	addr_serv.sin_addr.s_addr = INADDR_ANY;
	addr_serv.sin_port = htons(atoi(argv[1]));

	int servidor = socket(AF_INET, SOCK_STREAM, 0);
	int activado = 1;
	setsockopt(servidor, SOL_SOCKET, SO_REUSEADDR, &activado, sizeof(activado));

	if (bind(servidor, (void*) &addr_serv, sizeof(addr_serv)) != 0) {
		perror("Falló el enlace");
		return 1;
	}

	printf("Estoy escuchando\n");
	listen(servidor, 100);

	// Si se pudo lograr el enlace con el socket, continuemos la conexion
	struct sockaddr_in addr_clnt;
	unsigned int addr;
	char* senal = malloc(1000);
	int cliente = accept(servidor, (void*) &addr_clnt, &addr);

	printf("Conexión en: %d\n", cliente);
	send(cliente, "Hola cliente", 13, 0);
	
	while (1) {
		int info = recv(cliente, senal, 1000, 0);
		if (info <= 0) {
			perror("Se termino la señal del cliente");
			return 1;
		}
		senal[info] = '\0';
	}
	free(senal);
	return 0;
}