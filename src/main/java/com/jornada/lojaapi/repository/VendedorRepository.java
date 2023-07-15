package com.jornada.lojaapi.repository;


import com.jornada.lojaapi.entity.Vendedor;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class VendedorRepository {

    public Vendedor salvarVendedorDB(Vendedor vendedor) {
        Connection connection = null;
        try {
            // abrir conexao
            connection = ConexaoDB.getConnection();

            // executar operacao
            String sqlSequence = "select seq_vendedor.nextval proxval from DUAL";
            Statement statement = connection.createStatement();
            ResultSet retorno = statement.executeQuery(sqlSequence);

            Integer proximoId = -1;
            if (retorno.next()) {
                proximoId = retorno.getInt("proxval");
            }

            String sql = "INSERT INTO VENDEDOR " +
                    " (ID_VENDEDOR, NOME, CPF, TELEFONE) " +
                    " VALUES" +
                    " (?,?,?,?) ";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, proximoId);
            preparedStatement.setString(2, vendedor.getNome());
            preparedStatement.setLong(3, vendedor.getCpf());
            preparedStatement.setLong(4, vendedor.getTelefone());
            // java.util.Date -> java.sql.Date

            int resposta = preparedStatement.executeUpdate();
            System.out.println("salvarVendedorDB.resposta = " + resposta);

            vendedor.setIdVendedor(proximoId);
            return vendedor;
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

    public List<Vendedor> listar() {
        List<Vendedor> listaVendedores = new ArrayList<>();

        Connection connection = null;
        try {
            // abrir conexao
            connection = ConexaoDB.getConnection();

            String sql = "SELECT * FROM VENDEDOR";

            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery(sql);

            while (res.next()) {
                Vendedor vendedor = new Vendedor();
                vendedor.setIdVendedor(res.getInt("id_vendedor"));
                vendedor.setNome(res.getString("nome"));
                vendedor.setCpf(res.getLong("cpf"));
                vendedor.setTelefone(res.getLong("telefone"));
                listaVendedores.add(vendedor);
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
        return listaVendedores;
    }

    public boolean editar(Vendedor vendedor) {
        Connection connection = null;
        try {
            // abrir conexao
            connection = ConexaoDB.getConnection();

            // update
            String sql = "update vendedor set " +
                    " nome = ?, " +
                    " cpf = ?," +
                    " telefone = ? " +
                    " where id_vendedor = ? ";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, vendedor.getNome());
            preparedStatement.setLong(2, vendedor.getCpf());
            preparedStatement.setLong(3, vendedor.getTelefone());
            preparedStatement.setInt(4, vendedor.getIdVendedor());

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

    public boolean excluir(Integer idVendedor) {
        Connection connection = null;
        try {
            connection = ConexaoDB.getConnection();

            String sql = "delete from vendedor where id_vendedor = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idVendedor);

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

    public List<Vendedor> listarPorNome(String nome) {
        List<Vendedor> listaVendedores = new ArrayList<>();

        Connection connection = null;
        try {
            // abrir conexao
            connection = ConexaoDB.getConnection();

            String sql = "SELECT * FROM VENDEDOR where NOME like ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + nome + "%");
            ResultSet res = statement.executeQuery();

            while (res.next()) {
                Vendedor vendedor = new Vendedor();
                vendedor.setIdVendedor(res.getInt("id_vendedor"));
                vendedor.setNome(res.getString("nome"));
                vendedor.setCpf(res.getLong("cpf"));
                vendedor.setTelefone(res.getLong("telefone"));
                listaVendedores.add(vendedor);
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
        return listaVendedores;
    }
}

