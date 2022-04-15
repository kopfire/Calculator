package kata.test;

import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            if (input.equals("exit"))
                break;
            String[] arrayInput = Arrays.stream(input.split("[+*\\-/]")).map(String::trim).toArray(String[]::new);
            if (arrayInput.length != 2)
                throw new Exception("Invalid input");
            if (arrayInput[0].matches("\\d+") && arrayInput[1].matches("\\d+"))
                System.out.println(math(input, Integer.parseInt(arrayInput[0]), Integer.parseInt(arrayInput[1])));
            else {
                int x = toArabian(arrayInput[0]);
                int y = toArabian(arrayInput[1]);
                if (x == -1 || y == -1)
                    throw new Exception("Invalid input");

                int result = math(input, x, y);
                if (result < 0)
                    throw new Exception("Invalid output");
                System.out.println(toRoman(result));
            }
        }
    }

    public static String toRoman(int arabian) {
        StringBuilder output = new StringBuilder();
        Map<Integer, String> dictionary = new TreeMap<>(Collections.reverseOrder());
        {
            dictionary.put(100, "C");
            dictionary.put(90, "XC");
            dictionary.put(50, "L");
            dictionary.put(40, "XL");
            dictionary.put(10, "X");
            dictionary.put(9, "IX");
            dictionary.put(5, "V");
            dictionary.put(4, "IV");
            dictionary.put(1, "I");
        }
        for (int i : dictionary.keySet()) {
            output.append(dictionary.get(i).repeat(arabian / i));
            arabian -= arabian / i * i;
        }
        return output.toString();
    }

    public static int toArabian(String roman) {
        int number = 0;
        for (char i : roman.toCharArray()) {
            if (i == 'I') {
                number++;
                continue;
            } else if (number == 1)
                number -= 2;
            if (i == 'V')
                number += 5;
            else if (i == 'X')
                number += 10;
            else
                return -1;
        }
        return number;
    }

    public static int math(String input, int x, int y) {
        if (input.contains("*"))
            return x * y;
        if (input.contains("-"))
            return x - y;
        if (input.contains("/"))
            return x / y;
        return x + y;
    }
}
