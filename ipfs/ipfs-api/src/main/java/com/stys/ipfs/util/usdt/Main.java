package com.stys.ipfs.util.usdt;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Main {

	public static void main(String[] args) throws Throwable {

		/*String result1 = CoinUtils.getInstance().send("1CY4NHC4CVNk5RJ4fvYQvjRCUY9UbrUyeL",
				"1C1BtSbD8RZfY1Neg6UJ3mvVPSoH55kGcU", 31, 0.11, "1C1BtSbD8RZfY1Neg6UJ3mvVPSoH55kGcU");
		System.out.println(result1);*/
		//CoinUtils.getInstance().getBalance("1C1BtSbD8RZfY1Neg6UJ3mvVPSoH55kGcU").
	}

	public static void test3() {

		try {
			ArrayList<LinkedHashMap<?, ?>> arr = CoinUtils.getInstance()
					.listtransactions("1LyjyYkq1gnGB58CLDiHVtMhYqLBGa5NRu");

			System.out.println(arr);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public static void test2() {

		try {
			String result1 = CoinUtils.getInstance().send("1C1BtSbD8RZfY1Neg6UJ3mvVPSoH55kGcU",
					"1CY4NHC4CVNk5RJ4fvYQvjRCUY9UbrUyeL", 31, 0.01, "1C1BtSbD8RZfY1Neg6UJ3mvVPSoH55kGcU");

			System.out.println(result1);

		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	private static void test1() throws Throwable {
		// 查询钱包的余额
		ArrayList<LinkedHashMap<?, ?>> walletaddressbalances = CoinUtils.getInstance().getwalletaddressbalances();

		for (LinkedHashMap<?, ?> linkedHashMap : walletaddressbalances) {
			String address = (String) linkedHashMap.get("address");
			ArrayList<LinkedHashMap<?, ?>> arr = CoinUtils.getInstance().listtransactions(address);
			for (LinkedHashMap<?, ?> linkedHashMap2 : arr) {
				System.out.println("---------------------------------");
				System.out.println(linkedHashMap2.toString());
				String txid = (String) linkedHashMap2.get("txid");
				String referenceaddress = (String) linkedHashMap2.get("referenceaddress");
				Integer confirmations = (Integer) linkedHashMap2.get("confirmations");
				String f = (String) linkedHashMap2.get("amount");
				System.out.println(txid);
				System.out.println(referenceaddress);
				System.out.println(confirmations);

				System.out.println(f);

			}

			System.out.println(address);
		}
	}
}