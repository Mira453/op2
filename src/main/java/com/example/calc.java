package com.example;
public class calc {
    public static double calcMyTask(double a, double b, double c, double d) throws IllegalArgumentException{
            if (Math.abs(Math.tan(a)) > 1e6) {
                throw new IllegalArgumentException("близьке значення до розриву функції тангенса");
        }
            if (b > 1 || b < -1){
                throw new IllegalArgumentException("b має бути в межах від -1 до 1");
    }
        //завдання 15 з лр2 першого семестру
        double y = 2 * Math.sqrt(Math.tan(a) / Math.abs(Math.acos(b)))
                - 3 * Math.cbrt(Math.exp(c - a) * Math.sinh(d));
        if(Double.isNaN(y) ){
            throw new IllegalArgumentException("помилка. NaN");
        }
        return y;
}
}