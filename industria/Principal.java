package industria;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private static final BigDecimal SALARIO_MINIMO = new BigDecimal("1212.00");
    
    public static void main(String[] args) {
        List<Funcionario> funcionarios = new ArrayList<>(Arrays.asList(
                new Funcionario("João", LocalDate.of(2000, 10, 15), new BigDecimal("2500.00"), "Operador"),
                new Funcionario("Maria", LocalDate.of(1990, 5, 10), new BigDecimal("3000.00"), "Operador"),
                new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("4000.00"), "Coordenador"),
                new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("2000.00"), "Diretor"),
                new Funcionario("Alice", LocalDate.of(1995, 8, 5), new BigDecimal("5000.00"), "Recepcionista"),
                new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("4500.00"), "Operador"),
                new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("1000.00"), "Contador"),
                new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3000.00"), "Gerente"),
                new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("2000.00"), "Eletricista"),
                new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("4000.00"), "Gerente")
        ));

        // Remove o funcionário “João” da lista.
        funcionarios.removeIf(funcionario -> "João".equals(funcionario.getNome()));

        // Imprime todos os funcionários com todas suas informações
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for (Funcionario f : funcionarios) {
            String dataNascimento = f.getDataNascimento().format(formatter);
            String salario = String.format("%,.2f", f.getSalario());
            System.out.println(f.getNome() + ", " + dataNascimento + ", " + salario + ", " + f.getFuncao());
        }

        // Aumenta o salário dos funcionários em 10%
        for (Funcionario f : funcionarios) {
            f.setSalario(f.getSalario().multiply(new BigDecimal("1.1")));
        }

        // Agrupa os funcionários por função
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        // Imprime os funcionários, agrupados por função
        for (Map.Entry<String, List<Funcionario>> entry : funcionariosPorFuncao.entrySet()) {
            System.out.println("Função: " + entry.getKey());
            for (Funcionario f : entry.getValue()) {
                System.out.println(f);
            }
        }

        // Imprime os funcionários que fazem aniversário no mês 10 e 12
        System.out.println("Aniversariantes de Outubro e Dezembro:");
        for (Funcionario f : funcionarios) {
            int mesAniversario = f.getDataNascimento().getMonthValue();
            if (mesAniversario == 10 || mesAniversario == 12) {
                System.out.println(f);
            }
        }

        // Imprime o funcionário com a maior idade
        Funcionario maisVelho = Collections.max(funcionarios, Comparator.comparing(Funcionario::getDataNascimento));
        System.out.println("Funcionário com a maior idade: " + maisVelho.getNome() + ", Idade: " + (LocalDate.now().getYear() - maisVelho.getDataNascimento().getYear()));

        // Imprime a lista de funcionários por ordem alfabética
        List<Funcionario> funcionariosOrdenados = new ArrayList<>(funcionarios);
        funcionariosOrdenados.sort(Comparator.comparing(Funcionario::getNome));
        System.out.println("Funcionários em ordem alfabética:");
        for (Funcionario f : funcionariosOrdenados) {
            System.out.println(f);
        }

        // Imprime o total dos salários dos funcionários
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Total dos salários: " + String.format("%,.2f", totalSalarios));

        // Imprime quantos salários mínimos ganha cada funcionário
        System.out.println("Salários em múltiplos do salário mínimo:");
        for (Funcionario f : funcionarios) {
            BigDecimal salariosMinimos = f.getSalario().divide(SALARIO_MINIMO, 2, BigDecimal.ROUND_HALF_UP);
            System.out.println(f.getNome() + " ganha " + salariosMinimos + " salários mínimos");
        }
    }
}
