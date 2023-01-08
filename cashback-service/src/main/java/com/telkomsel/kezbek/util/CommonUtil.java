package com.telkomsel.kezbek.util;

public class CommonUtil {

	public static final Double calculateFinalAmount(Double amount, Double cashbackTiering, Double cashbackQty) {
		return amount - (amount * cashbackQty / 100) - cashbackTiering;
	}

}
