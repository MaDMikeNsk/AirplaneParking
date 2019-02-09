/* [M5_L3] Реализовать эмулятор стоянки самолётов ­ стэк. Размер стоянки ­ 5 мест. Работать должно следующим образом:
вводим в консоль бортовые номера самолётов, и программа их запоминает (“ставит” их на стоянку).
При вводе команды “exitAll” программа должна распечатать номера самолётов в порядке покидания стоянки
(и удалить их все из памяти). При вводе “exitLast” ­ должна распечатать и удалить из памяти только тот самолёт,
который сейчас выезжает. Если стоянка заполнена полностью, следующему самолёту должно быть отказано во въезде.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    private static void exitLast (ArrayList<String> array) {
        if (!array.isEmpty()) {
            System.out.println("Самолёт с номером " + array.get(array.size()-1) + " покинул стоянку");
            array.remove(array.get(array.size()-1));
        } else {
            System.out.println("Стоянка пуста");
        }
    }

    private static void exitAll (ArrayList<String> array) {
        if (!array.isEmpty()) {
            for (int i = array.size() - 1; i>=0; i--) {
                System.out.println("Самолёт с номером " + array.get(i) + " покидает стоянку");
                array.remove(i);
            }
        } else {
            System.out.println("Стоянка пуста");
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String inputStream = "";
        ArrayList <String> aircraftParkingList = new ArrayList<>();
        String regex = "[A-Z\\-0-9]+|exitAll|exitLast";
        final int  PARKING_SIZE = 5;

        while (!inputStream.equals("STOP")){
            do {
                System.out.println("Введите бортовой номер самолёта или команду:");
                inputStream = reader.readLine().trim();
                if (!inputStream.matches(regex)) {
                    System.out.println("Неверный формат ввода");
                }
            } while (!inputStream.matches(regex));

            if (inputStream.equals("exitAll")) {
                exitAll(aircraftParkingList);
            } else if(inputStream.equals("exitLast")) {
                exitLast(aircraftParkingList);
            } else if (!inputStream.equals("STOP") && aircraftParkingList.size()!=5){
                aircraftParkingList.add(inputStream);
                System.out.println("Самолёт с номером " + inputStream + " добавлен на стоянку");
            } else if (aircraftParkingList.size()==PARKING_SIZE) {
                System.out.println("На стоянке нет свободных мест, ожидайте...");
            } else {
                System.out.println("Программа завершила работу. До свидания!");
            }
        }
    }
}