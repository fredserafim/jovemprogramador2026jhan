import java.sql.*;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.SplittableRandom;

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

            int usuarioId = -1;

            //===================================== LOGIN ===============================
            Thread.sleep(1100);
            System.out.println("""
                  *****LOGIN*****
                  1 - Cadastrar novo usuario
                  2 - Entrar
                  """);

            int opLogin = sc.nextInt();
            sc.nextLine();

            if (opLogin == 1) {
                System.out.println("Nome:");
                String nome = sc.nextLine();

                System.out.println("Senha:");
                String senha = sc.nextLine();

                String mySQL ="INSERT INTO usuario (nome, senha) VALUES (?, ?)";
                PreparedStatement ps = conexao.prepareStatement(mySQL);
                ps.setString(1, nome);
                ps.setString(2, senha);
                ps.executeUpdate();

                System.out.println("Usuário cadastrado com sucesso!");

            }

            if (opLogin == 2) {
                System.out.println("Nome:");
                String nome = sc.nextLine();

                System.out.println("Senha:");
                String senha = sc.nextLine();

                String mySQL = "SELECT id FROM usuario WHERE nome = ? AND senha = ?";
                PreparedStatement ps = conexao.prepareStatement(mySQL);
                ps.setString(1, nome);
                ps.setString(2, senha);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    usuarioId = rs.getInt("id");
                    System.out.println("Login realizado!");
                } else {
                    System.out.println("Usuário Inválido!");
                    return;
                }
            }


            //===================================== RECEITA ===============================
            do {
                Thread.sleep(1000);
                System.out.printf("""
                      ====Caderno Receitas da Famìlia Casagrande====
                      1 - Adicionar  Receita
                      2 - Pesquisar Receitas
                      3 - Atualizar Receita
                      4 - Deletar pois não gostei
                      0 - Sair
                      
                      
                      
                      Receitas existentes : %S
                      """,receitasN);
                Thread.sleep(1000);
                System.out.println(" Digite a opção :");
                opcao = sc.nextInt();
                sc.nextLine();
                Thread.sleep(1000);

                switch (opcao){
                    case 1 :
                        Thread.sleep(1000);
                        System.out.println("Adicionar Receita");
                        Thread.sleep(1000);
                        System.out.println("Digite o titulo: ");
                        String titulo = sc.nextLine();
                        Thread.sleep(1000);
                        System.out.println( "Digite o id do ingrediente: ");
                        int ingredientes_id = sc.nextInt();
                        Thread.sleep(1000);
                        System.out.println("Digite a quantidade: ");
                        double quantidade = sc.nextDouble();
                        Thread.sleep(1000);
                        System.out.println("Digite o tempo: ");
                        int tempo = sc.nextInt();
                        sc.nextLine();
                        Thread.sleep(1000);
                        System.out.println("Digite qual a dificuldade( 1 facil, 2 medio, 3 dificil): ");
                        int complexidade_id = sc.nextInt();
                        Thread.sleep(1000);
                        System.out.println("Digite quantas pessoas serve: ");
                        int porcoes = sc.nextInt();
                        Thread.sleep(1000);
                        System.out.println("Qual a categoria( 1 doce, 2 salgado, 3 agridoce: ");
                        int categoria_id = sc.nextInt();
                        Thread.sleep(1000);
                        System.out.println("Quais utensílios necessários: ");
                        int ferramentas_id = sc.nextInt();
                        Thread.sleep(1000);
                        System.out.println("Modo de preparo: ");
                        int preparo_id = sc.nextInt();
                        Thread.sleep(1000);
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
                        psInsert.setInt(10,usuarioId);
                        var dataAtual = LocalDateTime.now();
                        psInsert.setDate(11,
                                new Date(dataAtual.getYear(),dataAtual.getMonth().getValue(),dataAtual.getDayOfMonth()));// pegar prof
                        psInsert.executeUpdate();
                        Thread.sleep(1000);
                        System.out.println("Receita salva com sucesso!");
                        Thread.sleep(1000);

                        receitasN++;

                        break;
                    case 2 :
                        System.out.println("Receitas");
                        Thread.sleep(1000);
                        System.out.println(" o que gostaria de cozinhar: ");
                        String tituloid = sc.nextLine();
                        String select = """
                                select id, titulo
                                from receita
                                where titulo like ?
                                order by titulo desc;""";
                        PreparedStatement psSelect = conexao.prepareStatement(select);
                        psSelect.setString(1,"%" +  tituloid + "%" );
                        ResultSet rsReceitas = psSelect.executeQuery();
                        Thread.sleep(1000);
                        System.out.println("Listas de Receitas");
                        while(rsReceitas.next()) {
                            System.out.printf("%d - %s \n",
                                    rsReceitas.getInt("id"),
                                    rsReceitas.getString("titulo")
                            );
                            System.out.println("Qual receita deseja( digite o ID): ");
                            int tituloid1 = sc.nextInt();
                            sc.nextLine();
                            String  select1 = """
                                    select re.id, re.titulo, com.dificuldade, cat.nome, fer.utensilios, pre.modo_preparo, igrs.ingrediente, igr.quantidade, igrs.unidade_medida  from receita re
                                    join complexidade com on re.complexidade_id = com.id
                                    join categoria cat on re.categoria_id = cat.id
                                    join ferramentas fer on re.ferramentas_id = fer.id
                                    join preparo pre on re.preparo_id = pre.id
                                    join usuario usu on re.usuario_id = usu.id
                                    join re on igr.receita_id = re.id
                                    join ingrediente_receita igr on igr.receita_id = re.id
                                    join ingredientes igrs on igr.ingredientes_id = igrs.id
                                    where re.id = ?
                                    order by re.titulo desc;
                                    """;

                            PreparedStatement psSelect1 = conexao.prepareStatement(select1);
                            psSelect1.setInt(1, tituloid1);
                            ResultSet rsDetalhe = psSelect1.executeQuery();
                            Thread.sleep(1000);
                            System.out.println("Lista");
                        }
                            while (rsDetalhe.next()) {
                                System.out.printf("%s - %s - %s - %s %s - %s - %d\n",
                                        rsDetalhe.getString("Titulo"),
                                        rsDetalhe.getString("Dificuldade"),
                                        rsDetalhe.getString("nome"),
                                        rsDetalhe.getString("utensilios"),
                                        rsDetalhe.getString("modo_preparo"),
                                        rsDetalhe.getString("ingrediente"),
                                        rsDetalhe.getInt("quantidade"),
                                        rsDetalhe.getString("unidade_medida")


                                );

                            }
                            Thread.sleep(1100);
                            break;
                    case 3:
                        System.out.println("update");
                        Thread.sleep(1100);
                        System.out.println("Digite o ID para atualizar: ");
                        int idUpdate = sc.nextInt();
                        sc.nextLine();
                        Thread.sleep(1100);
                        System.out.println("Novo título: ");
                        String tituloUpdate = sc.nextLine();
                        Thread.sleep(1100);
                        System.out.println("Novo tempo de preparo (minutos): ");
                        int tempoUpdate = sc.nextInt();
                        sc.nextLine();
                        Thread.sleep(1100);

                        String updade = "UPDATE receita SET titulo = ?, tempo = ? WHERE id = ?";

                        PreparedStatement psUpdate = conexao.prepareStatement(updade);

                        psUpdate.setString(1, tituloUpdate);
                        psUpdate.setInt(2, tempoUpdate);
                        psUpdate.setInt(3, idUpdate);

                        psUpdate.executeUpdate();
                        Thread.sleep(1100);
                        System.out.println("Receita atualizada!");
                        Thread.sleep(1100);
                        break;
                            case 4:
                                System.out.println("delete");
                                Thread.sleep(1100);
                                System.out.println("Digite o ID que gostaria de deletar: ");
                                int idDelete = sc.nextInt();
                                sc.nextLine();
                                Thread.sleep(1100);

                                String delete = " delete from *** where id =?";
                                PreparedStatement psDelete = conexao.prepareStatement(delete);
                                psDelete.setInt(1, idDelete);
                                psDelete.executeUpdate();
                                Thread.sleep(1100);
                                System.out.println("Usuario Deletado");
                                Thread.sleep(1100);

                                receitasN = -1;

                                break;
                            case 0:
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
