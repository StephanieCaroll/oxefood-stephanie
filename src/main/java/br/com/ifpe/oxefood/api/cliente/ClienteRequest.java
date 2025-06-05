package br.com.ifpe.oxefood.api.cliente;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.ifpe.oxefood.modelo.cliente.Cliente;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRequest {

    @NotNull(message = "O Nome é de preenchimento obrigatório") // NotNull Válida se o campo está nulo.
    @NotEmpty(message = "O Nome é de preenchimento obrigatório") // NotEmpty Válida se o campo está vazio.
    @Length(max = 100, message = "O Nome deverá ter no máximo {max} caracteres")

    private String nome;

    @JsonFormat(pattern = "dd/MM/yyyy") //Ele vai esperar a anotação em Dia/Mês/Ano
    @NotNull(message = "A data de nascimento é obrigatória.")
    @Past(message = "A data de nascimento não pode ser futura.")
    private LocalDate dataNascimento;

    @NotBlank(message = "O CPF é de preenchimento obrigatório")
    @CPF
    private String cpf;

    @Length(min = 8, max = 20, message = "O campo Fone tem que ter entre 8 e 20 caracteres")
    private String foneCelular;

    @NotBlank(message = "O Telefone Celular é de preenchimento obrigatório")
    @Pattern(regexp = "^81\\d{9}$", message = "O Telefone Celular deve começar com '81' e ter 11 dígitos (ex: 81987654321).")
    private String foneFixo;

    public Cliente build() {

        return Cliente.builder()
                .nome(nome)
                .dataNascimento(dataNascimento)
                .cpf(cpf)
                .foneCelular(foneCelular)
                .foneFixo(foneFixo)
                .build();
    }

}
