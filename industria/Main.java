package industria;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Pessoa pessoa = new Pessoa("João Silva", LocalDate.of(1985, 5, 10));
        Funcionario funcionario = new Funcionario("Maria Oliveira", LocalDate.of(1990, 3, 22), new BigDecimal("3500.00"), "Engenheira");

        System.out.println(pessoa);
        System.out.println(funcionario);
    }
}
