package repository.db;

import java.sql.ResultSet;
import java.util.List;

import beans.Pc;
import repository.RepositoryInterface;

public class DataBaseRepository implements RepositoryInterface{

	public Object copyRecord(ResultSet rs) throws Exception{
		Item item = new Item();
		item.setRid(rs.getInt("RID"));
		item.setName(rs.getString("Name"));
		item.setPrice(rs.getInt("Price"));
		item.setDetails(rs.getString("Details"));
		item.setStock(rs.getInt("Stock"));
		return item;
	}
	
	@Override
	public List<Pc> getPcList() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public void updatePc(Pc pc) {
		// TODO 自動生成されたメソッド・スタブ
		
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
