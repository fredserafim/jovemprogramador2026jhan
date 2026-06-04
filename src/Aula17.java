import java.sql.*;
import java.util.Scanner;

public class Aula17 {
    public  static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String url = "jdbc:mysql://localhost:3306/caderno_receita";
        String user = "root";
        String password = "";
        int opcao, usuario;

        try{
            Connection conexao = DriverManager.getConnection(url, user, password);
            System.out.println("Conectado!");

            System.out.println("""
                  *****Login*****
                  1 - Cadastrar novo usuario
                  2 - Entrar
                  
                  """);
            usuario = sc.nextInt();
            sc.nextLine();

            switch (usuario){
                case 1 :
                    System.out.println("Digite seu nome: ");
                    String nome = sc.nextLine();
                    System.out.println("Insira sua senha: ");
                    int senha = sc.nextInt();

                    String insert = "insert into usuario ( nome, senha) values(?,?)";
                    PreparedStatement psInsert = conexao.prepareStatement(insert);
                    psInsert.setString(1,nome);
                    psInsert.setInt(2,senha);
                    psInsert.executeUpdate();
                    System.out.println("Usuario inserido");
                    break;

                case 2 :
                    String nomeLogin = sc.nextLine();
                    int senhaLogin = sc.nextInt();
                    break;
                    // verificar usuario







            }

            do {
                System.out.println("""
                      ====Caderno Receitas da Famìlia Casagrande====
                      1 - Adicionar  Receita
                      2 - Pesquisar Receitas
                      3 - Atualizar Receita
                      4 - Deletar pois não gostei
                      0 - Sair
                      """);
                System.out.println(" Digite a opção :");
                opcao = sc.nextInt();
                sc.nextLine();

                switch (opcao){
                    case 1 :
                        System.out.println("create");
                        System.out.println("Digite o titulo: ");
                        String titulo = sc.nextLine();
                        System.out.println( "Digite o id do ingrediente: ");
                        int ingredientes_id = sc.nextInt();
                        System.out.println(" Digite a quantidade: ");
                        double quantidade = sc.nextDouble();
                        System.out.println("Digite o tempo: ");
                        int tempo = sc.nextInt();
                        sc.nextLine();
                        System.out.println("Digite qual a dificuldade(facil, medio, dificil): ");
                        String dificuldade = sc.nextLine();
                        System.out.println("Digite quantas pessoas serve: ");
                        int porcoes = sc.nextInt();
                        System.out.println("Qual a categoria: ");
                        int categoria_id = sc.nextInt();
                        System.out.println("");




                        String insert = "insert into  ( titulo, ingredientes_id, quantidade, tempo, dificuldade,porcoes, categoria_id," +
                                "ferramentas_id, preparo_id, usuario_id, data_criacao) values (?,?)";
                        PreparedStatement psInsert = conexao.prepareStatement(insert);
                        psInsert.setString(1,titulo);
                        psInsert.setInt(2,ingredientes_id);
                        psInsert.setDouble(3,quantidade);
                        psInsert.setInt(4,tempo);
                        psInsert.setString(5,dificuldade);
                        psInsert.setString(6,porcoes);
                        psInsert.setInt(7,categoria_id);
                        psInsert.setInt(8,ferramentas_id);





                        psInsert.executeUpdate();
                        System.out.println("Usuario inserido");

                        break;
                    case 2 :
                        System.out.println("read");
                        String select = " select * from usuario";
                        Statement stmt = conexao.createStatement();
                        ResultSet rs = stmt.executeQuery(select);
                        System.out.println("Lista");
                        while(rs.next()){
                            System.out.printf("%d - %s - %s\n",
                                    rs.getInt("id"),
                                    rs.getString("nome"),
                                    rs.getString("email")
                            );

                        }
                        break;
                    case 3 :
                        System.out.println("update");
                        System.out.println("Digite o ID para atualizar: ");
                        int idUpdate = sc.nextInt();
                        sc.nextLine();
                        System.out.println("Novo nome: ");
                        String nomeUpdate = sc.nextLine();
                        System.out.println("Novo email: ");
                        String emailUpdate =  sc.nextLine();

                        String updade = "update usuario set nome = ?, email = ? where id = ?";
                        PreparedStatement psUpdate = conexao.prepareStatement(updade);
                        psUpdate.setString(1,nomeUpdate);
                        psUpdate.setString(2,emailUpdate);
                        psUpdate.setInt( 3,idUpdate);
                        psUpdate.executeUpdate();
                        System.out.println("Usuario atualizado!");
                        break;
                    case 4 :
                        System.out.println("delete");
                        System.out.println("Digite o ID que gostaria de atualizar: ");
                        int idDelete = sc.nextInt();
                        sc.nextLine();

                        String delete = " delete from usuario where id =?";
                        PreparedStatement psDelete = conexao.prepareStatement(delete);
                        psDelete.setInt(1,idDelete);
                        psDelete.executeUpdate();
                        System.out.println("Usuario Deletado");

                        break;
                    case 0 :
                        System.out.println("Programa Finalizado");
                        break;
                    default:
                        System.out.println("Opção invalida!");

                }
            }while(opcao!=0);
            conexao.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


        sc.close();




    }
}
