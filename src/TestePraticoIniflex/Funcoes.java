package TestePraticoIniflex;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Funcoes {
	
    private static final int OP_LISTAR_TODOS = 1;
    private static final int OP_LISTAR_SEM_JOAO = 2;
    private static final int OP_AUMENTAR_SALARIO = 3;
    private static final int OP_AGRUPAR_POR_FUNCAO = 4;
    private static final int OP_AGRUPAR_POR_ANIVERSARIO = 5;
    private static final int OP_FUNCIONARIO_MAIS_VELHO = 6;
    private static final int OP_FUNCIONARIO_MAIS_NOVO = 7;
    private static final int OP_ORDENAR_ALFABETICAMENTE = 8;
    private static final int OP_TOTAL_SALARIOS = 9;
    private static final int OP_SALARIO_MINIMO_POR_FUNCIONARIO = 10;

    private static List<Funcionario> criarListaFuncionarios(){
        System.out.println("Inserindo Funcionários!");
        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 02), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 05), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 12, 02), new BigDecimal("2799.93"), "Gerente"));
        System.out.println("Funcionários inseridos com sucesso!");
        return funcionarios;
    }
    
    private static void listarTodosFuncionarios(List<Funcionario> Listafuncionarios) {

        //Imprimir todos os funcionários
        System.out.println("Funcionários:");
        Listafuncionarios.forEach(funcionario -> System.out.println(funcionario));
    }

    private static void listarFuncionariosSemJoao(List<Funcionario> Listafuncionarios) {

        //Remover o funcionário “João” da lista e imprimi-la
        Listafuncionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));
        System.out.println("Funcionários:");
        Listafuncionarios.forEach(funcionario -> System.out.println(funcionario));
    }

    private static void aumentarSalarioEListarFuncionarios(List<Funcionario> Listafuncionarios) {

        //Aumentar o salário dos funcionários em 10%
        Listafuncionarios.forEach(funcionario -> {
            BigDecimal salarioAtualizado = funcionario.getSalario().multiply(new BigDecimal("1.10"));
            funcionario.setSalario(salarioAtualizado);
        });

        System.out.println("Funcionários:");
        Listafuncionarios.forEach(funcionario -> System.out.println(funcionario));
    }

    private static void agruparFuncionariosEImprimir(List<Funcionario> Listafuncionarios) {

        //Agrupar os funcionários por função em um MAP
        Map<String, List<Funcionario>> funcionariosPorFuncao = Listafuncionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        //Imprimir os funcionários agrupados por função
        System.out.println("\nFuncionários agrupados por função:");
        funcionariosPorFuncao.forEach((funcao, listaPorFuncao) -> {
            System.out.println("Função: " + funcao);
            listaPorFuncao.forEach(funcionario -> System.out.println("\t" + funcionario.toString()));
        });
    }

    private static void imprimirFuncionariosPorAniversario(List<Funcionario> Listafuncionarios) {

        //Imprimir os funcionários que fazem aniversário nos meses 10 e 12
        List<Funcionario> aniversariantes = Listafuncionarios.stream()
                .filter(funcionario -> funcionario.getDataNascimento().getMonthValue() == 10 ||
                                        funcionario.getDataNascimento().getMonthValue() == 12)
                .collect(Collectors.toList());
        System.out.println("\nAniversariantes dos meses 10 e 12:");
        aniversariantes.forEach(funcionario -> System.out.println(funcionario.toString()));
    }  

    private static void imprimirFuncionarioMaisVelho(List<Funcionario> Listafuncionarios) {

        //Encontrar o funcionário mais velho
        Comparator<Funcionario> comparadorIdade = Comparator.comparingInt(funcionario ->
                funcionario.getDataNascimento().until(LocalDate.now()).getYears());
        Funcionario maisVelho = Listafuncionarios.stream()
                .max(comparadorIdade)
                .orElse(null);
        if (maisVelho != null) {
            int idade = maisVelho.getDataNascimento().until(LocalDate.now()).getYears();
            System.out.println("\nFuncionário mais velho: " + maisVelho.getNome() + ", Idade: " + idade + " anos");
        }       
    }  

    private static void imprimirFuncionarioMaisNovo(List<Funcionario> Listafuncionarios) {

        //Encontrar o funcionário mais novo
        Comparator<Funcionario> comparadorIdade = Comparator.comparingInt(funcionario ->
                funcionario.getDataNascimento().until(LocalDate.now()).getYears());

        Funcionario maisNovo = Listafuncionarios.stream()
                .min(comparadorIdade)
                .orElse(null);

        if (maisNovo != null) {
            int idade = maisNovo.getDataNascimento().until(LocalDate.now()).getYears();
            System.out.println("\nFuncionário mais novo: " + maisNovo.getNome() + ", Idade: " + idade + " anos");
        }       
    } 

    private static void ordernarListaPorOrdermAlfabetica(List<Funcionario> Listafuncionarios) {

        //Ordenar a lista de funcionários por ordem alfabética
        Listafuncionarios.sort(Comparator.comparing(Funcionario::getNome));
        System.out.println("\nFuncionários ordenados por nome:");
        Listafuncionarios.forEach(funcionario -> System.out.println(funcionario.toString()));      
    } 

    private static void calcularTotalSalario(List<Funcionario> Listafuncionarios) {
    
        //Calcular o total dos salários dos funcionários
        BigDecimal totalSalarios = Listafuncionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("\nTotal dos salários dos funcionários: " + String.format("%,.2f", totalSalarios));        
    }

    private static void imprimirSalarioMinimoPorFuncionario(List<Funcionario> funcionarios) {

        //Imprimir quantos salários mínimos ganha cada funcionário (salário mínimo = R$1212.00)
        System.out.println("\nSalários em termos de salários mínimos:");
        funcionarios.forEach(funcionario -> {
            BigDecimal salarioMinimo = new BigDecimal("1212.00");
            BigDecimal salariosMinimos = funcionario.getSalario().divide(salarioMinimo, 2, RoundingMode.HALF_UP);           
            System.out.println(funcionario.getNome() + ": " + salariosMinimos + " salários mínimos");
        });  
    }

    private static void imprimirMenuEOpcoes(List<Funcionario> funcionarios) {

        System.out.println("\nMenu de opções: ");
        System.out.println("1 - Listar todos os funcionários");
        System.out.println("2 - Listar todos os funcionários menos João");
        System.out.println("3 - Aumentar o salario em 10% e lista-los");
        System.out.println("4 - Agrupar funcionários por função e lista-los");
        System.out.println("5 - Agrupar funcionários por aniversário e lista-los");
        System.out.println("6 - Listar funcionário mais velho ");        
        System.out.println("7 - Listar funcionário mais novo ");
        System.out.println("8 - Listar funcionários por ordem alfabética");
        System.out.println("9 - Listar total de salário");
        System.out.println("10 - Listar salário mínimo por funcionário");
        
        try (Scanner scanner = new Scanner(System.in)) {
        	
        	String opcaoStr =  scanner.nextLine().trim(); 
        	
        	if (opcaoStr.isEmpty()) {
        		System.out.println("Opção inválida, é necessário escolher uma opção de 1 a 10!");
                imprimirMenuEOpcoes(funcionarios);
                return ;
        	}
        			
        	int opcaoEscolhida = Integer.parseInt(opcaoStr);

            if (opcaoEscolhida < 1 || opcaoEscolhida > 10) {
                System.out.println("Opção inválida!");
                imprimirMenuEOpcoes(funcionarios);
                return ;
            }
            else {
                switch (opcaoEscolhida) {	                   
                    case OP_LISTAR_TODOS:
                    	listarTodosFuncionarios(funcionarios);
                    	 break;
                    case OP_LISTAR_SEM_JOAO:
                    	listarFuncionariosSemJoao(funcionarios);
                    	 break;
                    case OP_AUMENTAR_SALARIO:
                    	aumentarSalarioEListarFuncionarios(funcionarios);
                    	 break;
                    case OP_AGRUPAR_POR_FUNCAO:
                    	agruparFuncionariosEImprimir(funcionarios);
                    	 break;
                    case OP_AGRUPAR_POR_ANIVERSARIO: 
                    	imprimirFuncionariosPorAniversario(funcionarios);
                    	 break;
                    case OP_FUNCIONARIO_MAIS_VELHO: 
                    	imprimirFuncionarioMaisVelho(funcionarios);
                    	 break;
                    case OP_FUNCIONARIO_MAIS_NOVO: 
                    	imprimirFuncionarioMaisNovo(funcionarios);
                    	 break;
                    case OP_ORDENAR_ALFABETICAMENTE:
                    	ordernarListaPorOrdermAlfabetica(funcionarios);
                    	 break;
                    case OP_TOTAL_SALARIOS:
                    	calcularTotalSalario(funcionarios);
                    	 break;
                    case OP_SALARIO_MINIMO_POR_FUNCIONARIO: 
                    	imprimirSalarioMinimoPorFuncionario(funcionarios);
                    	 break;
                    default:
                    	System.out.println("Opção inválida!");
                    	 break;
                }
            }
        }
    }   
    
    public static void Executar () {
        List<Funcionario> funcionarios = criarListaFuncionarios();
        imprimirMenuEOpcoes(funcionarios);
        }   
 }

