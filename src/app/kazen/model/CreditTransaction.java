//$Id$
package app.kazen.model;

import app.kazen.util.DateUtil;

public class CreditTransaction extends Transaction{

	public CreditTransaction(Long transactionId, Long transactionDate,Long userId, Integer amount) {
		super(transactionId, transactionDate, userId, amount);
	}

	public CreditTransaction(Long userId, Integer amount) {
		super(DateUtil.getCurrentTime(), DateUtil.getCurrentTime(), userId, amount);
	}

}
