/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Draw.ShapeLine;
import Draw.ShapePoint;
import Model.MaTran;
import Model.MaTran.Mode;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public interface IMainForm {

    public void setEventListener();

    public void initData();

    public void prepareUI();

    public void updateDoThiCoHuong();

    public void updateDoThiVoHuong();

    public void updateDoThiTuVe();

    public void updateDoThiCoSan(Mode mode);

    public void updateDoThiCoSanPosition(Mode mode, int position);

    public void clearViewOfOldRoad();

    public boolean checkValidBeforeExecute();

    public void executeOneTime();

    public void executePerStep(int stepIndicator);

    public void showDataWithStepIndicator(int startPoint, int finishPoint, int stepIndicator, int maxStep, int soDinh);

    public void setFlagDiChuyen1Diem();

    public void setFlagNoi2Diem();

    public void setFlagXoaDiem();

    public void setFlagThemDiem();

    public void setFlagXoaDuongThang();

    public void setFlagThayDoiGiaTri();

    public void updateViewAndDataWithActionDiChuyen1Diem(ArrayList<ShapePoint> listShapePoints,
            ArrayList<ShapeLine> listShapeLines);

    public void updateViewAndDataWithActionNoi2Diem(ArrayList<ShapePoint> listShapePoints,
            ArrayList<ShapeLine> listShapeLines);

    public void updateViewAndDataWithActionXoaDiem(ArrayList<ShapePoint> listShapePoints,
            ArrayList<ShapeLine> listShapeLines);

    public void updateViewAndDataWithActionThemDiem(ArrayList<ShapePoint> listShapePoints,
            ArrayList<ShapeLine> listShapeLines);

    public void updateViewAndDataWithActionXoaDuongThang(ArrayList<ShapePoint> listShapePoints,
            ArrayList<ShapeLine> listShapeLines);

    public void updateViewAndDataWithActionThayDoiGiaTri(ArrayList<ShapePoint> listShapePoints,
            ArrayList<ShapeLine> listShapeLines);

    public void updateValueOfLineOnTableMaTran(String newValue, int pointStartPosition, int pointEndPosition);
    
    public void handleWithActionChayTuDong();
}
