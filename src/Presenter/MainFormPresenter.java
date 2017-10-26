/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presenter;

import Draw.DrawDoThi;
import Draw.DrawDoThi.Flag;
import Draw.ShapeLine;
import Draw.ShapePoint;
import Model.MaTran.Mode;
import View.IMainForm;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class MainFormPresenter implements IMainFormPresenter {

    private IMainForm mView;

    // Co Huong || Vo Huong
    private Mode mMode = Mode.CoHuong;
    private int stepIndicator = 0;

    public MainFormPresenter(IMainForm view) {
        this.mView = view;
    }

    @Override
    public void onSelectedDoThiCoHuong() {
        stepIndicator = 0;
        mMode = Mode.CoHuong;
        mView.updateDoThiCoHuong();
    }

    @Override
    public void onSelectedDoThiVoHuong() {
        stepIndicator = 0;
        mMode = Mode.VoHuong;
        mView.updateDoThiVoHuong();
    }

    @Override
    public void onSelectedDoThiTuVe() {
        stepIndicator = 0;
        mView.updateDoThiTuVe();
    }

    @Override
    public void onSelectedDoThiCoSan() {
        stepIndicator = 0;
        mView.updateDoThiCoSan(mMode);
    }

    @Override
    public void onSelectedDuongDiMoi() {
        stepIndicator = 0;
        mView.clearViewOfOldRoad();
    }

    @Override
    public void onSelectedDoThiCoSanPosition(int position) {
        stepIndicator = 0;
        if (position >= 0) {
            mView.updateDoThiCoSanPosition(mMode, position);
        }
    }

    @Override
    public void onSelectedChayMotLan() {
        stepIndicator = 0;
        boolean isValid = mView.checkValidBeforeExecute();
        if (isValid) {
            mView.executeOneTime();
        }
    }

    @Override
    public void onSelectedChayTungBuoc() {
        boolean isValid = mView.checkValidBeforeExecute();
        if (isValid) {
            stepIndicator++;
            mView.executePerStep(stepIndicator);
        }
    }

    @Override
    public void onSelectedDiChuyen1Diem() {
        mView.setFlagDiChuyen1Diem();
    }

    @Override
    public void onSelectedNoi2Diem() {
        mView.setFlagNoi2Diem();
    }

    @Override
    public void onSelectedXoaDiem() {
        mView.setFlagXoaDiem();
    }

    @Override
    public void onSelectedThemDiem() {
        mView.setFlagThemDiem();
    }

    @Override
    public void onSelectedXoaDuongThang() {
        mView.setFlagXoaDuongThang();
    }

    @Override
    public void onSelectedThayDoiGiaTri() {
        mView.setFlagThayDoiGiaTri();
    }

    @Override
    public void onCallBackUpdatedFromDrawDoThi(DrawDoThi.Flag flagUpdate, ArrayList<ShapePoint> listShapePoints, ArrayList<ShapeLine> listShapeLines) {
        if (flagUpdate == Flag.DI_CHUYEN_1_DIEM) {
            mView.updateViewAndDataWithActionDiChuyen1Diem(listShapePoints, listShapeLines);
        } else if (flagUpdate == Flag.NOI_2_DIEM) {
            mView.updateViewAndDataWithActionNoi2Diem(listShapePoints, listShapeLines);
        } else if (flagUpdate == Flag.XOA_DIEM) {
            mView.updateViewAndDataWithActionXoaDiem(listShapePoints, listShapeLines);
        } else if (flagUpdate == Flag.THEM_DIEM) {
            mView.updateViewAndDataWithActionThemDiem(listShapePoints, listShapeLines);
        } else if (flagUpdate == Flag.XOA_DUONG_THANG) {
            mView.updateViewAndDataWithActionXoaDuongThang(listShapePoints, listShapeLines);
        } else if (flagUpdate == Flag.THAY_DOI_GIA_TRI) {
            mView.updateViewAndDataWithActionThayDoiGiaTri(listShapePoints, listShapeLines);
        }
    }

    @Override
    public void onUpdatedValueOfLineOnTableMaTran(String value, int pointStart, int pointEnd) {
        mView.updateValueOfLineOnTableMaTran(value, pointStart, pointEnd);
    }
}
