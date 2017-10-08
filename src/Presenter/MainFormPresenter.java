/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presenter;

import Model.MaTran.Mode;
import View.IMainForm;

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
        mView.updateDoThiCoHuong();
        mMode = Mode.CoHuong;
    }

    @Override
    public void onSelectedDoThiVoHuong() {
        mView.updateDoThiVoHuong();
        mMode = Mode.VoHuong;
        stepIndicator = 0;
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

}
