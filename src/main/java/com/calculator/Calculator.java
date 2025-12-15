package com.calculator;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("계산기 프로그램입니다. 수식을 입력하세요 (예: 10 + 5.5)");
        System.out.println("종료하려면 'exit' 또는 'quit'를 입력하세요.");
        
        while (true) {
            System.out.print("\n수식 입력: ");
            String input = scanner.nextLine().trim();
            
            if (input.equalsIgnoreCase("exit") || input.equalsIgnoreCase("quit")) {
                System.out.println("계산기를 종료합니다.");
                break;
            }
            
            if (input.isEmpty()) {
                continue;
            }
            
            try {
                double result = evaluate(input);
                System.out.println("결과: " + result);
            } catch (Exception e) {
                System.out.println("오류: " + e.getMessage());
            }
        }
        
        scanner.close();
    }
    
    public static double evaluate(String expression) throws IllegalArgumentException {
        expression = expression.trim();
        
        // 패턴: 숫자 연산자 숫자 (공백 선택적)
        Pattern pattern = Pattern.compile("^\\s*(-?(?:\\d+\\.\\d*|\\d*\\.\\d+|\\d+))\\s*([+\\-*/])\\s*(-?(?:\\d+\\.\\d*|\\d*\\.\\d+|\\d+))\\s*$");
        Matcher matcher = pattern.matcher(expression);
        
        if (!matcher.matches()) {
            throw new IllegalArgumentException("잘못된 수식 형식입니다. '숫자 연산자 숫자' 형식으로 입력하세요.");
        }
        
        double operand1 = Double.parseDouble(matcher.group(1));
        String operator = matcher.group(2);
        double operand2 = Double.parseDouble(matcher.group(3));
        
        return calculate(operand1, operator, operand2);
    }
    
    public static double calculate(double operand1, String operator, double operand2) {
        return switch (operator) {
            case "+" -> add(operand1, operand2);
            case "-" -> subtract(operand1, operand2);
            case "*" -> multiply(operand1, operand2);
            case "/" -> divide(operand1, operand2);
            default -> throw new IllegalArgumentException("지원하지 않는 연산자입니다: " + operator);
        };
    }
    
    public static double add(double a, double b) {
        return a + b;
    }
    
    public static double subtract(double a, double b) {
        return a - b;
    }
    
    public static double multiply(double a, double b) {
        return a * b;
    }
    
    public static double divide(double a, double b) {
        if (Math.abs(b) < 1e-10) {
            throw new ArithmeticException("0으로 나눌 수 없습니다.");
        }
        return a / b;
    }
}
