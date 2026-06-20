import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

public class Aula17 {
    public  static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String url = "jdbc:mysql://127.0.0.1:3306/caderno_receita";
        String user = "root";
        String password = "";
        int opcao;



        try{
            Connection conexao = DriverManager.getConnection(url, user, password);
            System.out.println("Conectado!");


            int usuarioId = -1;

            //===================================== LOGIN ===============================
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
                String sql = "SELECT COUNT(*) AS total FROM receita";

                PreparedStatement ps = conexao.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();

                int totalReceitas = 0;

                if (rs.next()) {
                    totalReceitas = rs.getInt("total");
                }
                System.out.printf("""
                      ====Caderno Receitas da vó Nadir====
                      1 - Adicionar  Receita
                      2 - Pesquisar Receitas
                      3 - Atualizar Receita
                      4 - Deletar pois não gostei
                      0 - Sair
                      
                      
                      
                      Receitas existentes : %d
                      """,totalReceitas);
                System.out.println(" Digite a opção :");
                opcao = sc.nextInt();
                sc.nextLine();

                switch (opcao){
                    case 1 :
                        System.out.println("Adicionar Receita");
                        System.out.println("Digite o titulo: ");
                        String titulo = sc.nextLine();
                        System.out.println( "Digite o id do ingrediente: ");
                        int ingredientes_id = sc.nextInt();
                        System.out.println("Digite a quantidade: ");
                        double quantidade = sc.nextDouble();
                        System.out.println("Digite o tempo: ");
                        int tempo = sc.nextInt();
                        sc.nextLine();
                        System.out.println(""" 
                                Digite qual a dificuldade:
                                1 - Facil
                                2 - Media
                                3 - Dificil""");
                        int complexidade_id = sc.nextInt();
                        System.out.println("Digite quantas pessoas serve: ");
                        int porcoes = sc.nextInt();
                        System.out.println("""
                                 Qual a categoria:
                                 1 - Doce
                                 2 - Salgado
                                 3 - Agridoce""");
                        int categoria_id = sc.nextInt();
                        System.out.println("Quais utensílios necessários: ");
                        int ferramentas_id = sc.nextInt();
                        System.out.println("Modo de preparo: ");
                        int preparo_id = sc.nextInt();







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
                        psInsert.setDate(11, Date.valueOf(LocalDate.now()));
                        psInsert.executeUpdate();
                        System.out.println("Receita salva com sucesso! ");



                        break;
                    case 2 :
                        System.out.println("Receitas");
                        System.out.println("O que gostaria de cozinhar: ");
                        String tituloid = sc.nextLine();
                        String select = """
                                select id, titulo
                                from receita
                                where titulo like ?
                                order by id asc;""";
                        PreparedStatement psSelect = conexao.prepareStatement(select);
                        psSelect.setString(1,"%" +  tituloid + "%" );
                        ResultSet rsReceitas = psSelect.executeQuery();
                        System.out.println("Listas de Receitas");
                        while(rsReceitas.next()) {
                            System.out.printf("%d - %s \n",
                                    rsReceitas.getInt("id"),
                                    rsReceitas.getString("titulo")
                            );}
                            System.out.println("Qual receita deseja( digite o ID): ");
                            int tituloid1 = sc.nextInt();
                            sc.nextLine();
                            String  select1 = """
                                    select re.id, re.titulo, com.dificuldade , cat.classificacao ,fer.utensilios,
                                    pre.modo_preparo, igrs.ingrediente, igr.quantidade, igrs.unidade_medida,
                                    usu.nome , re.data_criacao ,re.tempo, re.porcoes, com.dificuldade, re.tempo
                                    from receita re
                                    join complexidade com on re.complexidade_id = com.id
                                    join categoria cat on re.categoria_id = cat.id
                                    join ferramentas fer on re.ferramentas_id = fer.id
                                    join preparo pre on re.preparo_id = pre.id
                                    join usuario usu on re.usuario_id = usu.id
                                    join ingrediente_receita igr on igr.receita_id = re.id
                                    join ingredientes igrs on igr.ingredientes_id = igrs.id
                                    where re.id = ?
                                    order by re.titulo desc;
                                    """;


                            PreparedStatement psSelect2 = conexao.prepareStatement(select1);
                            psSelect2.setInt(1, tituloid1);
                            ResultSet rsDetalhe = psSelect2.executeQuery();
                            System.out.println("Lista");


                        String ultima = "";
                        String modoPreparo = "";
                        String usuario = "";
                        Date dataCriacao = null;


                        while (rsDetalhe.next()) {


                            String receita = rsDetalhe.getString("titulo");

                            if (!receita.equals(ultima)) {
                                System.out.println("\n=== " + receita + " ===");
                                ultima = receita;
                                System.out.println("Categoria: " + rsDetalhe.getString(4));
                                System.out.println("Dificuldade: " + rsDetalhe.getString("dificuldade"));
                                System.out.println("Tempo: " + rsDetalhe.getInt("tempo") + " min");
                                System.out.println("Porções: " + rsDetalhe.getInt("porcoes"));
                                System.out.println("Utensílio: " + rsDetalhe.getString("utensilios"));
                                System.out.println("\nIngredientes:");


                                modoPreparo = rsDetalhe.getString("modo_preparo");
                                usuario = rsDetalhe.getString("nome");
                                dataCriacao = rsDetalhe.getDate("data_criacao");
                            }

                            System.out.printf(
                                    "%s - %.2f%s%n",
                                    rsDetalhe.getString("ingrediente"),
                                    rsDetalhe.getDouble("quantidade"),
                                    rsDetalhe.getString("unidade_medida")
                            );
                        }

                        System.out.println("\nModo de preparo:\n");
                        System.out.println(modoPreparo);
                        System.out.printf(" \nCriado por - %s - %tF \n", usuario, dataCriacao);

                            break;
                    case 3:
                        System.out.println("update");
                        System.out.println("Digite o ID para atualizar: ");
                        int idUpdate = sc.nextInt();
                        sc.nextLine();
                        System.out.println("Novo título: ");
                        String tituloUpdate = sc.nextLine();
                        System.out.println("Novo tempo de preparo (minutos): ");
                        int tempoUpdate = sc.nextInt();
                        sc.nextLine();

                        String updade = "UPDATE receita SET titulo = ?, tempo = ? WHERE id = ?";

                        PreparedStatement psUpdate = conexao.prepareStatement(updade);

                        psUpdate.setString(1, tituloUpdate);
                        psUpdate.setInt(2, tempoUpdate);
                        psUpdate.setInt(3, idUpdate);

                        psUpdate.executeUpdate();
                        System.out.println("Receita atualizada!");
                        break;
                    case 4:
                        System.out.println("delete");
                        System.out.println("Digite o ID que gostaria de deletar: ");
                        int idDelete = sc.nextInt();
                        sc.nextLine();

                        String delete = "DELETE FROM receita WHERE id = ?";
                        PreparedStatement psDelete = conexao.prepareStatement(delete);
                        psDelete.setInt(1, idDelete);
                        psDelete.executeUpdate();
                        System.out.println("Receita deletada com sucesso!");



                        break;
                    case 0:
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
