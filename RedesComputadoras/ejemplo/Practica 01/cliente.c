#include <stdio.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <unistd.h>
#include <string.h>
#include <stdio.h>
#include <stdlib.h>

int main(int argc, char const *argv[])
{

    if (argc != 3)
    {
        printf("Se requiere solo la direccion IPv4 y el puerto del servidor");
        printf("Ejemplo de ejecucion:");
        printf("./cliente <IPv4> <puerto>");
        return 1;
    }

	int sock = 0, valread;
	struct sockaddr_in serv_addr;
	char hello[1024];
	char buffer[1024] = {0};
	
	if ((sock = socket(AF_INET, SOCK_STREAM, 0)) < 0)
	{
		printf("\n Error durante la creacion del cliente \n");
		return -1;
	}

	serv_addr.sin_family = AF_INET;
	serv_addr.sin_port = htons(atoi(argv[2]));
	
	if(inet_pton(AF_INET, argv[1], &serv_addr.sin_addr)<=0)
	{
		printf("\nLa direccion IP no es valida o no puede ser utilizada \n");
		return -1;
	}

	if (connect(sock, (struct sockaddr *)&serv_addr, sizeof(serv_addr)) < 0)
	{
		printf("\n Error al conectar con el servidor \n");
		return -1;
	}
    while (1)
	{
		fgets(hello, 1024, stdin);
        send(sock , hello , strlen(hello) , 0 );
        valread = read( sock , buffer, 1024);
        printf("%s\n",buffer );
	}
	return 0;
}
