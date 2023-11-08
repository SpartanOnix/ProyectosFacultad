# Practica01_Redes

Practica 01 de Redes de computadoras

## Integrantes:
-Cruz Jimenez Alejandro
-Reyes Martinez Antonio
-Sandoval Medoza Antonio
-Sinencio Granados Dante Jusepee

## Protocolo:
Para el protocolo STPM seguimos los siguientes comandos:
-Documentacion: https://www.samlogic.net/articles/smtp.htm
```
HELO / EHLO:	This command is used to identify the sender (client) to the SMTP server.
MAIL FROM:	Specifies the e-mail address of the sender.
RCPT TO:	Specifies the e-mail address of the recipient.
DATA	Starts the transfer of the actual data (body text, attachments etc).
RSET (RESET):	Specifies that the current mail transaction will be aborted.
VRFY (VERIFY):	Asks the receiver to confirm that the argument identifies a user or mailbox.
HELP	This command causes the server to send helpful information to the client.
QUIT	Quits the session.
```
## Ejemplo de compilacion:
1. Primero se ejecuta el servidor con: python Servidor.py
2. Luego se ejecuta el clinete con: python Cliente.py -r correo@dominio.com -m Soy_un_mensaje

## Ejemplo del resultado a obtener:
```
C: HELO Server
S: 250 Hello client
C: MAIL FROM: mail@samlogic.com
S: 250 OK
C: RCPT TO: mail@samlogic.com
S: 250 OK
C: DATA
S: 354 End data
C: The message data (body text, subject, e-mail header, attachments etc)
C: .
S: 250 OK, message accepted for delivery: queued as 12345
C: QUIT
S: 221 Bye
```