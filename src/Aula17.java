import java.sql.*;
import java.util.Scanner;

public class Aula17 {
    public  static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String url = "jdbc:mysql://127.0.0.1:3306/caderno_receita";
        String user = "root";
        String password = "";
        int opcao, usuario, receitasN = 0;

        try{
            Connection conexao = DriverManager.getConnection(url, user, password);
            System.out.println("Conectado!");

            Thread.sleep(1100);
            System.out.println("""
                  *****Login*****
                  1 - Cadastrar novo usuario
                  2 - Entrar
                  
                  """);
            usuario = sc.nextInt();
            sc.nextLine();

            switch (usuario){
                case 1 :
                    Thread.sleep(1100);
                    System.out.println("Digite seu nome: ");
                    String nome = sc.nextLine();
                    Thread.sleep(1100);
                    System.out.println("Insira sua senha: ");
                    int senha = sc.nextInt();
                    Thread.sleep(1100);

                    String insert = "insert into usuario ( nome, senha) values(?,?) ";
                    PreparedStatement psInsert = conexao.prepareStatement(insert);
                    psInsert.setString(1,nome);
                    psInsert.setInt(2,senha);
                    psInsert.executeUpdate();
                    System.out.println("Usuario inserido");
                    Thread.sleep(1100);
                    break;

                case 2 :
                    Thread.sleep(1100);
                    String nomeLogin = sc.nextLine();
                    int senhaLogin = sc.nextInt();
                    break;
                    // verificar usuario







            }

            do {
                Thread.sleep(1100);
                System.out.printf("""
                      ====Caderno Receitas da Famìlia Casagrande====
                      1 - Adicionar  Receita
                      2 - Pesquisar Receitas
                      3 - Atualizar Receita
                      4 - Deletar pois não gostei
                      0 - Sair
                      
                      
                      
                      Receitas existentes : %S
                      """,receitasN);
                Thread.sleep(1100);
                System.out.println(" Digite a opção :");
                opcao = sc.nextInt();
                sc.nextLine();
                Thread.sleep(1100);

                switch (opcao){
                    case 1 :
                        Thread.sleep(1100);
                        System.out.println("create");
                        Thread.sleep(1100);
                        System.out.println("Digite o titulo: ");
                        String titulo = sc.nextLine();
                        Thread.sleep(1100);
                        System.out.println( "Digite o id do ingrediente: ");
                        int ingredientes_id = sc.nextInt();
                        Thread.sleep(1100);
                        System.out.println(" Digite a quantidade: ");
                        double quantidade = sc.nextDouble();
                        Thread.sleep(1100);
                        System.out.println("Digite o tempo: ");
                        int tempo = sc.nextInt();
                        sc.nextLine();
                        Thread.sleep(1100);
                        System.out.println("Digite qual a dificuldade( 1 facil, 2 medio, 3 dificil): ");
                        int complexidade_id = sc.nextInt();
                        Thread.sleep(1100);
                        System.out.println("Digite quantas pessoas serve: ");
                        int porcoes = sc.nextInt();
                        Thread.sleep(1100);
                        System.out.println("Qual a categoria: ");
                        int categoria_id = sc.nextInt();
                        Thread.sleep(1100);
                        System.out.println(" Quais utensílios necessários");
                        int ferramentas_id = sc.nextInt();
                        Thread.sleep(1100);
                        System.out.println("Modo de preparo: ");
                        int preparo_id = sc.nextInt();
                        Thread.sleep(1100);
                        // data criação e o suario devem ser pegos sozinhos





                        String insert = "insert into  ( titulo, ingredientes_id, quantidade, tempo, complexidade_id,porcoes, categoria_id," +
                                "ferramentas_id, preparo_id, usuario_id, data_criacao) values (?,?,?,?,?,?,?,?,?,?,?)";
                        PreparedStatement psInsert = conexao.prepareStatement(insert);
                        psInsert.setString(1,titulo);
                        psInsert.setInt(2,ingredientes_id);
                        psInsert.setDouble(3,quantidade);
                        psInsert.setInt(4,tempo);
                        psInsert.setInt(5,complexidade_id);
                        psInsert.setInt(6,porcoes);
                        psInsert.setInt(7,categoria_id);
                        psInsert.setInt(8,ferramentas_id);
                        psInsert.setInt(9,preparo_id);
                        //psInsert.setInt(10,usuario_id);
                        //psInsert.setInt(11,data_criacao);// pegar prof
                        psInsert.executeUpdate();
                        Thread.sleep(1100);
                        System.out.println("Receita salva com sucesso!");
                        Thread.sleep(1100);

                        receitasN++;

                        break;
                    case 2 :
                        System.out.println("read");
                        Thread.sleep(1100);
                        System.out.println("Qual receita deseja: ");
                        String tituloID = sc.nextLine();
                        String select = """
                                select re.titulo, com.dificuldade, cat.nome, fer.utensilios, pre.modo_preparo from receita re
                                join complexidade com on re.complexidade_id = com.id
                                join categoria cat on re.categoria_id = cat.id
                                join ferramentas fer on re.ferramentas_id = fer.id
                                join preparo pre on re.preparo_id = pre.id
                                join usuario usu on re.usuario_id = usu.id
                                where re.titulo like "%morango%"
                                order by re.titulo desc;
                                """, + tituloID;

                        Statement stmt = conexao.createStatement();
                        ResultSet rs = stmt.executeQuery(select);
                        Thread.sleep(1100);
                        System.out.println("Lista");
                        while(rs.next()){
                            System.out.printf("%d - %s - %s\n",
                                    rs.getInt("id"),
                                    rs.getString("nome"),
                                    rs.getString("email")
                            );

                        }
                        Thread.sleep(1100);
                        break;
                    case 3 :
                        System.out.println("update");
                        Thread.sleep(1100);
                        System.out.println("Digite o ID para atualizar: ");
                        int idUpdate = sc.nextInt();
                        sc.nextLine();
                        Thread.sleep(1100);
                        System.out.println("Novo nome: ");
                        String nomeUpdate = sc.nextLine();
                        Thread.sleep(1100);
                        System.out.println("Novo email: ");
                        String emailUpdate =  sc.nextLine();
                        Thread.sleep(1100);

                        String updade = "update +++ set +++ = ?, *** = ? where*** = ?";
                        PreparedStatement psUpdate = conexao.prepareStatement(updade);
                        psUpdate.setString(1,nomeUpdate);
                        psUpdate.setString(2,emailUpdate);
                        psUpdate.setInt( 3,idUpdate);
                        psUpdate.executeUpdate();
                        Thread.sleep(1100);
                        System.out.println("Receita atualizada!");
                        Thread.sleep(1100);
                        break;
                    case 4 :
                        System.out.println("delete");
                        Thread.sleep(1100);
                        System.out.println("Digite o ID que gostaria de deletar: ");
                        int idDelete = sc.nextInt();
                        sc.nextLine();
                        Thread.sleep(1100);

                        String delete = " delete from *** where id =?";
                        PreparedStatement psDelete = conexao.prepareStatement(delete);
                        psDelete.setInt(1,idDelete);
                        psDelete.executeUpdate();
                        Thread.sleep(1100);
                        System.out.println("Usuario Deletado");
                        Thread.sleep(1100);

                        receitasN = -1;

                        break;
                    case 0 :
                        Thread.sleep(1100);
                        System.out.println("Programa Finalizado");
                        Thread.sleep(1100);
                        break;
                    default:
                        Thread.sleep(1100);
                        System.out.println("Opção invalida!");
                        Thread.sleep(1100);

                }
            }while(opcao!=0);
            conexao.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


        sc.close();




    }
}
