import java.util.Scanner;

public class Prova {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String produto = "";
        String descontoAplicado;
        String categNome = "";
        double valorProduto = 0.0;
        double desconto,valorTotal;
        double valorDesconto = 0.0;
        int opcaoPedido, quantidade, categoria, idade;


        do {
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
            opcaoPedido = sc.nextInt();

        }while ( opcaoPedido <= 0 || opcaoPedido >7);





        do {
            System.out.println("Digite a quatidade desejada: ");
            quantidade = sc.nextInt();
        }while( quantidade < 0);

        do {
            System.out.println("""
                    *****Digite a Categoria*****
                    
                    
                    1       Estudante
                    2        Idoso   
                    3     Cliente Comum
                    
                    
                    """);
            categoria = sc.nextInt();
        }while( categoria < 0 || categoria > 3);

        System.out.println("Digite sua Idade: ");
        idade= sc.nextInt();

        switch (opcaoPedido) {
            case 1:
                if(categoria == 1 && idade<60){
                    categNome = "Estudante";
                    produto = "Hambúrguer";
                    valorProduto = 18.0;
                    desconto = valorProduto * (10.0/100);
                    valorDesconto = valorProduto - desconto;
                }
                else if((categoria == 2 &&  idade >=60) || (categoria == 1 &&  idade >=60 ) || (categoria == 3 && idade >= 60)){
                    categNome = "Idoso";
                    produto = "Hambúrguer";
                    valorProduto = 18.0;
                    desconto = valorProduto * (20.0/100);
                    valorDesconto = valorProduto - desconto;
                }else{
                    categNome = "Cliente Comum";
                    produto = "Hambúrguer";
                    valorProduto = 18.0;
                    desconto = 0.0;
                    valorDesconto = 0.0;
                }
                break;

            case 2:
                if(categoria == 1 && idade<60){
                    categNome = "Estudante";
                    produto = "Cheeseburguer";
                    valorProduto = 20.0;
                    desconto = valorProduto * (10.0/100);
                    valorDesconto = valorProduto - desconto;
                }
                else if((categoria == 2 &&  idade >=60) || (categoria == 1 &&  idade >=60 ) || (categoria == 3 && idade >= 60)){
                    categNome = "Idoso";
                    produto = "Cheeseburguer";
                    valorProduto = 20.0;
                    desconto = valorProduto * (20.0/100);
                    valorDesconto = valorProduto - desconto;
                }else{
                    categNome = "Cliente Comum";
                    produto = "Cheeseburguer";
                    valorProduto = 20.0;
                    desconto = 0.0;
                    valorDesconto = 0.0;
                }
                break;

            case 3:
                if(categoria == 1 && idade<60){
                    categNome = "Estudante";
                    produto = "Batata Frita";
                    valorProduto = 12.0;
                    desconto = valorProduto * (10.0/100);
                    valorDesconto = valorProduto - desconto;
                }
                else if((categoria == 2 &&  idade >=60) || (categoria == 1 &&  idade >=60 ) || (categoria == 3 && idade >= 60)){
                    categNome = "Idoso";
                    produto = "Batata Frita";
                    valorProduto = 12.0;
                    desconto = valorProduto * (20.0/100);
                    valorDesconto = valorProduto - desconto;
                }else{
                    categNome = "Cliente Comum";
                    produto = "Batata Frita";
                    valorProduto = 12.0;
                    desconto = 0.0;
                    valorDesconto = 0.0;
                }
                break;

            case 4:
                if(categoria == 1 && idade<60){
                    categNome = "Estudante";
                    produto = "Refrigerante";
                    valorProduto = 8.0;
                    desconto = valorProduto * (10.0/100);
                    valorDesconto = valorProduto - desconto;
                }
                else if((categoria == 2 &&  idade >=60) || (categoria == 1 &&  idade >=60 ) || (categoria == 3 && idade >= 60)){
                    categNome = "Idoso";
                    produto = "Refrigerante";
                    valorProduto = 8.0;
                    desconto = valorProduto * (20.0/100);
                    valorDesconto = valorProduto - desconto;
                }else{
                    categNome = "Cliente Comum";
                    produto = "Refrigerante";
                    valorProduto = 8.0;
                    desconto = 0.0;
                    valorDesconto = 0.0;
                }
                break;

            case 5:
                if(categoria == 1 && idade<60){
                    categNome = "Estudante";
                    produto = "Suco Natural";
                    valorProduto = 10.0;
                    desconto = valorProduto * (10.0/100);
                    valorDesconto = valorProduto - desconto;
                }
                else if((categoria == 2 &&  idade >=60) || (categoria == 1 &&  idade >=60 ) || (categoria == 3 && idade >= 60)){
                    categNome = "Idoso";
                    produto = "Suco Natural";
                    valorProduto = 10.0;
                    desconto = valorProduto * (20.0/100);
                    valorDesconto = valorProduto - desconto;
                }else{
                    categNome = "Cliente Comum";
                    produto = "Suco Natural";
                    valorProduto = 10.0;
                    desconto = 0.0;
                    valorDesconto = 0.0;
                }
                break;

            case 6:
                if(categoria == 1 && idade<60){
                    categNome = "Estudante";
                    produto = "Milkshake";
                    valorProduto = 15.0;
                    desconto = valorProduto * (10.0/100);
                    valorDesconto = valorProduto - desconto;
                }
                else if((categoria == 2 &&  idade >=60) || (categoria == 1 &&  idade >=60 ) || (categoria == 3 && idade >= 60)){
                    categNome = "Idoso";
                    produto = "Milkshake";
                    valorProduto = 15.0;
                    desconto = valorProduto * (20.0/100);
                    valorDesconto = valorProduto - desconto;
                }else{
                    categNome = "Cliente Comum";
                    produto = "Milkshake";
                    valorProduto = 15.0;
                    desconto = 0.0;
                    valorDesconto = 0.0;
                }
                break;

            case 7:
                if(categoria == 1 && idade<60){
                    categNome = "Estudante";
                    produto = "Salada";
                    valorProduto = 16.0;
                    desconto = valorProduto * (10.0/100);
                    valorDesconto = valorProduto - desconto;
                }
                else if((categoria == 2 &&  idade >=60) || (categoria == 1 &&  idade >=60 ) || (categoria == 3 && idade >= 60)){
                    categNome = "Idoso";
                    produto = "Salada";
                    valorProduto = 16.0;
                    desconto = valorProduto * (20.0/100);
                    valorDesconto = valorProduto - desconto;
                }else{
                    categNome = "Cliente Comum";
                    produto = "Salada";
                    valorProduto = 16.0;
                    desconto = 0.0;
                    valorDesconto = 0.0;
                }
                break;

            default:
                System.out.println("Opção Invalida");
                break;
        }


        if(valorDesconto > 0.0){
            valorTotal = valorDesconto * quantidade;
        }else{
            valorTotal = valorProduto * quantidade;
        }



        if(categNome.equals("Estudante")){
            descontoAplicado = "10%";
        }
        else if(categNome.equals("Idoso")){
            descontoAplicado = "20%";
        }else{
            descontoAplicado = "Não Aprovado";
        }

        //nao apliquei a % de outra forma pois nao lembrei como se aplica e tentei fazer o menos possivel sem consulta
        // apesar de ter pedido ajuda , pois nao havia encontrado o porque do erro

        System.out.printf("""
                 == Comprovante de Compra ====================

                 Produto: %s             Quantidade: %d

                 Categoria: %s

                 Valor do Item: %.2f

                 Desconto Aplicado: %s

                 Valor Total: %.2f

                 =============================================
                 """, produto, quantidade, categNome, valorProduto, descontoAplicado, valorTotal);















        sc.close();
    }

}
