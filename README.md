# Chat telnet
app developed with jdk8 and maven 3
chat server that should listen on TCP port 10000 for clients. The chat protocol is very simple, clients connect with "telnet" and write single lines of text. On each new line of text, the server will broadcast that line to all other connected clients.

# For test:
  execute: mvn test

# For run:
  execute: mvn install  
  move to: in folder /target  
  execute: java -jar chat-0.0.1-SNAPSHOT.jar  
  open more shell and type "telnet localhost 10000"
  
  
