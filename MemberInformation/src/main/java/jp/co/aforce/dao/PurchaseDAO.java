package jp.co.aforce.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import jp.co.aforce.bean.Item;
import jp.co.aforce.bean.Product;



public class PurchaseDAO extends DAO{
	
	
	
	public boolean insert(
			
			List<Item> cart, String name, String address
			
			     )throws Exception{
		
	       Connection con=getConnection();
	       con.setAutoCommit(false);
	       
	       for (Item item: cart) {
		   PreparedStatement st = con.prepareStatement(
				"insert into purchase(product_id, product_name,"+
				" product_price,product_count,customer_name,"+
						"customer_address)values (?,?,?,?,?,?)");
		
		Product p=item.getProduct();
		
		st.setInt(1, p.getId());
		st.setString(2, p.getName());
		st.setInt(3, p.getPrice());
		st.setInt(4, item.getCount());
		st.setString(5, name);
		st.setString(6, address);

		
		
		int line=st.executeUpdate();
		
	
	st.close();
	if (line!=1) {
		con.rollback();
		con.setAutoCommit(true);
		con.close();
		return false;
	}}
	 
	con.commit();
	con.setAutoCommit(true);
	con.close();
	return true;
}
}
