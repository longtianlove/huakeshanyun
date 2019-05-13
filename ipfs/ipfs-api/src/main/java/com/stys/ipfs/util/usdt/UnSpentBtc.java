package com.stys.ipfs.util.usdt;

public class UnSpentBtc {
    private String tx_hash;
    private long tx_output_n;
    private long tx_output_n2;
    private long value;
    private long confirmations;
    private String scriptPubKey;

    public String getScriptPubKey() {
        return scriptPubKey;
    }

    public void setScriptPubKey(String scriptPubKey) {
        this.scriptPubKey = scriptPubKey;
    }

    public String getTx_hash() {
        return tx_hash;
    }

    public void setTx_hash(String tx_hash) {
        this.tx_hash = tx_hash;
    }

    public long getTx_output_n() {
        return tx_output_n;
    }

    public void setTx_output_n(long tx_output_n) {
        this.tx_output_n = tx_output_n;
    }

    public long getTx_output_n2() {
        return tx_output_n2;
    }

    public void setTx_output_n2(int tx_output_n2) {
        this.tx_output_n2 = tx_output_n2;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public long getConfirmations() {
        return confirmations;
    }

    public void setConfirmations(long confirmations) {
        this.confirmations = confirmations;
    }

    @Override
    public String toString() {
        return "UnSpentBtc{" +
                "tx_hash='" + tx_hash + '\'' +
                ", tx_output_n='" + tx_output_n + '\'' +
                ", tx_output_n2='" + tx_output_n2 + '\'' +
                ", value='" + value + '\'' +
                ", confirmations='" + confirmations + '\'' +
                '}';
    }
}
