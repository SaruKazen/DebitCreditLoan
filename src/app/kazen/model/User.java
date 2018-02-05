//$Id$
package app.kazen.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.kazen.util.DateUtil;

public class User {

	private Long userId;
	private String userName;
	private Map<Long, CreditTransaction> creditTransaction;
	private Map<Long, DebitTransaction> debitTransaction;

	public User(String userName) {
		setUserId(DateUtil.getCurrentTime());
		setUserName(userName);
	}

	public User(Long userId, String userName, List<CreditTransaction> creditTransactions, List<DebitTransaction> debitTransactions) {
		setUserId(userId);
		setUserName(userName);
		setCreditTransaction(creditTransactions);
		setDebitTransaction(debitTransactions);
	}

	private void setUserId(Long userId) {
		this.userId = userId;
	}

	private void setUserName(String userName) {
		this.userName = userName;
	}

	private void setCreditTransaction(List<CreditTransaction> creditTransactions) {
		if (creditTransactions == null) {
			return;
		}
		for (CreditTransaction creditTransaction : creditTransactions) {
			addCreditTransaction(creditTransaction);
		}
	}

	private void setDebitTransaction(List<DebitTransaction> debitTransactions) {
		if (debitTransactions == null) {
			return;
		}
		for (DebitTransaction debitTransaction : debitTransactions) {
			addDebitTransaction(debitTransaction);
		}
	}

	public void addCreditTransaction(CreditTransaction creditTransaction) {
		if (getUserId() == creditTransaction.getUserId()) {
			getCreditTransaction().put(creditTransaction.getTransactionId(), creditTransaction);
		}
	}

	public void addDebitTransaction(DebitTransaction debitTransaction) {
		if (getUserId() == debitTransaction.getUserId()) {
			getDebitTransaction().put(debitTransaction.getTransactionId(), debitTransaction);
		}
	}

	public Long getUserId() {
		return this.userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public Map<Long, CreditTransaction> getCreditTransaction() {
		if (this.creditTransaction == null) {
			this.creditTransaction = new HashMap<Long, CreditTransaction>();
		}
		return this.creditTransaction;
	}

	public Map<Long, DebitTransaction> getDebitTransaction() {
		if (this.debitTransaction == null) {
			this.debitTransaction = new HashMap<Long, DebitTransaction>();
		}
		return this.debitTransaction;
	}
}
