package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jp.co.aforce.bean.Client;

public class ClientDAO extends DAO {
    
    public Client search(String identity, String password) throws Exception {
        Client client = null;
        
        Connection con = getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            st = con.prepareStatement("SELECT * FROM user WHERE identity=? AND password=?");
            st.setString(1, identity);
            st.setString(2, password);
            rs = st.executeQuery();
            
            if (rs.next()) {
                client = new Client();
                client.setId(rs.getInt("id"));
                client.setIdentity(rs.getString("identity"));
                client.setFullname1(rs.getString("fullname1"));
                client.setFullname2(rs.getString("fullname2"));
                client.setPassword(rs.getString("password"));
                client.setEmail(rs.getString("email"));
                client.setBirthday(rs.getString("birthday"));
                client.setPhone(rs.getString("phone"));
                client.setFax(rs.getString("fax"));
            }
        } finally {
            close(con, st, rs);
        }
        
        return client;
    }

    public boolean checkExistence(String identity) throws Exception {
        boolean exists = false;
        
        Connection con = getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        
        try {
            st = con.prepareStatement("SELECT * FROM user WHERE identity=?");
            st.setString(1, identity);
            rs = st.executeQuery();
            
            if (rs.next()) {
                exists = true;
            }
        } finally {
            close(con, st, rs);
        }
        
        return exists;
    }

    public void create(Client newClient) throws Exception {
        Connection con = getConnection();
        PreparedStatement st = null;
        
        try {
            st = con.prepareStatement(
                "INSERT INTO user (identity, fullname1, fullname2, password, email, birthday, phone, fax) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
            );
            st.setString(1, newClient.getIdentity());
            st.setString(2, newClient.getFullname1());
            st.setString(3, newClient.getFullname2());
            st.setString(4, newClient.getPassword());
            st.setString(5, newClient.getEmail());
            st.setString(6, newClient.getBirthday());
            st.setString(7, newClient.getPhone());
            st.setString(8, newClient.getFax());
            st.executeUpdate();
        } finally {
            close(con, st);
        }
    }
    
    private void close(Connection con, PreparedStatement st, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        close(con, st);
    }
    
    private void close(Connection con, PreparedStatement st) {
        if (st != null) {
            try {
                st.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
