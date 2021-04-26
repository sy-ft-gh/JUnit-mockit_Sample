package com.example.calc;

public class Calculate {
    private double sum;
    private double tax;

    public Calculate() {
        // 合計金額を0初期化
        setSummary(0);
    }
    //  合計金額を設定
    public void setSummary(int value) {
        final int minValue = 0;
        final int maxValue = 1000000;
        if (value < minValue || value  > maxValue) {
            throw new IllegalArgumentException("金額は" + minValue + "から" + maxValue + "の間で設定してください。" );
        }
        this.sum = value;
        setTax();
    }
    // 消費税計算
    private void setTax() {
        this.tax = Tax.calcTax(this.sum);
    }
    // 金額計算
    public void calculate(Operator ope, int value) {
        if (ope == null)
            throw new IllegalArgumentException("演算子が指定されていません。" );
        switch (ope) {
            case ADD:
                // 加算
                this.sum += value;
                break;
            case SUB:
                // 減算
                this.sum -= value;
                break;
            case MULT:
                // 乗算
                this.sum *= value;
                break;
            case DIV:
                // 割り算は計算時に四捨五入する
                this.sum /= value;
                break;
        }
        // 消費税を設定
        setTax();
    }
    // 合計に消費税を足して四捨五入し返却
    public long  getSummary() {
        return Math.round(this.sum + this.tax);
    }
}