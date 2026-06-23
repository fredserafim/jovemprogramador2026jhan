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


            int usuarioId = 0;

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
                    case 1:

                        System.out.println("Adicionar Receita");

                        System.out.println("Digite o titulo: ");
                        String titulo = sc.nextLine();

                        System.out.println("Digite o tempo: ");
                        int tempo = sc.nextInt();

                        System.out.println("""
                                     Dificuldade:
                                     1 - Facil
                                     2 - Media
                                     3 - Dificil
                                     """);
                        int complexidade_id = sc.nextInt();

                        System.out.println("Digite quantas pessoas serve: ");
                        int porcoes = sc.nextInt();

                        System.out.println("""
                                 Categoria:
                                 1 - Doce
                                 2 - Salgado
                                 3 - Agridoce
                                 """);
                        int categoria_id = sc.nextInt();
                        sc.nextLine();

                         // modo preparo
                        System.out.println("Modo de preparo: ");
                        String modoPreparo = sc.nextLine();

                        String sqlPreparo = "insert into preparo (modo_preparo, tempo_preparo) VALUES (?, ?)";
                        PreparedStatement psPreparo = conexao.prepareStatement(sqlPreparo, Statement.RETURN_GENERATED_KEYS);
                        psPreparo.setString(1, modoPreparo);
                        psPreparo.setInt(2, tempo);
                        psPreparo.executeUpdate();

                        ResultSet rsPrep = psPreparo.getGeneratedKeys();
                        rsPrep.next();
                        int preparo_id = rsPrep.getInt(1);

                        String sqlReceita = (" insert into receita(titulo, tempo, complexidade_id, porcoes, categoria_id, preparo_id, usuario_id, data_criacao)" +
                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?);");
                        PreparedStatement psReceita = conexao.prepareStatement(sqlReceita, Statement.RETURN_GENERATED_KEYS);

                        psReceita.setString(1, titulo);
                        psReceita.setInt(2, tempo);
                        psReceita.setInt(3, complexidade_id);
                        psReceita.setInt(4, porcoes);
                        psReceita.setInt(5, categoria_id);
                        psReceita.setInt(6, preparo_id);
                        psReceita.setInt(7, usuarioId);
                        psReceita.setDate(8, Date.valueOf(LocalDate.now()));

                        psReceita.executeUpdate();

                        ResultSet rsRec = psReceita.getGeneratedKeys();
                        rsRec.next();
                        int receita_id = rsRec.getInt(1);
                        // ingredientes

                        int continuarIng = 1;
                        while (continuarIng == 1) {

                            System.out.println("Ingrediente: ");
                            String nomeIng = sc.nextLine().trim().toLowerCase();

                            System.out.println("Quantidade: ");
                            double qtd = sc.nextDouble();
                            sc.nextLine();

                            System.out.println("Qual unidade de pedida : ");
                            String unidadeMedida = sc.nextLine().trim().toLowerCase();

                            String sqlBuscaIng = ("select id from ingredientes where lower(ingrediente) = ?;");
                            PreparedStatement psBuscaIng = conexao.prepareStatement(sqlBuscaIng);
                            psBuscaIng.setString(1, nomeIng);
                            ResultSet rsIng = psBuscaIng.executeQuery();

                            int ingrediente_id;
                            if (rsIng.next()) {
                                ingrediente_id = rsIng.getInt("id");
                            } else {
                                String sqlInsertIng = ("insert into ingredientes (ingrediente, unidade_medida) values (?, ?);");

                                PreparedStatement psInsertIng = conexao.prepareStatement(sqlInsertIng, Statement.RETURN_GENERATED_KEYS);
                                psInsertIng.setString(1, nomeIng);
                                psInsertIng.setString(2, unidadeMedida);
                                psInsertIng.executeUpdate();

                                ResultSet rsKey = psInsertIng.getGeneratedKeys();
                                rsKey.next();
                                ingrediente_id = rsKey.getInt(1);
                            }

                            String sqlRelIng = ("insert into ingrediente_receita (receita_id, ingredientes_id, quantidade, observacao) values (?, ?, ?, '');");
                            PreparedStatement psRelIng = conexao.prepareStatement(sqlRelIng);
                            psRelIng.setInt(1, receita_id);
                            psRelIng.setInt(2, ingrediente_id);
                            psRelIng.setDouble(3, qtd);
                            psRelIng.executeUpdate();

                            System.out.println("""
                                    Gostaria de acrescentar mais algum ingrediente?
                                    1 - Sim / 2 - Não""");
                            continuarIng = sc.nextInt();
                            sc.nextLine();
                        }

                    //utensilios
                        int continuarUt = 1;
                        while (continuarUt == 1) {

                            System.out.println("Utensílio: ");
                            String nomeUt = sc.nextLine().trim().toLowerCase();

                            String sqlBuscaUt = ("select id from ferramentas where lower(utensilios) = ? ;");
                            PreparedStatement psBuscaUt = conexao.prepareStatement(sqlBuscaUt);
                            psBuscaUt.setString(1, nomeUt);
                            ResultSet rsUt = psBuscaUt.executeQuery();

                            int ferramentas_id;
                            if (rsUt.next()) {
                                ferramentas_id = rsUt.getInt("id");
                            } else {
                                String sqlInsertUt = ("insert into ferramentas (utensilios) values (?) ;");

                                PreparedStatement psInsertUt = conexao.prepareStatement(sqlInsertUt, Statement.RETURN_GENERATED_KEYS);
                                psInsertUt.setString(1, nomeUt);
                                psInsertUt.executeUpdate();

                                ResultSet rsKey = psInsertUt.getGeneratedKeys();
                                rsKey.next();
                                ferramentas_id = rsKey.getInt(1);
                            }

                            String sqlRelUt =("insert into ferramenta_receita (receita_id, ferramentas_id) values (?, ?);");
                            PreparedStatement psRelUt = conexao.prepareStatement(sqlRelUt);
                            psRelUt.setInt(1, receita_id);
                            psRelUt.setInt(2, ferramentas_id);
                            psRelUt.executeUpdate();

                            System.out.println("""
                                    Gostaria de acrescentar mais algum utensilio?
                                    1 - Sim / 2 - Não""");
                            continuarUt = sc.nextInt();
                            sc.nextLine();
                        }

                        System.out.printf("""
                              =================================
                                RECEITA CADASTRADA COM SUCESSO
                              =================================
                             
                             Título:  %s
                             Tempo:   %d minutos
                             Porções: %d
                             """, titulo,tempo,porcoes);

                        break;
                    case 2:
                        System.out.println("Receitas");
                        System.out.println("O que gostaria de cozinhar: ");

                        String tituloid = sc.nextLine();

                        String select = ("""
                                            select id, titulo from receita
                                            where titulo like ?
                                            order by id asc;
                                
                                           """);
                        PreparedStatement psSelect = conexao.prepareStatement(select);
                        psSelect.setString(1, "%" + tituloid + "%");

                        ResultSet rsReceitas = psSelect.executeQuery();

                        System.out.println("Lista de Receitas");

                        while (rsReceitas.next()) {
                            System.out.printf("%d - %s \n",
                                    rsReceitas.getInt("id"),
                                    rsReceitas.getString("titulo")
                            );
                        }

                        System.out.println("Qual receita deseja (digite o ID): ");
                        int tituloid1 = sc.nextInt();
                        sc.nextLine();

                        // receitas
                        String selectReceita = """
                                               select re.titulo,com.dificuldade,cat.classificacao,re.tempo,re.porcoes,pre.modo_preparo,usu.nome,re.data_criacao
                                               from receita re
                                               join complexidade com on re.complexidade_id = com.id
                                               join categoria cat on re.categoria_id = cat.id
                                               join preparo pre on re.preparo_id = pre.id
                                               join usuario usu on re.usuario_id = usu.id
                                               where re.id = ?;
                                               """;

                        PreparedStatement psSelectReceita = conexao.prepareStatement(selectReceita);
                        psSelectReceita.setInt(1, tituloid1);

                        ResultSet rsReceita = psSelectReceita.executeQuery();

                        if (!rsReceita.next()) {
                            System.out.println("Receita não encontrada.");
                            break;
                        }

                        System.out.println("\n=== " + rsReceita.getString("titulo") + " ===");
                        System.out.println("Categoria: " + rsReceita.getString("classificacao"));
                        System.out.println("Dificuldade: " + rsReceita.getString("dificuldade"));
                        System.out.println("Tempo: " + rsReceita.getInt("tempo") + " min");
                        System.out.println("Porções: " + rsReceita.getInt("porcoes"));

                        // ingredientes
                        String selectIngredientes = """
                                                    select igrs.ingrediente,igr.quantidade,igrs.unidade_medida
                                                    from ingrediente_receita igr
                                                    join ingredientes igrs on igr.ingredientes_id = igrs.id
                                                    where igr.receita_id = ?;
                                                    """;

                        PreparedStatement psIng = conexao.prepareStatement(selectIngredientes);
                        psIng.setInt(1, tituloid1);

                        ResultSet rsIng = psIng.executeQuery();

                        System.out.println("\nIngredientes:");

                        while (rsIng.next()) {
                            System.out.printf("%s - %.2f %s%n",
                                    rsIng.getString("ingrediente"),
                                    rsIng.getDouble("quantidade"),
                                    rsIng.getString("unidade_medida")
                            );
                        }

                        // utensilios
                        String selectUtensilios = """
                                                  select fer.utensilios
                                                  from ferramenta_receita fr
                                                  join ferramentas fer on fer.id = fr.ferramentas_id
                                                  where fr.receita_id = ?;
                                                  """;

                        PreparedStatement psUt = conexao.prepareStatement(selectUtensilios);
                        psUt.setInt(1, tituloid1);

                        ResultSet rsUt = psUt.executeQuery();

                        System.out.println("\nUtensílios:");

                        while (rsUt.next()) {
                            System.out.println("- " + rsUt.getString("utensilios"));
                        }

                        //modo preparo
                        System.out.println("\nModo de preparo:\n");
                        System.out.println(rsReceita.getString("modo_preparo"));

                        System.out.printf("\nCriado por - %s - %tF \n",
                                rsReceita.getString("nome"),
                                rsReceita.getDate("data_criacao"));

                        break;
                        case 3:
                        System.out.println("update");

                        // LISTA RECEITAS
                        String selectReceitas = "SELECT id, titulo, tempo FROM receita";
                        Statement stmtList = conexao.createStatement();
                        ResultSet rsList = stmtList.executeQuery(selectReceitas);

                        System.out.println("Receitas cadastradas:");
                        while (rsList.next()) {
                            System.out.println("ID: " + rsList.getInt("id")
                                    + " | Título: " + rsList.getString("titulo")
                                    + " | Tempo: " + rsList.getInt("tempo") + " min");
                        }
                        rsList.close();
                        stmtList.close();

                        System.out.println("Digite o ID da receita:");
                        int idReceita = sc.nextInt();
                        sc.nextLine();

                        int opcEdit = -1;

                        while (opcEdit != 0) {

                            System.out.println("""
                                    ===== MENU EDIÇÃO =====
                                      1 - Editar título
                                      2 - Editar tempo
                                      3 - Editar porções
                                      4 - Editar categoria
                                      5 - Editar complexidade
                                      6 - Editar modo preparo
                                      7 - Editar ingredientes
                                      8 - Editar utensílios
                                      0 - Finalizar edição
                                    """);

                            opcEdit = sc.nextInt();
                            sc.nextLine();

                            switch (opcEdit) {

                                // titulo
                                case 1:
                                    System.out.println("Novo título:");
                                    String novoTitulo = sc.nextLine();

                                    String up1 = "UPDATE receita SET titulo = ? WHERE id = ?";
                                    PreparedStatement ps1 = conexao.prepareStatement(up1);
                                    ps1.setString(1, novoTitulo);
                                    ps1.setInt(2, idReceita);
                                    ps1.executeUpdate();

                                    System.out.println("Título atualizado!");
                                    break;

                                // tempo
                                case 2:
                                    System.out.println("Novo tempo:");
                                    int novoTempo = sc.nextInt();
                                    sc.nextLine();

                                    String up2 = "UPDATE receita SET tempo = ? WHERE id = ?";
                                    PreparedStatement ps2 = conexao.prepareStatement(up2);
                                    ps2.setInt(1, novoTempo);
                                    ps2.setInt(2, idReceita);
                                    ps2.executeUpdate();

                                    System.out.println("Tempo atualizado!");
                                    break;

                                // porcoes
                                case 3:
                                    System.out.println("Novas porções:");
                                    int novasPorcoes = sc.nextInt();
                                    sc.nextLine();

                                    String up3 = "UPDATE receita SET porcoes = ? WHERE id = ?";
                                    PreparedStatement ps3 = conexao.prepareStatement(up3);
                                    ps3.setInt(1, novasPorcoes);
                                    ps3.setInt(2, idReceita);
                                    ps3.executeUpdate();

                                    System.out.println("Porções atualizadas!");
                                    break;

                                // categoria
                                case 4:
                                    System.out.println("Nova categoria (id):");
                                    int cat = sc.nextInt();
                                    sc.nextLine();

                                    String up4 = "UPDATE receita SET categoria_id = ? WHERE id = ?";
                                    PreparedStatement ps4 = conexao.prepareStatement(up4);
                                    ps4.setInt(1, cat);
                                    ps4.setInt(2, idReceita);
                                    ps4.executeUpdate();

                                    System.out.println("Categoria atualizada!");
                                    break;

                                // complexidade
                                case 5:
                                    System.out.println("Nova complexidade (id):");
                                    int comp = sc.nextInt();
                                    sc.nextLine();

                                    String up5 = "UPDATE receita SET complexidade_id = ? WHERE id = ?";
                                    PreparedStatement ps5 = conexao.prepareStatement(up5);
                                    ps5.setInt(1, comp);
                                    ps5.setInt(2, idReceita);
                                    ps5.executeUpdate();

                                    System.out.println("Complexidade atualizada!");
                                    break;

                                // preparo
                                case 6: {
                                    System.out.println("Novo modo de preparo:");
                                    String novoPreparo = sc.nextLine();

                                    String selectPrepEdit = "SELECT preparo_id FROM receita WHERE id = ?";
                                    PreparedStatement psPrepEdit = conexao.prepareStatement(selectPrepEdit);
                                    psPrepEdit.setInt(1, idReceita);
                                    ResultSet rsPrepEdit = psPrepEdit.executeQuery();

                                    int preparoIdEdit = 0;
                                    if (rsPrepEdit.next()) {
                                        preparoIdEdit = rsPrepEdit.getInt("preparo_id");
                                    }

                                    String up6 = "UPDATE preparo SET modo_preparo = ? WHERE id = ?";
                                    PreparedStatement ps6 = conexao.prepareStatement(up6);
                                    ps6.setString(1, novoPreparo);
                                    ps6.setInt(2, preparoIdEdit);
                                    ps6.executeUpdate();

                                    System.out.println("Preparo atualizado!");
                                    break;
                                }

                                // ingredientes
                                case 7: {

                                    System.out.println("Ingredientes da receita:");

                                    String selectIngEdit = ("""
                        
                                            SELECT ir.id, i.ingrediente
                                                                    FROM ingrediente_receita ir
                                                                    JOIN ingredientes i ON i.
                                            
                                                                   WHERE ir.receita_id = ? ;
                        """);

                                    PreparedStatement psIngEdit = conexao.prepareStatement(selectIngEdit);
                                    psIngEdit.setInt(1, idReceita);
                                    ResultSet rsIngEdit = psIngEdit.executeQuery();

                                    while (rsIngEdit.next()) {
                                        System.out.println(rsIngEdit.getInt("id") + " - " + rsIngEdit.getString("ingrediente"));
                                    }

                                    System.out.println(
                                            """
                                                    1 - Trocar ingrediente
                                                    
                                                    
                                                                                   iente
                                                    
                                                    
                                                                                                  iente
                                                    
                                                                                                       oltar
                                                    """);

                                    int opIng = sc.nextInt();
                                    sc.nextLine();

                                    if (opIng == 1) {
                                        System.out.println("ID da relação ingrediente_receita:");
                                        int relId = sc.nextInt();
                                        sc.nextLine();

                                        System.out.println("Novo ingrediente (id):");
                                        int novoIng = sc.nextInt();
                                        sc.nextLine();

                                        String upIng = "UPDATE ingrediente_receita SET ingredientes_id = ? WHERE id = ?";
                                        PreparedStatement psUpIng = conexao.prepareStatement(upIng);
                                        psUpIng.setInt(1, novoIng);
                                        psUpIng.setInt(2, relId);
                                        psUpIng.executeUpdate();

                                        System.out.println("Ingrediente alterado!");
                                    }

                                    if (opIng == 2) {
                                        System.out.println("ID da relação para remover:");
                                        int relId = sc.nextInt();
                                        sc.nextLine();

                                        String delIng = "DELETE FROM ingrediente_receita WHERE id = ?";
                                        PreparedStatement psDel = conexao.prepareStatement(delIng);
                                        psDel.setInt(1, relId);
                                        psDel.executeUpdate();

                                        System.out.println("Ingrediente removido!");
                                    }

                                    if (opIng == 3) {
                                        System.out.println("Nome do ingrediente:");
                                        String nomeIng = sc.nextLine().trim().toLowerCase();

                                        System.out.println("Quantidade:");
                                        double qtd = sc.nextDouble();
                                        sc.nextLine();

                                        System.out.println("Unidade:");
                                        String uni = sc.nextLine();

                                        String sqlBusca = "SELECT id FROM ingredientes WHERE LOWER(ingrediente) = ?";
                                        PreparedStatement psBusca = conexao.prepareStatement(sqlBusca);
                                        psBusca.setString(1, nomeIng);
                                        ResultSet rsBusca = psBusca.executeQuery();

                                        int ingId;

                                        if (rsBusca.next()) {
                                            ingId = rsBusca.getInt("id");
                                        } else {
                                            String insertIng = "INSERT INTO ingredientes (ingrediente, unidade_medida) VALUES (?, ?)";
                                            PreparedStatement psIns = conexao.prepareStatement(insertIng, Statement.RETURN_GENERATED_KEYS);
                                            psIns.setString(1, nomeIng);
                                            psIns.setString(2, uni);
                                            psIns.executeUpdate();

                                            ResultSet rsKey = psIns.getGeneratedKeys();
                                            rsKey.next();
                                            ingId = rsKey.getInt(1);
                                        }

                                        String insertRel = "INSERT INTO ingrediente_receita (receita_id, ingredientes_id, quantidade, observacao) VALUES (?, ?, ?, '')";
                                        PreparedStatement psRel = conexao.prepareStatement(insertRel);
                                        psRel.setInt(1, idReceita);
                                        psRel.setInt(2, ingId);
                                        psRel.setDouble(3, qtd);
                                        psRel.executeUpdate();

                                        System.out.println("Ingrediente adicionado!");
                                    }

                                    break;
                                }

                                // utensilios
                                case 8: {

                                    System.out.println("Utensílios:");

                                    String selectUtEdit = """
            SELECT fr.id, f.utensilios
            FROM ferramenta_receita fr
            JOIN ferramentas f ON f.id = fr.ferramentas_id
            WHERE fr.receita_id = ? ;
            """;

                                    PreparedStatement psUtEdit = conexao.prepareStatement(selectUtEdit);
                                    psUtEdit.setInt(1, idReceita);
                                    ResultSet rsUtEdit = psUtEdit.executeQuery();

                                    while (rsUtEdit.next()) {
                                        System.out.println(rsUtEdit.getInt("id") + " - " + rsUtEdit.getString("utensilios"));
                                    }

                                    System.out.println("""
            1 - Trocar utensílio
            2 - Remover utensílio
            3 - Adicionar utensílio
            0 - Voltar
            """);

                                    int opUt = sc.nextInt();
                                    sc.nextLine();

                                    if (opUt == 1) {

                                        System.out.println("ID da relação:");
                                        int relId = sc.nextInt();
                                        sc.nextLine();

                                        System.out.println("Novo utensílio (id):");
                                        int novoUt = sc.nextInt();
                                        sc.nextLine();

                                        String upUt = "UPDATE ferramenta_receita SET ferramentas_id = ? WHERE id = ?";
                                        PreparedStatement psUp = conexao.prepareStatement(upUt);
                                        psUp.setInt(1, novoUt);
                                        psUp.setInt(2, relId);
                                        psUp.executeUpdate();

                                        System.out.println("Utensílio atualizado!");
                                    }

                                    if (opUt == 2) {

                                        System.out.println("ID da relação:");
                                        int relId = sc.nextInt();
                                        sc.nextLine();

                                        String delUt = "DELETE FROM ferramenta_receita WHERE id = ?";
                                        PreparedStatement psDel = conexao.prepareStatement(delUt);
                                        psDel.setInt(1, relId);
                                        psDel.executeUpdate();

                                        System.out.println("Utensílio removido!");
                                    }

                                    if (opUt == 3) {

                                        System.out.println("Nome do utensílio:");
                                        String nomeUt = sc.nextLine().trim().toLowerCase();

                                        String sqlBuscaUt = "SELECT id FROM ferramentas WHERE LOWER(utensilios) = ?";
                                        PreparedStatement psBuscaUt = conexao.prepareStatement(sqlBuscaUt);
                                        psBuscaUt.setString(1, nomeUt);
                                        ResultSet rsBuscaUt = psBuscaUt.executeQuery();

                                        int utId;

                                        if (rsBuscaUt.next()) {
                                            utId = rsBuscaUt.getInt("id");
                                        } else {
                                            String insertUt = "INSERT INTO ferramentas (utensilios) VALUES (?)";
                                            PreparedStatement psInsUt = conexao.prepareStatement(insertUt, Statement.RETURN_GENERATED_KEYS);
                                            psInsUt.setString(1, nomeUt);
                                            psInsUt.executeUpdate();

                                            ResultSet rsKey = psInsUt.getGeneratedKeys();
                                            rsKey.next();
                                            utId = rsKey.getInt(1);
                                        }

                                        String insertRelUt = "INSERT INTO ferramenta_receita (receita_id, ferramentas_id) VALUES (?, ?)";
                                        PreparedStatement psRelUt = conexao.prepareStatement(insertRelUt);
                                        psRelUt.setInt(1, idReceita);
                                        psRelUt.setInt(2, utId);
                                        psRelUt.executeUpdate();

                                        System.out.println("Utensílio adicionado!");
                                    }

                                    break;
                                } //
                            }



                        }


                        break;
                        case 4:
                        System.out.println("delete");

                        String selectReceitasDelete = "SELECT id, titulo, tempo FROM receita";
                        Statement stmtListDelete = conexao.createStatement();
                        ResultSet rsListDelete = stmtListDelete.executeQuery(selectReceitasDelete);

                        System.out.println("Receitas cadastradas:");
                        while (rsListDelete.next()) {
                            System.out.println("ID: " + rsListDelete.getInt("id")
                                    + " | Título: " + rsListDelete.getString("titulo")
                                    + " | Tempo: " + rsListDelete.getInt("tempo") + " min");
                        }
                        rsListDelete.close();
                        stmtListDelete.close();

                        System.out.println("Digite o ID que gostaria de deletar: ");
                        int idDelete = sc.nextInt();
                        sc.nextLine();

                        String deleteFerramentaReceita = "DELETE FROM ferramenta_receita WHERE receita_id = ?";
                        PreparedStatement psDeleteFerramentaReceita = conexao.prepareStatement(deleteFerramentaReceita);
                        psDeleteFerramentaReceita.setInt(1, idDelete);
                        psDeleteFerramentaReceita.executeUpdate();

                        String deleteIngredienteReceita = "DELETE FROM ingrediente_receita WHERE receita_id = ?";
                        PreparedStatement psDeleteIngredienteReceita = conexao.prepareStatement(deleteIngredienteReceita);
                        psDeleteIngredienteReceita.setInt(1, idDelete);
                        psDeleteIngredienteReceita.executeUpdate();

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
