package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DBQueries {
	private Statement statement =  null;
	private String    tableName 	= "";
	private String[]  fieldsName 	= new String[]{};
	private String    fieldKey  	= ""; // para o delete e update
	private int		  keyFieldIndex = -1;
	
	// Construtor da classe
	public DBQueries() {
		try {
			this.statement = new DBConnection().getConnection().createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public DBQueries(String tableName, String fieldsName, String fieldKey) {
		this.setTableName(tableName);
		this.setFieldsName(fieldsName);
		this.setFieldKey(fieldKey); 
		try {
			this.statement = new DBConnection().getConnection().createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// busca a posição do campo chave
	private int whereIsKeyField() {
		for (int i = 0; i < this.fieldsName.length; i++){
			if(this.fieldsName[i].equals(this.fieldKey)){
				return(i);
			}
		}
		return(-1);
	}
	
	// Transforma o array em uma string formatada
	public String strFields (String[] elements, String separator){
		String out = "";
		
		// O loop percorre os campos passados, remove o espaço e, caso não for o ultimo elemento, coloca um separador entre os valores
		for (int i = 0; i < elements.length; i++) {
			out +=  elements[i].trim() + ((i == elements.length-1) ? "" : separator);
		}
		return (out);
	}
	
	// irá executar atualizações (INSERT, UPDATE e DELETE)
	public int execute(String query) {
		try {
			int rs = statement.executeUpdate(query);
			return (rs);
		} catch (Exception e) {
		}
		return 0;
	}
	
	// Executa consultas SELECT - ResultSet armazena os resultados/tabela da consulta 
	public ResultSet querySelect(String querySelect) {
		try {
			ResultSet rs = statement.executeQuery(querySelect);
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ResultSet select(String where) {
		String sql = "SELECT " +  this.strFields(this.fieldsName, ", ") + " FROM " + this.tableName;
		// verifica se where foi parametrizado
		sql += (( where!= "") ? " WHERE " + where : "" );
		System.out.print(sql);
		return this.querySelect(sql);
	}
	
	// Verifica se o insert corresponde a todos os campos da tabela
	public boolean incompleteValues(String[] values) {
		if (values.length != this.fieldsName.length){
			 System.out.println("Campos esperados: " + Arrays.toString(this.fieldsName));
			for (String value : values) {
				System.out.println(value);
			}
			System.out.println("\n A quantidade de campos é diferente da quantidade de valores!");
			return true;
		}
		else return false;
	}
	
	// remove 'idUsuario' da lista de campos
	private String[] removeIdUsuario(String[] fields) {
	    List<String> fieldList = new ArrayList<>(Arrays.asList(fields));
	    
	    fieldList.remove("idUsuario");
	    return fieldList.toArray(new String[0]);
	}
	
	public int insert(String[] values) {
		String[] filteredFields = removeIdUsuario(this.fieldsName);
		if (values == null || values.length != filteredFields.length) {
	        System.out.println("A quantidade de valores não corresponde à quantidade de campos.");
	        return 0;
	    }
		else {
			String sql = "INSERT INTO " + this.tableName + " ( "+ this.strFields(filteredFields, ", ");
			sql += ") VALUES ('" + strFields(values, "','")+"')";
			System.out.println("Query gerada: " + sql);
			return (this.execute(sql));
		}
	}
	
	public int delete(String[] values) {
		if (incompleteValues(values)) {
			return 0;
		}
		
		String sql = "\nDELETE FROM "+this.tableName+" ";
		if (this.keyFieldIndex < 0){
			return(0);
		}
		sql += "\n WHERE "+ this.fieldKey +" = '"+ values[this.keyFieldIndex] +"'"; // Chave primaria
		System.out.print(sql);
		return (this.execute(sql));
	}
	
	public int update(String[] values) {
		if (incompleteValues(values)) {
			return 0;
		}
		
		String sql = "\nUPDATE "+this.tableName+" SET ";
		for(int i=0; i < values.length; i++){
			sql += "\n\t "+
				this.fieldsName[i] + " = '"+values[i].trim()+"'" 
				+ ((i == values.length-1) ? "" : ", ");
		}
		if (this.keyFieldIndex < 0){
			return(0);
		}
		sql += "\n WHERE "+ this.fieldKey +" = '"+ values[this.keyFieldIndex] +"'";
		System.out.print(sql);
		return (this.execute(sql));
	}

	public String getTableName() {
		return tableName;
	}


	public void setTableName(String tableName) {
		this.tableName = tableName;
	}


	public String[] getFieldsName() {
		return fieldsName;
	}

	public void setFieldKey(String fieldKey) {
		this.fieldKey = fieldKey;
		this.keyFieldIndex = whereIsKeyField();
	}
	
	public int getKeyFieldIndex() {
		return keyFieldIndex;
	}
	
	public void setKeyFieldIndex(int keyFieldIndex) {
		this.keyFieldIndex = keyFieldIndex;
	}

	public void setFieldsName(String fieldsName) {
		this.fieldsName	= fieldsName.split(",");
		for (int i=0;  i< this.fieldsName.length; i++) {
			this.fieldsName[i] = this.fieldsName[i].trim();
		};
	}
}