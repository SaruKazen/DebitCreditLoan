//$Id$
package com.kazen.model;

public abstract class Transaction {

	private Long transactionId;
	private Long transactionDate;
	private Long userId;
	private Integer amount;

	public Transaction(Long transactionId, Long transactionDate, Long userId, Integer amount) {
		setTransactionId(transactionId);
		setTransactionDate(transactionDate);
		setUserId(userId);
		setAmount(amount);
	}

	private void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	private void setUserId(Long userId) {
		this.userId = userId;
	}

	private void setAmount(Integer amount) {
		this.amount = amount;
	}

	private void setTransactionDate(Long transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Long getTransactionId() {
		return this.transactionId;
	}

	public Long getTransactionDate() {
		return this.transactionDate;
	}

	public Long getUserId() {
		return this.userId;
	}

	public Integer getAmount() {
		return this.amount;
	}
}
