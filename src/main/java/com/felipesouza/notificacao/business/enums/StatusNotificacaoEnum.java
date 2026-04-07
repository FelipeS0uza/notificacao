/* Os enums são um tipo especial de classe que representa um grupo fixo de constantes.
* São usados para representar valores que não mudam.
* Exemplos: Dias da semana, estados de um objeto, opções de estado de contrato, etc */

package com.felipesouza.notificacao.business.enums;

//Por se tratar de uma classe especial, ao invés de public class, usamos o public enum
public enum StatusNotificacaoEnum {
    //Só especificar as opções disponiveis
    PENDENTE,
    NOTIFICADO,
    CANCELADO
}