//Camada DTO - Data Transfer Object
//É um modelo de projeto usado para transportar dados, evitando passar dados sensíveis para outro serviço.

package com.felipesouza.notificacao.business.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.felipesouza.notificacao.business.enums.StatusNotificacaoEnum;
import lombok.*;

import java.time.LocalDateTime;

@Getter //Cria todos os getters
@Setter //Cria todos os setters
@AllArgsConstructor //  Cria o construtor recebendo todos os argumentos
@NoArgsConstructor  //Cria o construtor não recebendo nenhum argumento
@Builder    //Simplifica a instância de classes fornecendo os parâmetros na ordem desejada e de uma só vez.
public class TarefasDTO {

    private String id;
    private String nomeTarefa;
    private String descricao;
    private String emailUsuario;
    private StatusNotificacaoEnum statusNotificacaoEnum;

    //JsonFormat faz a serialização da data e hora, assim fazendo a conversão para o formato que o BD compreenda
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataCriacao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataAgendamento;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataAlteracao;
}