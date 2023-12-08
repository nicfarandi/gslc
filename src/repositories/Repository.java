package repositories;

import models.Model;
import utils.Connection;

import java.util.ArrayList;

public interface Repository<T extends Model> {
	ArrayList<T> find(String column, String[] kondisi, boolean joinTable, String joinTableName,Connection conn);
	
	T findOne(String column, String[] kondisi, boolean joinTable, String joinTableName, Connection conn);
	
	T insert(String[] data, Connection conn); 
	
}
