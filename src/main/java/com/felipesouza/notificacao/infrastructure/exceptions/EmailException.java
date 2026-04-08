package com.felipesouza.notificacao.infrastructure.exceptions;

//Extende a RunTimeException pois é com ela que criamos exceções não verificadas para tratarmos de acordo com nossa lógica
public class EmailException extends RuntimeException {

    //Recebe uma mensagem e passa para a classe pai RuntimeException
    public EmailException(String message) {
        super(message);
    }

    //Recebe uma mensagem e causa do erro(throwable)
    //Throwable é a classe base de todos os erros em Java
    public EmailException(String message, Throwable throwable) {
        super(message, throwable);
    }
}