//$Id$
package com.kazen.model;

import com.kazen.util.DateUtil;

public class DebitTransaction extends Transaction {

	private Double interest;

	public DebitTransaction(Long userId, Integer amount, Double interest) {
		super(DateUtil.getCurrentTime(), DateUtil.getCurrentTime(), userId, amount);
		setInterest(interest);
	}

	public DebitTransaction(Long transactionId, Long transactionDate, Long userId, Integer amount, Double interest) {
		super(transactionId, transactionDate, userId, amount);
		setInterest(interest);
	}

	private void setInterest(Double interest) {
		this.interest = interest;
	}

	public Double getInterest() {
		return this.interest;
	}

	public Integer getPayableAmount() {
		Integer payable = (int) (getAmount() * getInterest());
		return payable;
	}
}
