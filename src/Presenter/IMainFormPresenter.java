/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presenter;

import Draw.DrawDoThi.Flag;
import Draw.ShapeLine;
import Draw.ShapePoint;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public interface IMainFormPresenter {

    public void onSelectedDoThiCoHuong();

    public void onSelectedDoThiVoHuong();

    public void onSelectedDoThiTuVe();

    public void onSelectedDoThiCoSan();

    public void onSelectedDuongDiMoi();

    public void onSelectedDoThiCoSanPosition(int position);

    public void onSelectedChayMotLan();

    public void onSelectedChayTungBuoc();

    public void onSelectedDiChuyen1Diem();

    public void onSelectedNoi2Diem();

    public void onSelectedXoaDiem();

    public void onSelectedThemDiem();
    
    public void onSelectedXoaDuongThang(); 
    
    public void onSelectedThayDoiGiaTri();

    public void onCallBackUpdatedFromDrawDoThi(Flag flagUpdate,
            ArrayList<ShapePoint> listShapePoints,
            ArrayList<ShapeLine> listShapeLines);

}
