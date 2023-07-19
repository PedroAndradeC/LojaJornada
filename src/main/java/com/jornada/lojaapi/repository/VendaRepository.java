package com.jornada.lojaapi.repository;

import com.jornada.lojaapi.entity.Venda;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class VendaRepository {

    public Venda salvarVendaDB(Venda venda) {
        Connection connection = null;
        try {
            // abrir conexao
            connection = ConexaoDB.getConnection();

            // executar operacao
            String sqlSequence = "select seq_venda.nextval proxval from DUAL";
            Statement statement = connection.createStatement();
            ResultSet retorno = statement.executeQuery(sqlSequence);

            Integer proximoId = -1;
            if (retorno.next()) {
                proximoId = retorno.getInt("proxval");
            }

            String sql = "INSERT INTO VENDA " +
                    " (ID_VENDA, ID_PRODUTO, ID_CLIENTE, ID_VENDEDOR, ID_QUANTIDADE) " +
                    " VALUES" +
                    " (?,?,?,?,?) ";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, proximoId);
            preparedStatement.setInt(2, venda.getIdProduto());
            preparedStatement.setInt(3, venda.getIdCliente());
            preparedStatement.setInt(4, venda.getIdVendedor());
            preparedStatement.setInt(5, venda.getQuantidade());
            // java.util.Date -> java.sql.Date

            int resposta = preparedStatement.executeUpdate();
            System.out.println("salvarVendaDB.resposta = " + resposta);

            venda.setIdVenda(proximoId);
            return venda;
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
}
