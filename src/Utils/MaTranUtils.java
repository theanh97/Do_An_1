/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Model.Line;
import Model.Point;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author DELL
 */
public class MaTranUtils {

    public static ArrayList<Point> convertListVariableToListPoint(Object[][] listVariables) {
        ArrayList<Point> listPoints = new ArrayList<Point>();
        int size = listVariables.length;
        for (int i = 0; i < size; i++) {
            ArrayList<Line> listLines = new ArrayList<Line>();
            for (int j = 0; j < size; j++) {
                int value = (int) listVariables[i][j];
                Line line = new Line(i + 1, j + 1, value);
                listLines.add(line);
            }
            Point point = new Point(i + 1, listLines);
            listPoints.add(point);
        }
        return listPoints;
    }

    public static Object[][] convertListPointsToListVariables(ArrayList<Point> listPoints) {
        int size = listPoints.size();
        Object[][] listVariables = new Object[size][size];
        for (int i = 0; i < size; i++) {
            ArrayList<Line> listLines = listPoints.get(i).getListLine();
            for (int j = 0; j < size; j++) {
                listVariables[i][j] = listLines.get(j).getValue();
            }
        }

        return listVariables;
    }
    
    public static int[][] convertListObjectToListInt(Object[][] listObject){
        int size = listObject.length;
        int[][] listInt = new int[size][size]; 
        for(int i =0 ; i< size; i++){
            for(int j =0; j< size; j++){
                listInt[i][j] = (int) listObject[i][j];
            }
        }
        return listInt; 
    }
}
