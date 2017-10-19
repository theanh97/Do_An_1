/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Draw.MShape;
import Draw.ShapeLine;
import Draw.ShapePoint;
import Model.Line;
import Model.MPoint;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author DELL
 */
public class MaTranUtils {

    public static ArrayList<MPoint> convertListVariableToListPoint(Object[][] listVariables,
            Point[] coordinate) {
        ArrayList<MPoint> listPoints = new ArrayList<MPoint>();
        int size = listVariables.length;
        for (int i = 0; i < size; i++) {
            ArrayList<Line> listLines = new ArrayList<Line>();
            for (int j = 0; j < size; j++) {
                int value = (int) listVariables[i][j];
                Line line = new Line(i + 1, j + 1, value);
                listLines.add(line);
            }
            MPoint point = new MPoint(i + 1, coordinate[i], listLines);
            listPoints.add(point);
        }
        return listPoints;
    }

    public static Object[][] convertListPointsToListVariables(ArrayList<MPoint> listPoints) {
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

    public static int[][] convertListObjectToListInt(Object[][] listObject) {
        int size = listObject.length;
        int[][] listInt = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                listInt[i][j] = (int) listObject[i][j];
            }
        }
        return listInt;
    }

    public static ArrayList<MShape> convertListDatasToListShape(ArrayList<MPoint> listDatas, boolean isCoHuong) {
        HashMap<Integer, MPoint> listPoint = new HashMap<Integer, MPoint>();

        for (MPoint point : listDatas) {
            listPoint.put(point.getIndicator(), point);
        }

        ArrayList<MShape> list = new ArrayList<MShape>();
        for (MPoint point : listDatas) {
            // shape point
            ShapePoint sp = new ShapePoint(point.getOriginPoint(), point.getIndicator(), false);
            list.add(sp);
            // shape line 
            for (Line line : point.getListLine()) {
                if (line.getValue() != 0) {
                    ShapeLine sl = new ShapeLine(
                            point.getOriginPoint(),
                            listPoint.get(line.getEndIndicator()).getOriginPoint(),
                            line.getValue(),
                            isCoHuong,
                            false,
                            line.getStartIndicator(),
                            line.getEndIndicator());
                    list.add(sl);
                }
            }
        }
        return list;
    }

    public static ArrayList<MPoint> convertListShapeToListPoint(ArrayList<ShapePoint> listShapePoints,
            ArrayList<ShapeLine> listShapeLines) {
        ArrayList<MPoint> listPoints = new ArrayList<MPoint>();

        // convert thành MPoint
        for (ShapePoint sp : listShapePoints) {
            int indicator = sp.getIndicator();
            Point originPoint = sp.getOriginPoint();
            ArrayList<Line> listLines = new ArrayList<Line>();

            // convert thành Line
            // Khởi tạo mặc định tất cả line có value = 0 
            for (int i = 0; i < listShapePoints.size(); i++) {
                int finishIndicator = listShapePoints.get(i).getIndicator();
                Line line = new Line(indicator, finishIndicator, 0);
                listLines.add(line);
            }

            // Tìm những line có value và gán lại cho nó đúng value 
            for (ShapeLine sl : listShapeLines) {
                for (Line line : listLines) {
                    if (sl.getStartIndicator() == line.getStartIndicator()
                            && sl.getFinishIndicator() == line.getEndIndicator()) {
                         line.setValue(sl.getValue());
                    }
                }
            }

            MPoint mPoint = new MPoint(indicator, originPoint, listLines);
            listPoints.add(mPoint);
        }

        return listPoints;
    }
}
