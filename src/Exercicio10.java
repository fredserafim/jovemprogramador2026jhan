import java.util.Scanner;

public class Exercicio10 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int opcao;
        double  total=0.0;
        String repetir = "sim";

        while(repetir.equals("sim")) {

            System.out.println("""
                    CODIGO          Produto          PREÇO UNITÁRIO     
                    1              Hambúrguer          R$ 18,00         
                    2            Cheeseburguer         R$ 20,00         
                    3             Batata Frita         R$ 12,00         
                    4             Refrigerante         R$ 8,00          
                    5             Suco Natural         R$ 10,00         
                    6              Milkshake           R$ 15,00         
                    7               Salada             R$ 16,00         
                    """);
            opcao = sc.nextInt();

            switch (opcao){
                case 1:
                    total+=18.0;
                    System.out.println(" hamburguer adicionado");
                    break;
                case 2:
                    total+=20.0;
                    System.out.println("chesse adicionado");
                    break;
                case 3:
                    total+=12.0;
                    System.out.println("batata adicionada");
                    break;
                case 4:
                    total+=8.0;
                    System.out.println("refri adicionado");
                    break;
                default:
                    System.out.println("opção invalida");
            }
            System.out.println(" total pedido : R$ "+ total);
            sc.nextLine();
            System.out.println(" deseja acrescentar mais algum item: ");
            repetir = sc.nextLine();
        }

        System.out.println(" total pedido : R$ "+ total);



        sc.close();
    }

}
