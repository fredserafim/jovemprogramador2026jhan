import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Exercicio15 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        int  opcao;
        List<String> tarefa = new ArrayList<>();

        do{

            System.out.println("""
                    1       Adicione Tarefa
                    2       Remover Tarefa
                    3       Listetarefa
                    4       sair
                    """);
            opcao = sc.nextInt();
            sc.nextLine();


            switch (opcao){
                case 1:
                    System.out.println("Adicione uma tarefa: ");
                    tarefa.add(sc.nextLine());

                    break;
                case 2:
                    for (int i = 0; i < tarefa.size(); i++) {
                         System.out.println(i + "-" +tarefa.get(i));

                    }

                    //tarefa.forEach((tarefas) -> System.out.println(tarefas));
                    System.out.print("Remova opção de tarefa desejada: ");
                    tarefa.remove(sc.nextInt());

                    break;
                case 3:
                    System.out.println("Suas tarefas são :");
                    tarefa.forEach((tarefas) -> System.out.println(tarefas));


                    break;
                case 4:
                    System.out.println(" tchau!!");

                    break;

            }
        }while(opcao != 4);




            sc.close();
    }

}