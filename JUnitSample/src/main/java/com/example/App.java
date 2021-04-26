package com.example;

import com.example.calc.Calculate;
import com.example.calc.Operator;

public final class App {
    public static void main(String[] args) {
        Calculate calc = new Calculate();
        // 初期金額に100円を設定
        calc.setSummary(100);
        // 200を加算
        calc.calculate(Operator.ADD, 200);
        // 7で除算
        calc.calculate(Operator.DIV, 7);
        // 13で乗算
        calc.calculate(Operator.MULT, 13);
        // 200を減算
        calc.calculate(Operator.SUB, 200);
        long result = calc.getSummary();
        System.out.println("合計は" + result + "円です。");

    }
}
