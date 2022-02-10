import java.util.LinkedList;
import java.util.Scanner;


public class Main {


    static void calculateFirst(LinkedList linkedList, Integer index, String operator) {
        Double a = (Double) linkedList.get(index - 1);
        Double b = (Double) linkedList.get(index);

        switch (operator) {
            case "*":

                linkedList.set(index, a * b);
                linkedList.remove(index - 1);
                break;
            case "/":
                if (b == 0) {
                    System.out.println("Dzielenie przez zero kończymy działania...");
                    return;
                } else {
                    linkedList.set(index, a / b);

                }
                linkedList.remove(index - 1);
                break;
            case "-":
                linkedList.set(index, b * (-1));
                break;
        }
    }

    static double finalCalculate(LinkedList linkedList) {
        double sum = 0;
        for (int i = 0; i < linkedList.size(); i++) {
            sum = sum + (Double) linkedList.get(i);
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println("Wprowadź ciąg liczb i działań (np 2+2*2):");
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();

        if (text.isBlank()) {
            System.out.println("pusty wpis, kończymy działanie");
            return;
        }

        String[] operators = text.split("(\\d+\\.\\d+)|\\d+");
        String[] numbersStr = text.split("\\+|\\-|\\*|\\/");
        if (operators.length != numbersStr.length) {
            System.out.println("Wprowadzono nieprawidłowy ciąg liczb i działań. Program kończy pracę.");
            return;
        }
        LinkedList<Double> linkedResults = new LinkedList<>();
        LinkedList<String> linkedOperators = new LinkedList<>();
        for (int j = 0; j < numbersStr.length; j++) {
            try {
                linkedResults.add(j, Double.parseDouble(numbersStr[j]));
            } catch (NumberFormatException e) {
                System.out.println("Wykryto zły znak");
                return;
            }
            linkedOperators.add(j, operators[j]);

        }
        Integer index = 0;
        while (linkedResults.size() > index) {
            if (linkedOperators.size() != linkedResults.size()) {
                System.out.println("dzielenie przez zero.");
                return;
            } else {
                if (linkedOperators.get(index).contains("*")) {
                    calculateFirst(linkedResults, index, "*");
                    linkedOperators.set(index, "..");
                }
                if (linkedOperators.get(index).contains("/")) {
                    calculateFirst(linkedResults, index, "/");
                    linkedOperators.set(index, "..");
                }
                if (linkedOperators.get(index).contains("-")) {
                    calculateFirst(linkedResults, index, "-");
                    linkedOperators.set(index, "+");
                }
                index++;
                if (index >= 2 && linkedOperators.get(index - 1).contains("..")) {
                    linkedOperators.remove(index - 1);
                    index--;
                }
            }

        }
        double finalCalculate = finalCalculate(linkedResults);
        System.out.println("Result: " + finalCalculate);

    }


}
