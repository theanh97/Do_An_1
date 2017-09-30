/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author DELL
 */
public class Point {

    /**
     * @return the indicator
     */
    public int getIndicator() {
        return indicator;
    }

    /**
     * @param indicator the indicator to set
     */
    public void setIndicator(int indicator) {
        this.indicator = indicator;
    }

    /**
     * @return the listLine
     */
    public ArrayList<Line> getListLine() {
        return listLine;
    }

    /**
     * @param listLine the listLine to set
     */
    public void setListLine(ArrayList<Line> listLine) {
        this.listLine = listLine;
    }
    private int indicator ;
    private ArrayList<Line> listLine;
    
    public Point(int indicator , ArrayList<Line>listLine){
        this.indicator = indicator; 
        this.listLine = listLine; 
    }
}
