package com.jornada.lojaapi.repository;

import com.jornada.lojaapi.entity.Cliente;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ClienteRepository {

    public Cliente salvarClienteDB(Cliente cliente) {
        Connection connection = null;
        try {
            // abrir conexao
            connection = ConexaoDB.getConnection();

            // executar operacao
            String sqlSequence = "select seq_cliente.nextval proxval from DUAL";
            Statement statement = connection.createStatement();
            ResultSet retorno = statement.executeQuery(sqlSequence);

            Integer proximoId = -1;
            if (retorno.next()) {
                proximoId = retorno.getInt("proxval");
            }

            String sql = "INSERT INTO CLIENTE " +
                    " (ID_CLIENTE, NOME, CPF, TELEFONE) " +
                    " VALUES" +
                    " (?,?,?,?) ";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, proximoId);
            preparedStatement.setString(2, cliente.getNome());
            preparedStatement.setInt(3, cliente.getCpf());
            preparedStatement.setInt(3, cliente.getTelefone());
            // java.util.Date -> java.sql.Date

            int resposta = preparedStatement.executeUpdate();
            System.out.println("salvarClienteDB.resposta = " + resposta);

            cliente.setIdCliente(proximoId);
            return cliente;
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

    public List<Cliente> listar() {
        List<Cliente> listaClientes = new ArrayList<>();

        Connection connection = null;
        try {
            // abrir conexao
            connection = ConexaoDB.getConnection();

            String sql = "SELECT * FROM CLIENTE";

            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery(sql);

            while (res.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(res.getInt("id_cliente"));
                cliente.setNome(res.getString("nome"));
                cliente.setIdCliente(res.getInt("cpf"));
                cliente.setIdCliente(res.getInt("telefone"));
                listaClientes.add(cliente);
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
        return listaClientes;
    }

    public boolean editar(Cliente cliente) {
        Connection connection = null;
        try {
            // abrir conexao
            connection = ConexaoDB.getConnection();

            // update
            String sql = "update cliente set " +
                    " nome = ?, " +
                    " cpf = ?," +
                    " telefone = ? " +
                    " where id_cliente = ? ";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, cliente.getNome());
            preparedStatement.setInt(2, cliente.getCpf());
            preparedStatement.setInt(2, cliente.getTelefone());
            preparedStatement.setInt(4, cliente.getIdCliente());

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

    public boolean excluir(Integer idCliente) {
        Connection connection = null;
        try {
            connection = ConexaoDB.getConnection();

            String sql = "delete from cliente where id_cliente = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idCliente);

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

    public List<Cliente> listarPorNome(String nome) {
        List<Cliente> listaClientes = new ArrayList<>();

        Connection connection = null;
        try {
            // abrir conexao
            connection = ConexaoDB.getConnection();

            String sql = "SELECT * FROM CLIENTE where NOME like ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + nome + "%");
            ResultSet res = statement.executeQuery();

            while (res.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(res.getInt("id_cliente"));
                cliente.setNome(res.getString("nome"));
                cliente.setCpf(res.getInt("cpf"));
                cliente.setTelefone(res.getInt("telefone"));
                listaClientes.add(cliente);
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
        return listaClientes;
    }
}

