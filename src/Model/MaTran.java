/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Utils.MaTranUtils;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class MaTran {

    public MaTran(Mode mode, Object[][] listVariableCoHuong) {
        this.mode = mode;
        this.listVariableCoHuong = listVariableCoHuong;
        this.listPoints = MaTranUtils.convertListVariableToListPoint(listVariableCoHuong);
        getListVariablesVoHuongFromListVariablesCoHuong();
        createColumnsName();
    }

    /**
     * @return the listPoints
     */
    public ArrayList<Point> getListPoints() {
        return listPoints;
    }

    /**
     * @param listPoints the listPoints to set
     */
    public void setListPoints(ArrayList<Point> listPoints) {
        this.listPoints = listPoints;
    }

    /**
     * @return the listVariableVoHuong
     */
    public Object[][] getListVariableVoHuong() {
        return listVariableVoHuong;
    }

    /**
     * @param listVariableVoHuong the listVariableVoHuong to set
     */
    public void setListVariableVoHuong(Object[][] listVariableVoHuong) {
        this.listVariableVoHuong = listVariableVoHuong;
    }

    /**
     * @return the mode
     */
    public Mode isMode() {
        return mode;
    }

    /**
     * @param mode the mode to set
     */
    public void setMode(Mode mode) {
        this.mode = mode;
    }

    /**
     * @return the listVariableCoHuong
     */
    public Object[][] getlistVariableCoHuong() {
        return listVariableCoHuong;
    }

    /**
     * @param listVariableCoHuong the listVariableCoHuong to set
     */
    public void setlistVariableCoHuong(Object[][] listVariableCoHuong) {
        this.listVariableCoHuong = listVariableCoHuong;
    }

    private Mode mode;
    private ArrayList<Point> listPoints;
    private Object[][] listVariableCoHuong;
    private Object[][] listVariableVoHuong;
    private Object[] ColumnsName;

    public void createColumnsName() {
        int size = getListPoints().size();
        ColumnsName = new Object[size];
        for (int i = 0; i < size; i++) {
            ColumnsName[i] = getListPoints().get(i).getIndicator();
        }
    }

    public void getListVariablesVoHuongFromListVariablesCoHuong() {
        int size = getListPoints().size();
        listVariableVoHuong = new Object[size][size];
        // Copy 
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < getListPoints().size(); j++) {
                listVariableVoHuong[i][j] = listVariableCoHuong[i][j];
            }
        }

        // Convert 
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < getListPoints().size(); j++) {
                int value = (int) listVariableVoHuong[i][j];
                if (value != 0) {
                    listVariableVoHuong[j][i] = listVariableVoHuong[i][j];
                }
            }
        }
    }

    public Object[][] getListVariable() {
        if (mode == Mode.CoHuong) {
            return listVariableCoHuong;
        } else {
            return listVariableVoHuong;
        }
    }

    public void reverseDirection() {
        if (mode == Mode.CoHuong) {
            mode = Mode.VoHuong;
        } else {
            mode = Mode.CoHuong;
        }
    }

    public enum Mode {
        CoHuong,
        VoHuong
    }

    /**
     * @return the ColumnsName
     */
    public Object[] getColumnsName() {
        return ColumnsName;
    }

    /**
     * @param ColumnsName the ColumnsName to set
     */
    public void setColumnsName(Object[] ColumnsName) {
        this.ColumnsName = ColumnsName;
    }
}