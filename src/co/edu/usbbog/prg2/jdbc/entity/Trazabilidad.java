package co.edu.usbbog.prg2.jdbc.entity;

import java.util.Date;

public class Trazabilidad {
    public static void main(String[] args) {
        System.out.println("Hola".toUpperCase());
    }
    private int id;
    private int userId;
    private char operationCrud;
    private String tableName;
    private int tableId;
    private Date createDate;
    private String addressIP;

    public Trazabilidad() {
    }

    public Trazabilidad(int id, int userId, char operationCrud, String tableName,
            int tableId, Date createDate, String addressIP) {
        this.id = id;
        this.userId = userId;
        this.operationCrud = operationCrud;
        this.tableName = tableName;
        this.tableId = tableId;
        this.createDate = createDate;
        this.addressIP = addressIP;
    }

    // Métodos set y get
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public char getOperationCrud() {
        return operationCrud;
    }

    public void setOperationCrud(char operationCrud) {
        this.operationCrud = operationCrud;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getAddressIP() {
        return addressIP;
    }

    public void setAddressIP(String addressIP) {
        this.addressIP = addressIP;
    }

}
