package repository.db;

import java.sql.ResultSet;

import DBSample.Item;

public class ItemManager extends DataBaseManager {

	public Object copyRecord(ResultSet rs) throws Exception{
		Item item = new Item();
		item.setRid(rs.getInt("RID"));
		item.setName(rs.getString("Name"));
		item.setPrice(rs.getInt("Price"));
		item.setDetails(rs.getString("Details"));
		item.setStock(rs.getInt("Stock"));
		return item;
	}

	public void insert(Item aItem){
		String sql = "";
		sql += "Insert into stockItem (Name,Price,Details,Stock) values (";
		sql += "'" + aItem.getName() + "'";
		sql += ",";
		sql += aItem.getPrice();
		sql += ",";
		sql += "'"+ aItem.getDetails() +"'";
		sql += ",";
		sql += aItem.getStock();
		sql += ")";
		updateRecord(sql);
	}

	public Item get(int id){
		return (Item)getRecord("select * from stockItem where RID=" + id);
	}

}
