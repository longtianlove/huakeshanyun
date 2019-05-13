package com.stys.ipfs.test.net;

import org.bitcoinj.core.Coin;

import com.stys.ipfs.test.OmniIndivisibleValue;

/**
 * Definitions for MoneyMan address functionality on TestNet and RegTest
 */
public class MoneyMan {
	public static final long willetsPerSatoshi = 100; // Exchange rate for MoneyMan: 100 Omni per BTC

	/**
	 * Calculate Omni returned in a MoneyMan exchange.
	 *
	 * @param bitcoin An amount of bitcoin
	 * @return the amount of Omni the MoneyMan will exchange it for
	 */
	public static OmniIndivisibleValue toOmni(Coin bitcoin) {
		return OmniIndivisibleValue.ofWillets(bitcoin.value * willetsPerSatoshi);
	}
}
