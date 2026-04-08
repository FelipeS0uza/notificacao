//Camada CONTROLLER recebe as requisições HTTP, mapeia os endpoints, chama a Service para usar os metodos
// e retorna respostas HTTP conforme o necessário.
// Não deve conter lógica de negócio (cálculos, validações complexas, regras de banco)

package com.felipesouza.notificacao.controller;

import com.felipesouza.notificacao.business.EmailService;
import com.felipesouza.notificacao.business.dto.TarefasDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController  //Indica para o spring que essa classe é o controlador e que vai lidar com as requisições (Padrão REST)
@RequiredArgsConstructor    //Gera um construtor que inicializa apenas os campos PRIVATE FINAL
@RequestMapping("/email")   //Responsável por apontar qual é a URI da controller
public class EmailController {

    //Injeção de dependências
    private final EmailService emailService;

    //ResponseEntity<> é uma classe que indica que o metodo vai retornar uma resposta HTTP do tipo que estiver dentro de <>
    //RequestBody indica que estou passando um objeto no corpo da requisição

    @PostMapping    //Indica que o metodo é um POST
    public ResponseEntity<Void> enviarEmail(@RequestBody TarefasDTO dto) {
        //É feito o envio do email, recebendo a tarefa dto como parametro
        emailService.enviaEmail(dto);
        //Caso esteja tudo ok, retorna um ok.
        return ResponseEntity.ok().build();
    }
}