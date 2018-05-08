//$Id$
package com.kazen.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import com.kazen.util.DateUtil;

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

    public Integer getTotalDebitAmount() {
        Integer amount = 0;
        Map<Long, DebitTransaction> debitData = getDebitTransaction();
        for( Entry<Long,DebitTransaction>entry : debitData.entrySet()) {
            DebitTransaction transaction = entry.getValue();
            amount = amount + transaction.getAmount();
        }
        return amount;
    }
    
    public Integer getTotalPayableAmount() {
        Integer amount = 0;
        Map<Long, DebitTransaction> debitData = getDebitTransaction();
        for( Entry<Long,DebitTransaction>entry : debitData.entrySet()) {
            DebitTransaction transaction = entry.getValue();
            amount = amount + transaction.getPayableAmount();
        }
        return amount;
    }
    
    public Integer getTotalPaidAmount() {
        Integer amount = 0;
        Map<Long, CreditTransaction> creditData = getCreditTransaction();
        for( Entry<Long,CreditTransaction>entry : creditData.entrySet()) {
            CreditTransaction transaction = entry.getValue();
            amount = amount + transaction.getAmount();
        }
        return amount;
    }
    
    
	public void getUserData() {
	    Map<String,Object> userData = new HashMap<String,Object>();
	    userData.put("user_id", getUserId());
		userData.put("user_name", getUserName());

		Integer amountDebited = 0;
		Integer amountPayable = 0;
		Integer amountPaid = 0;
		
		List<Map<String,Object>> debitData = new ArrayList<Map<String, Object>>();
		Map<Long, DebitTransaction> debitTransactions = getDebitTransaction();
        for( Entry<Long,DebitTransaction>entry : debitTransactions.entrySet()) {
            DebitTransaction transaction = entry.getValue();
            
            amountDebited = amountDebited + transaction.getAmount();
            amountPayable = amountPayable + transaction.getPayableAmount();
            
            Map<String,Object> transactionData = transaction.getTransactionData();
            debitData.add(transactionData);
            
        }
		List<Map<String,Object>> creditData = new ArrayList<Map<String, Object>>();

        Map<Long, CreditTransaction> creditTransactions = getCreditTransaction();
        for( Entry<Long,CreditTransaction>entry : creditTransactions.entrySet()) {
            CreditTransaction transaction = entry.getValue();
            amountPaid = amountPaid + transaction.getAmount();
            
            Map<String,Object> transactionData = transaction.getTransactionData();
            creditData.add(transactionData);
        }
        
        userData.put("amount_debit", amountDebited);
        userData.put("amount_credit", amountPaid);
        userData.put("amount_payable", amountPayable);
        
        Integer amountBalance = amountPayable - amountPaid;
        userData.put("amount_balance", amountBalance);
        
        userData.put("credit_transactions", creditData);
        userData.put("debit_transactions", debitData);
	}
}
