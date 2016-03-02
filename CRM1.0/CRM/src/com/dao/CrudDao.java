package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jdbc.DBUtility;
import com.model.ContactInfo;

public class CrudDao {

	private Connection dbConnection;
	private PreparedStatement pStmt;
	private int currentSeqId;

	public CrudDao() {
		dbConnection = DBUtility.getConnection();
	}

	public int addContact(ContactInfo contact) {
		String insertQuery = "insert into contacts values(seq_contacts.nextval,?,?,?,?,?,?,?,?)";

		try {
			pStmt = dbConnection.prepareStatement(insertQuery);
			pStmt.setString(1, contact.getFirstName());
			pStmt.setString(2, contact.getLastName());
			pStmt.setString(3, contact.getAddress1());
			pStmt.setString(4, contact.getAddress2());
			pStmt.setString(5, contact.getCity());
			pStmt.setInt(6, contact.getPostalCode());
			pStmt.setInt(7, contact.getMobile());
			pStmt.setString(8, contact.getEmail());
			pStmt.executeUpdate();
			pStmt.close();

			String seqNumQuery = "select seq_contacts.currval from dual";
			pStmt = dbConnection.prepareStatement(seqNumQuery);
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				System.out.println("ksdjfalhjkdsjfhjsakdfkj" + rs.getInt(1));
				currentSeqId = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return currentSeqId;
	}

	public void deleteStudent(int userId) {
		String deleteQuery = "DELETE FROM CONTACTS WHERE ID = ?";
		try {
			pStmt = dbConnection.prepareStatement(deleteQuery);
			pStmt.setInt(1, userId);
			pStmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	public void updateContact(ContactInfo contact) {
		String updateQuery = "UPDATE CONTACTS SET first_name = ?, "
				+ "last_name = ?, Address1 = ?, Address2 = ?, City = ?, zipcode = ?, Mobile = ?, EMAIL = ? WHERE ID = ?";
		try {
			pStmt = dbConnection.prepareStatement(updateQuery);
			pStmt.setString(1, contact.getFirstName());
			pStmt.setString(2, contact.getLastName());
			pStmt.setString(3, contact.getAddress1());
			pStmt.setString(4, contact.getAddress2());
			pStmt.setString(5, contact.getCity());
			pStmt.setInt(6, contact.getPostalCode());
			pStmt.setInt(7, contact.getMobile());
			pStmt.setString(8, contact.getEmail());
			pStmt.setInt(9, contact.getId());
			System.out.println("yyyyyyyyyyyyyyyyyyyyyy" + contact.getId());
			pStmt.executeUpdate();

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	public List<ContactInfo> getAllContacts() {
		List<ContactInfo> contacts = new ArrayList<ContactInfo>();

		String query = "SELECT id,first_name,last_name,address1,address2,city,zipcode,mobile,email FROM CONTACTS";
		try {
			Statement stmt = dbConnection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				ContactInfo contactInfo = new ContactInfo();
				contactInfo.setId(rs.getInt("id"));
				System.out.println("IDDDDDDDDDDDDDDDDDDDDDDDDDDD" + rs.getInt("id"));
				contactInfo.setFirstName(rs.getString("first_name"));
				contactInfo.setLastName(rs.getString("last_name"));
				contactInfo.setAddress1(rs.getString("address1"));
				contactInfo.setAddress1(rs.getString("address2"));
				contactInfo.setCity(rs.getString("city"));
				contactInfo.setPostalCode(rs.getInt("zipcode"));
				contactInfo.setMobile(rs.getInt("mobile"));
				contactInfo.setEmail(rs.getString("email"));
				contacts.add(contactInfo);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return contacts;
	}
}