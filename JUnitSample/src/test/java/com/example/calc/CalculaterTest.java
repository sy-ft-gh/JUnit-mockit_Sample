package com.example.calc;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.Instant;

import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.Matchers.*;
import org.mockito.Mockito.*;

import org.junit.Test;


public class CalculaterTest {

    @Test
    public void instanceInit() throws Exception {
        Class<?> clazz = Calculate.class;
        Constructor<?> cons = clazz.getConstructor();
        int mod = cons.getModifiers();
        // 引数無しコンストラクタが存在する事
        assertEquals(mod, 1);
        Calculate culc = (Calculate) cons.newInstance();
        Field f_sum = clazz.getDeclaredField("sum");
        f_sum.setAccessible(true);
        // sumが0となっている事
        assertEquals(f_sum.get(culc), 0.0);
        Field f_tax = clazz.getDeclaredField("tax");
        f_tax.setAccessible(true);
        // taxが0となっている事
        assertEquals(f_tax.get(culc), 0.0);
    } 

    @Test
    public void calctax() throws Exception {
        // staticメソッドのmockを実施
        try (MockedStatic<Tax>  mocked = Mockito.mockStatic(Tax.class)) {
            // 中だけモックが有効（staticメソッドの挙動を変更）
            // calcTaxの引数が2.0の時結果を0.1で返却する
            // Taxクラスのメソッドを介さず、mockにて注入するメソッドが実行される
            mocked.when(() -> Tax.calcTax(2.0)).thenReturn(0.1); 

            Class<?> clazz = Calculate.class;
            Constructor<?> cons = clazz.getConstructor();
            Calculate culc = (Calculate) cons.newInstance();
            Field f_sum = clazz.getDeclaredField("sum");
            // sumフィールドに2.0を設定
            f_sum.setAccessible(true);
            f_sum.set(culc, 2.0);
            Method m = clazz.getDeclaredMethod("setTax");
            m.setAccessible(true);
            // setTaxメソッドの呼び出し
            m.invoke(culc);
            // taxフィールドの確認
            Field f_tax = clazz.getDeclaredField("tax");
            f_tax.setAccessible(true);
            assertEquals(f_tax.get(culc) ,0.1);
        }
    }
}
