#include <unistd.h>
#include <stdio.h>
#include <sys/socket.h>
#include <stdlib.h>
#include <netinet/in.h>
#include <string.h>
#include <arpa/inet.h>
int main(int argc, char const *argv[])
{

    if (argc != 2)
    {
        printf("se requiere unicamente el puerto como argumento");
        printf("Ejemplo de ejecución:");
        printf("./servidor <puerto>");
        return 1;
    }

	int server_fd, new_socket, valread;
	struct sockaddr_in address;
	int opt = 1;
	int addrlen = sizeof(address);
	char buffer[1024] = {0};
	char respuesta[1024] = {0};
	
	if ((server_fd = socket(AF_INET, SOCK_STREAM, 0)) == 0)
	{
		perror("Error al crear el socket");
		exit(EXIT_FAILURE);
	}
	
	if (setsockopt(server_fd, SOL_SOCKET, SO_REUSEADDR , &opt, sizeof(opt)))
	{
		perror("Error al asignar el puerto al socket");
		exit(EXIT_FAILURE);
	}
	address.sin_family = AF_INET;
	address.sin_addr.s_addr = INADDR_ANY;
	address.sin_port = htons(atoi(argv[1]));
	
	if (bind(server_fd, (struct sockaddr *)&address, sizeof(address))<0)
	{
		perror("Error en la construccion del socket");
		exit(EXIT_FAILURE);
	}

	if (listen(server_fd, 3) < 0)
	{
		perror("Error, el puerto ya fue utilizado");
		exit(EXIT_FAILURE);
	}

	if ((new_socket = accept(server_fd, (struct sockaddr *)&address,
					(socklen_t*)&addrlen))<0)
	{
		perror("Error al crear el servidor");
		exit(EXIT_FAILURE);
	}


    while (read( new_socket , buffer, 1024))
    {
        printf("Comando solicitado en el cliente: %s\n",buffer);
		FILE *fpipe;
		//char *command = "cat READEME.md";
		char *command = buffer;
		char c = 0;

		if (0 == (fpipe = (FILE*)popen(command, "r")))
		{
			perror("popen() failed.");
			exit(EXIT_FAILURE);
		}

		fread(&respuesta, 1024, 1024, fpipe);
		pclose(fpipe);
        send(new_socket , respuesta , strlen(respuesta) , 0);
		memset(buffer,0,strlen(buffer));
		memset(command,0,strlen(command));
		memset(respuesta,0,strlen(respuesta));
    }
	return 0;
}
