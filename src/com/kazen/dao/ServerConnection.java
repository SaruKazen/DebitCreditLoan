//$Id$
package com.kazen.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.kazen.model.CreditTransaction;
import com.kazen.model.DebitTransaction;
import com.kazen.model.User;
import com.kazen.util.DateUtil;

public class ServerConnection {

	private static final Logger LOGGER = Logger.getLogger(ServerConnection.class.getName());

	private Connection connect = null;

	public Connection getConnection() {
		return this.connect;
	}
	private ServerConnection() {
		Statement command = null;
		try {
			checkDatabaseConnection();
			connect = DriverManager.getConnection("jdbc:mysql://localhost/DebitCrebitLoan", "root", "");
			command = connect.createStatement();
			try {
				command.executeUpdate(" create table UserInfo(UserId BIGINT,UserName VARCHAR(50),CreatedDate BIGINT)");
				LOGGER.log(Level.INFO, "UserInfo table created successfully");
			} catch (Exception e) {
				LOGGER.log(Level.FINE, "UserInfo table exists", e);
			}
			try {
				command.executeUpdate(" create table DebitTransactions(TransactionId BIGINT,UserId BIGINT, Amount INT,Interest DOUBLE ,TransactionDate BIGINT)");
				LOGGER.log(Level.INFO, "DebitTransactions table created successfully");
			} catch (Exception e) {
				LOGGER.log(Level.FINE, "DebitTransactions table exists", e);
			}
			try {
				command.executeUpdate(" create table CreditTransactions(TransactionId BIGINT,UserId BIGINT, Amount INT,TransactionDate BIGINT)");
				LOGGER.log(Level.INFO, "CreditTransactions table created successfully");
			} catch (Exception e) {
				LOGGER.log(Level.FINE, "CreditTransactions table exists");
			}
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Something went wrong", e);
		} finally {
			close(command);
		}

	}

	private void checkDatabaseConnection() throws SQLException {
		Statement command = null;
		try {
			connect = DriverManager.getConnection("jdbc:mysql://localhost/DebitCrebitLoan", "root", "");
			LOGGER.log(Level.FINE, "Database DebitCreditLoan exists");
		} catch (Exception e) {
			LOGGER.log(Level.INFO, "Database missing, recreating.....");
			connect = DriverManager.getConnection("jdbc:mysql://localhost", "root", "");
			LOGGER.log(Level.INFO, "Creating database...");
			command = connect.createStatement();
			String sql = "CREATE DATABASE DebitCrebitLoan";
			command.executeUpdate(sql);
			LOGGER.log(Level.INFO, "Database created successfully...");
		} finally {
			close(command);
			close(connect);
		}

	}

	private void close(Connection io) {
		if (io != null) {
			try {
				io.close();
			} catch (Exception e) {
				LOGGER.log(Level.SEVERE, "Something went wrong", e);
			}
		}
	}

	private void close(Statement io) {
		if (io != null) {
			try {
				io.close();
			} catch (Exception e) {
				LOGGER.log(Level.SEVERE, "Something went wrong", e);
			}
		}
	}

	public static ServerConnection getInstance() {
		return new ServerConnection();
	}

	public Map<Long, User> getUsers() {
		Map<Long, User> users = new HashMap<Long, User>();
		Statement command = null;
		ResultSet rs = null;
		try {
			command = connect.createStatement();

			rs = command.executeQuery("select * from UserInfo");
			while (rs.next()) { // Loading the user Information to the user class objects
				try {
					users.put(rs.getLong("UserId"), new User(rs.getLong("UserId"), rs.getString("UserName"), null, null));
				} catch (Exception e) {
					LOGGER.log(Level.SEVERE, "unable to parse data....", e);
				}
			}

			rs = command.executeQuery("select * from DebitTransactions");
			while (rs.next()) { // load debit transactions to the user object
				try {
					Long transactionId = rs.getLong("TransactionId");
					Long transactionDate = rs.getLong("TransactionDate");
					Long userId = rs.getLong("UserId");
					Integer amount = rs.getInt("Amount");
					Double interest = rs.getDouble("Interest");
					DebitTransaction debitTransaction = new DebitTransaction(transactionId, transactionDate, userId, amount, interest);
					if (users.containsKey(userId)) {
						users.get(userId).addDebitTransaction(debitTransaction);
					}

				} catch (Exception e) {
					LOGGER.log(Level.SEVERE, "unable to parse data....", e);
				}
			}

			rs = command.executeQuery("select * from CreditTransactions");
			while (rs.next()) { // load debit transactions to the user object
				try {
					Long transactionId = rs.getLong("TransactionId");
					Long transactionDate = rs.getLong("TransactionDate");
					Long userId = rs.getLong("UserId");
					Integer amount = rs.getInt("Amount");
					CreditTransaction creditTransaction = new CreditTransaction(transactionId, transactionDate, userId, amount);
					if (users.containsKey(userId)) {
						users.get(userId).addCreditTransaction(creditTransaction);
					}
				} catch (Exception e) {
					LOGGER.log(Level.SEVERE, "unable to parse data....", e);
				}
			}

		} catch (Exception e) {
			LOGGER.log(Level.WARNING, "Error in getting userdetails", e);
		} finally {
			close(command);
		}

		return users;
	}

	public Boolean addUser(User user) {
		Statement command = null;
		try {
			command = connect.createStatement();
			command.executeUpdate("insert into UserInfo values('" + user.getUserId() + "','" + user.getUserName() + "','" + DateUtil.getCurrentTime()
					+ "')");
		} catch (Exception e) {
			return Boolean.FALSE;
		} finally {
			close(command);
		}
		return Boolean.TRUE;
	}
}
