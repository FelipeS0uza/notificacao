//Camada SERVICE é onde se desenvolve as regras de negócio e toda a lógica e recebe as requisições do controller
//Intermedia a comunicação entre o Controller e o Repository

package com.felipesouza.notificacao.business;

import com.felipesouza.notificacao.business.dto.TarefasDTO;
import com.felipesouza.notificacao.infrastructure.exceptions.EmailException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

@Service    //Indica ao spring que é uma Service
@RequiredArgsConstructor    //Gera um construtor que inicializa apenas os campos PRIVATE FINAL
public class EmailService {

    //Injeção de dependências
    private final JavaMailSender javaMailSender;    //Interface do Spring usada para enviar e-mails via SMTP
    private final TemplateEngine templateEngine;    //Ferramenta que permite criar arquivos HTML dinâmicos de forma organizada

    //Anotação para indicar que a variavel será definida no application.yaml
    @Value("${envio.email.remetente}")
    public String remetente;

    @Value("${envio.email.nomeRemetente}")
    private String nomeRemetente;

    public void enviaEmail(TarefasDTO dto) {

        try{
            //É o objeto que representa o email completo, com toda a estrutura necessária para suportar conteúdo além de texto simples.
            //O protocolo de email original (SMTP) foi criado para enviar apenas texto simples.
            // O MIME surgiu para estender essa capacidade, permitindo emails com:
            // HTML formatado, Anexos, Caracteres especiais (UTF-8, acentos) e Imagens embutidas no corpo.
            MimeMessage mensagem = javaMailSender.createMimeMessage();

            //Facilitar a construção do MimeMessage.
            // O true habilita modo multipart (necessário para HTML/anexos) e UTF_8 define a codificação.
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mensagem, true, StandardCharsets.UTF_8.name());

            //Define o remetente do email com endereço e nome de exibição
            mimeMessageHelper.setFrom(new InternetAddress(remetente, nomeRemetente));

            //Define o destinatário parseando a string de email do DTO — parse() suporta múltiplos emails separados por vírgula.
            mimeMessageHelper.setTo(InternetAddress.parse(dto.getEmailUsuario()));

            //Define o assunto do email.
            mimeMessageHelper.setSubject("Notificação de tarefa");

            //Cria o contexto do Thymeleaf e injeta as variáveis dinâmicas que serão substituídas no template HTML
            Context context = new Context();
            context.setVariable("nomeTarefa", dto.getNomeTarefa());
            context.setVariable("dataAgendamento", dto.getDataAgendamento());
            context.setVariable("descricao", dto.getDescricao());

            //Processa o arquivo notificacao.html substituindo as variáveis pelo contexto, gerando o HTML final do email.
            String template = templateEngine.process("notificacao", context);

            //Define o corpo do email com o HTML gerado. O true indica que o conteúdo é HTML (e não texto puro)
            mimeMessageHelper.setText(template, true);

            //Envia o email de fato usando o servidor SMTP configurado.
            javaMailSender.send(mensagem);

        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new EmailException("Erro ao enviar o email " + e.getCause());
        }
    }
}