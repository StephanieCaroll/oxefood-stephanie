package br.com.ifpe.oxefood.modelo.cliente;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.SQLRestriction;
import org.hibernate.validator.constraints.Length; // Importar para @Length
import org.hibernate.validator.constraints.br.CPF; // Importar para @CPF

import com.fasterxml.jackson.annotation.JsonFormat; // Pode ser útil se a entidade for serializada diretamente

import br.com.ifpe.oxefood.util.entity.EntidadeAuditavel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty; // Embora @NotBlank seja geralmente preferível para Strings
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent; // Importar para @PastOrPresent
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Cliente")
@SQLRestriction("habilitado = true")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cliente extends EntidadeAuditavel {

    @OneToMany(mappedBy = "cliente", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<EnderecoCliente> enderecos;

    @Column(nullable = false, length = 100)
    @NotNull(message = "O Nome é de preenchimento obrigatório")
    @NotEmpty(message = "O Nome é de preenchimento obrigatório")
    @Length(max = 100, message = "O Nome deverá ter no máximo {max} caracteres")
    private String nome;

    @Column(nullable = false) // O campo dataNascimento não poderá ser nulo no DB
    @NotNull(message = "A Data de Nascimento é de preenchimento obrigatório")
    @PastOrPresent(message = "A Data de Nascimento não pode ser futura")
    @JsonFormat(pattern = "dd/MM/yyyy") // Esta anotação é mais para serialização/desserialização, mas pode ser mantida
    private LocalDate dataNascimento;

    @Column(unique = true, nullable = false, length = 11) // Adicionei nullable=false e length=11
    @NotBlank(message = "O CPF é de preenchimento obrigatório")
    @CPF(message = "CPF inválido")
    private String cpf;

    @Column(nullable = false, length = 11) // Adicionei nullable=false e length=11 para consistência com o pattern
    @NotBlank(message = "O Telefone Celular é de preenchimento obrigatório")
    @Pattern(regexp = "^81\\d{9}$", message = "O Telefone Celular deve começar com '81' e ter 11 dígitos (ex: 81987654321).")
    @Length(min = 11, max = 11, message = "O campo Telefone Celular deve ter exatamente {min} caracteres (DDD + 9 dígitos).")
    private String foneCelular;

    @Column(length = 20) // Mantido o length. Não é nullable=false, então pode ser nulo.
    @Length(max = 20, message = "O campo Fone Fixo deve ter no máximo {max} caracteres.") // Replicado do Request
    private String foneFixo;

}