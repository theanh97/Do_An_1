/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Model.Line;
import Model.MPoint;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class Const {

    // Đồ thị Có Sẵn 
    public static String[] LIST_DO_THI
            = new String[]{"Đồ thị 1", "Đồ thị 2"};
    
    public static Object[][] DO_THI_1 = {
        {0, 2, 0, 0, 0},
        {0, 0, 0, 7, 0},
        {26, 22, 0, 0, 0},
        {0, 0, 16, 0, 8},
        {0, 0, 5, 0, 0}
    };
    
    public static Point [] DO_THI_1_COORDINATE = {
        new Point(50,50),
        new Point(50,200),
        new Point(150,50),
        new Point(150,200),
        new Point(250,135),
    };

    public static Object[][] DO_THI_2 = {
        {0, 7, 0, 0, 0, 0, 0},
        {0, 0, 8, 0, 0, 0, 0},
        {0, 0, 0, 0, 5, 0, 0},
        {5, 9, 0, 0, 15, 6, 0},
        {0, 7, 0, 0, 0, 8, 9},
        {0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 11, 0}
    };
    
     public static Point [] DO_THI_2_COORDINATE = {
        new Point(50,50),
        new Point(50,200),
        new Point(150,50),
        new Point(150,200),
        new Point(250,50),
        new Point(250,200),
        new Point(350,275),
    };
     
     // Draw 
     public static Color COLOR_POINT_NORMAL = Color.BLUE;
     public static Color COLOR_LINE_NORMAL = Color.BLUE;
     public static Color COLOR_POINT_SELECTED = Color.RED;
     public static Color COLOR_LINE_SELECTED = Color.RED;
     public static Color COLOR_POINT_INDICATOR = Color.GREEN;
     public static int POINT_RADIUS = 35 ;
     public static int X_POINT_INDICATOR = 14; 
     public static int Y_POINT_INDICATOR = 20; 
}
