/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import javafx.util.Pair;

/**
 *
 * @author DELL
 */
public class Dijkstra {

    static int[][] DO_THI = {
        {0, 7, 0, 0, 0, 0, 0},
        {0, 0, 8, 0, 0, 0, 0},
        {0, 0, 0, 0, 5, 0, 0},
        {5, 9, 0, 0, 15, 6, 0},
        {0, 7, 0, 0, 0, 8, 9},
        {0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 11, 0}
    };
    static int n = 7;

    public static void main(String[] args) {
        dijkstra(DO_THI, n, 0, 5);
    }

    public static ArrayList<DataOfTwoPointForOneStep> dijkstra(int[][] doThi, int soDinh, int start, int finish) {
        // giảm 1 
        start -= 1;
        finish -= 1;

        ArrayList<DataOfTwoPointForOneStep> listDataOfTwoPointsForOneStep = new ArrayList<DataOfTwoPointForOneStep>();
        boolean isSuccess = true;
        int firstStart = start;
        int[] back = new int[100]; // Lưu đỉnh cha
        int[] weight = new int[100]; // Lưu trọng số 
        int[] mark = new int[100]; // đánh dấu đỉnh 

        // Khởi tạo 
        for (int i = 0; i < soDinh; i++) {
            back[i] = -1;
            mark[i] = 0;
            weight[i] = Integer.MAX_VALUE;
        }

        back[start] = 0;
        weight[start] = 0;

        // kiểm tra đồ thị có liên thông hay ko 
        int connect = -1;
        do {
            // listValues of DataOfTwoPoints
            HashMap<Integer, Integer> listValues = new HashMap<Integer, Integer>();
            int startPoint;
            int finishPoint;
            int beforeValue;
            int currentValue;
            ArrayList<Integer> listPointMarked = new ArrayList<Integer>();

            // đỉnh bắt đầu là đỉnh 0 => gán connect = -1 
            connect = -1;
            int min = Integer.MAX_VALUE;
            // duyệt qua các đỉnh trên đồ thị
            System.out.println("\t---- Đỉnh Xuất Phát: " + (start + 1));
            startPoint = start + 1;

            beforeValue = weight[start];

            for (int j = 0; j < soDinh; j++) {
                // nếu đỉnh chưa được đánh dấu 
                if (mark[j] == 0) {

                    // lưu lại cặp giá trị ( đỉnh đang thử nối - độ dài đường đi đến điểm đó ) 
                    int pointConnected = j + 1;
                    int length = weight[start] + doThi[start][j];

                    listValues.put(pointConnected, length);

                    // nếu tồn tại đường đi giữa đỉnh i và j
                    // weight[j] : tổng trọng số từ đỉnh bắt đầu -> đỉnh đang xét 
                    //  weight[start] + DO_THI[start][j] : trọng số đang xét
                    System.out.println("\tĐỉnh nối : " + (j + 1) + " --- Value : " + (weight[start] + doThi[start][j]));

                    if (doThi[start][j] != 0
                            && weight[j] > weight[start] + doThi[start][j]) {
                        // lưu lại dùng để so sánh lần sau 
                        weight[j] = weight[start] + doThi[start][j];
                        back[j] = start;
                    }

                    // dựa vào mảng weight -> tìm đường đi ngắn nhất hiện tại 
                    if (min > weight[j]) {
                        min = weight[j];
                        // dựa vào biến connect 
                        // ta có thể quyết định điểm tiếp theo cần duyệt 
                        // và đổ thị có liên thông không 
                        connect = j;
                    }
                }
            }
            start = connect;
            System.out.println("Đỉnh nối được chọn : " + (start + 1));

            // thêm các đỉnh đi qua vào listPointMarked của DataOfTwoPointForOneStep
            for (int i = 0; i < n; i++) {
                if (mark[i] == 1) {
                    listPointMarked.add(i + 1);
                }
            }
            currentValue = weight[start];
//            System.out.println("Giá trị trước đó : " + beforeValue);
//            System.out.println("Giá trị Hiện tại: " + currentValue);

//            System.out.println("Các điểm đã đi qua : ");
//            for (int i = 0; i < listPointMarked.size(); i++) {
//                System.out.println(listPointMarked.get(i) + " - ");
//            }

            finishPoint = start + 1;
            // tạo DataTwoOfPoint 
            DataOfTwoPointForOneStep dotpfos = new DataOfTwoPointForOneStep(
                    startPoint, finishPoint, listValues, beforeValue, currentValue, listPointMarked);
            listDataOfTwoPointsForOneStep.add(dotpfos);
            if (start == -1) {
                isSuccess = false;
                break;
            }
            mark[start] = 1;
            
            for(int i =0 ; i< 10 ;i++ ){ 
                if(back[i] != 0){
                    System.out.println("\tWeight["+ (i+1)+"] : " + weight[i] );
                }
            }
            System.out.println("Tổng hiện Tại : " + weight[finish]);
        } while (connect != -1 && start != finish);

        // nếu có đường đi 
        if (isSuccess == true) {
            System.out.print((firstStart + 1) + " ->");
            printPath(firstStart, finish, back);
            System.out.println("\n" + weight[finish] + "\n");
            return listDataOfTwoPointsForOneStep;
        } // nếu ko có đường đi 
        else {
            return null;
        }
    }

    public static void printPath(int start, int finish, int back[]) {
        if (start != finish) {
            printPath(start, back[finish], back);
            System.out.print((finish + 1) + " -> ");
        }
    }
}
