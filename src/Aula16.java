import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Caneta caneta = new Caneta("vermelho", 3);
        Caneta caneta2 = new Caneta("azul", 5);
        Caneta caneta3 = new Caneta("verde", 1);
        System.out.println(caneta);
        System.out.println(caneta3);


        String url = "jdbc:mysql://localhost:3306/javabanco";
        String user = "root";
        String password = "";
        int opcao;

        try{
            Connection conexao = DriverManager.getConnection(url, user, password);
            System.out.println("Conectado!");

            do{
                System.out.println("""
                        ===MENU===
                        1 - Inserir
                        2 - Listar
                        3 - Atualizar
                        4 - Deletar
                        5 - Buscar por email
                        0 - SAIR
                        """);
                System.out.print("Digite a opção:");
                opcao = sc.nextInt();
                sc.nextLine();

                switch (opcao){
                    case 1:
                        System.out.println("CREATE");
                        System.out.print("Digite o nome:");
                        String nome = sc.nextLine();
                        System.out.print("Digite o email:");
                        String email = sc.nextLine();

                        String insert = "INSERT INTO usuario (nome, email) VALUES (?,?)";
                        PreparedStatement psInsert = conexao.prepareStatement(insert);
                        psInsert.setString(1,nome);
                        psInsert.setString(2, email);
                        psInsert.executeUpdate();
                        System.out.println("Usuário Inserido");
                        break;
                    case 2:
                        System.out.println("READ");
                        String select = "SELECT * FROM usuario";
                        Statement stmt = conexao.createStatement();
                        ResultSet rs = stmt.executeQuery(select);
                        System.out.println("LISTA");
                        while(rs.next()){
                            System.out.printf("%d - %s - %s\n",
                                    rs.getInt("id"),
                                    rs.getString("nome"),
                                    rs.getString("email")
                            );
                        }
                        break;
                    case 3:
                        System.out.println("UPDATE");
                        System.out.print("Digite o ID para atualizar: ");
                        int idUpdate = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Novo nome: ");
                        String nomeUpdate = sc.nextLine();
                        System.out.print("Novo email: ");
                        String emailUpdate = sc.nextLine();

                        String update = "UPDATE usuario SET nome = ?, email = ? WHERE id = ?";
                        PreparedStatement psUpdate = conexao.prepareStatement(update);
                        psUpdate.setString(1,nomeUpdate);
                        psUpdate.setString(2,emailUpdate);
                        psUpdate.setInt(3,idUpdate);
                        psUpdate.executeUpdate();
                        System.out.println("Usuario Atualizado");

                        break;
                    case 4:
                        System.out.println("DELETE");
                        System.out.print("Digite o ID para deletar: ");
                        int idDelete = sc.nextInt();

                        String delete = "DELETE FROM usuario WHERE id =?";
                        PreparedStatement psDelete = conexao.prepareStatement(delete);
                        psDelete.setInt(1,idDelete);
                        psDelete.executeUpdate();
                        System.out.println("Ususario Deletado");
                        break;
                    case 5:
                        System.out.println("READ");
                        System.out.println("Digite o email:");
                        String buscaEmail = sc.nextLine();
                        System.out.println("Digite o nome:");
                        String buscaNome = sc.nextLine();
                        String selectNome = "SELECT nome, email FROM usuario where email = '"+ buscaEmail +"'";
                        System.out.println(selectNome);
                        Statement stmt2 = conexao.createStatement();
                        ResultSet rsNome = stmt2.executeQuery(selectNome);


                        while(rsNome.next()){
                            String emailCad = rsNome.getString("email");
                            String nomeCad = rsNome.getString("nome");
                            if(buscaNome.equals(nomeCad) && buscaEmail.equals(emailCad)){
                                System.out.println("Logado");
                            }else{
                                System.out.println("Nao logado");
                            }

                        }

                        break;
                    case 0:
                        System.out.println("Programa Finalizado");
                        break;
                    default:
                        System.out.println("Opção Inválida");

                }

            }while(opcao !=0);
            conexao.close();

        }catch(Exception e){
            e.printStackTrace();
        }



        sc.close();
    }
}