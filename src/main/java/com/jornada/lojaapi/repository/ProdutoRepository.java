package com.jornada.lojaapi.repository;

import com.jornada.lojaapi.entity.Produto;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository //BEAN
public class ProdutoRepository {

    public Produto salvarProdutoDB(Produto produto) {
        Connection connection = null;
        try {
            // abrir conexao
            connection = ConexaoDB.getConnection();

            // executar operacao
            String sqlSequence = "select seq_produto.nextval proxval from DUAL";
            Statement statement = connection.createStatement();
            ResultSet retorno = statement.executeQuery(sqlSequence);

            Integer proximoId = -1;
            if (retorno.next()) {
                proximoId = retorno.getInt("proxval");
            }

            String sql = "INSERT INTO PRODUTO " +
                    " (ID_PRODUTO, NOME, QUANTIDADE, PRECO, DATA_DE_VALIDADE) " +
                    " VALUES" +
                    " (?,?,?,?,?) ";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, proximoId);
            preparedStatement.setString(2, produto.getNome());
            preparedStatement.setInt(3, produto.getQuantidade());
            preparedStatement.setDouble(3, produto.getPreco());
            // java.util.Date -> java.sql.Date
            preparedStatement.setDate(4, new Date(produto.getDataDeValidade().getTime()));


            int resposta = preparedStatement.executeUpdate();
            System.out.println("salvarProdutoDB.resposta = " + resposta);

            produto.setIdProduto(proximoId);
            return produto;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            // fechar conexao
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    public List<Produto> listar() {
        List<Produto> listaProdutos = new ArrayList<>();

        Connection connection = null;
        try {
            // abrir conexao
            connection = ConexaoDB.getConnection();

            String sql = "SELECT * FROM PRODUTO";

            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery(sql);

            while (res.next()) {
                Produto produto = new Produto();
                produto.setIdProduto(res.getInt("id_produto"));
                produto.setNome(res.getString("nome"));
                produto.setQuantidade(res.getInt("quantidade"));
                produto.setPreco(res.getDouble("preco"));
                produto.setDataDeValidade(res.getDate("DATA_DE_VALIDADE"));
                listaProdutos.add(produto);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            // fechar conexao
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return listaProdutos;
    }

    public boolean editar(Produto produto) {
        Connection connection = null;
        try {
            // abrir conexao
            connection = ConexaoDB.getConnection();

            // update
            String sql = "update produto set " +
                    " nome = ?, " +
                    " quantidade = ?," +
                    " preco = ?," +
                    " DATA_DE_VALIDADE = ? " +
                    " where id_produto = ? ";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, produto.getNome());
            preparedStatement.setInt(2, produto.getQuantidade());
            preparedStatement.setDouble(2, produto.getPreco());
            preparedStatement.setDate(3, new Date(produto.getDataDeValidade().getTime()));
            preparedStatement.setInt(4, produto.getIdProduto());

            //executar
            int resultado = preparedStatement.executeUpdate();
            return resultado > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            // fechar conexao
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    public boolean excluir(Integer idProduto) {
        Connection connection = null;
        try {
            connection = ConexaoDB.getConnection();

            String sql = "delete from produto where id_produto = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idProduto);

            int resultado = preparedStatement.executeUpdate();
            return resultado > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            // fechar conexao
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    public List<Produto> listarPorNome(String nome) {
        List<Produto> listaProdutos = new ArrayList<>();

        Connection connection = null;
        try {
            // abrir conexao
            connection = ConexaoDB.getConnection();

            String sql = "SELECT * FROM PRODUTO where NOME like ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + nome + "%");
            ResultSet res = statement.executeQuery();

            while (res.next()) {
                Produto produto = new Produto();
                produto.setIdProduto(res.getInt("id_produto"));
                produto.setNome(res.getString("nome"));
                produto.setQuantidade(res.getInt("quantidade"));
                produto.setPreco(res.getDouble("preco"));
                produto.setDataDeValidade(res.getDate("DATA_DE_VALIDADE"));
                listaProdutos.add(produto);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            // fechar conexao
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return listaProdutos;
    }

    public List<Produto> listarPorId(Integer id) {
        List<Produto> listaProdutos = new ArrayList<>();

        Connection connection = null;
        try {
            // abrir conexao
            connection = ConexaoDB.getConnection();

            String sql = "SELECT * FROM PRODUTO where id_produto = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet res = statement.executeQuery();

            while (res.next()) {
                Produto produto = new Produto();
                produto.setIdProduto(res.getInt("id_produto"));
                produto.setNome(res.getString("nome"));
                produto.setQuantidade(res.getInt("quantidade"));
                produto.setPreco(res.getDouble("preco"));
                produto.setDataDeValidade(res.getDate("DATA_DE_VALIDADE"));
                listaProdutos.add(produto);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            // fechar conexao
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return listaProdutos;
    }

    public List<Produto> listarPorPreco(Integer id) {
        List<Produto> listaProdutos = new ArrayList<>();

        Connection connection = null;
        try {
            // abrir conexao
            connection = ConexaoDB.getConnection();

            String sql = "SELECT * FROM PRODUTO where id_produto = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet res = statement.executeQuery();

            while (res.next()) {
                Produto produto = new Produto();
                produto.setIdProduto(res.getInt("id_produto"));
                produto.setNome(res.getString("nome"));
                produto.setQuantidade(res.getInt("quantidade"));
                produto.setPreco(res.getDouble("preco"));
                produto.setDataDeValidade(res.getDate("DATA_DE_VALIDADE"));
                listaProdutos.add(produto);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            // fechar conexao
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return listaProdutos;
    }
}

//    private static List<Produto> produtos = new ArrayList<>();
//    private static int nextId = 1;
//
//    public Produto salvarProdutoDB(Produto produto) {
//        produto.setIdProduto(nextId++);
//        produtos.add(produto);
//        return produto;
//    }
//
//    public boolean editar(Produto produto) {
//        for (Produto p : produtos) {
//            if (p.getIdProduto().equals(produto.getIdProduto())) {
//                p.setNome(produto.getNome());
//                p.setPreco(produto.getPreco());
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public List<Produto> listar() {
//        return produtos;
//    }
//
//    public List<Produto> listarPorNome(String nome) {
//        List<Produto> produtosPorNome = new ArrayList<>();
//        for (Produto p : produtos) {
//            if (p.getNome().equalsIgnoreCase(nome)) {
//                produtosPorNome.add(p);
//            }
//        }
//        return produtosPorNome;
//    }
//
//    public List<Produto> listarPorId(Integer id) {
//        List<Produto> produtosPorId = new ArrayList<>();
//        for (Produto p : produtos) {
//            if (p.getIdProduto().equals(id)) {
//                produtosPorId.add(p);
//            }
//        }
//        return produtosPorId;
//    }
//
//    public boolean excluir(Integer id) {
//        for (Produto p : produtos) {
//            if (p.getIdProduto().equals(id)) {
//                produtos.remove(p);
//                return true;
//            }
//        }
//        return false;
//    }
//}
