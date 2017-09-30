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

    public MainFormPresenter(IMainForm view) {
        this.mView = view;
    }

    @Override
    public void onSelectedDoThiCoHuong() {
        mView.updateDoThiCoHuong();
        mMode = Mode.CoHuong;
    }

    @Override
    public void onSelectedDoThiVoHuong() {
        mView.updateDoThiVoHuong();
        mMode = Mode.VoHuong;
    }

    @Override
    public void onSelectedDoThiTuVe() {
        mView.updateDoThiTuVe();
    }

    @Override
    public void onSelectedDoThiCoSan() {
        mView.updateDoThiCoSan(mMode);
    }

    @Override
    public void onSelectedDoThiCoSanPosition(int position) {
        if (position >= 0) {
            mView.updateDoThiCoSanPosition(mMode, position);
        }
    }

    @Override
    public void onSelectedChayMotLan() {
        boolean isValid = mView.checkValidBeforeExecute(); 
        if(isValid){
            mView.executeOneTime();
        }
    }

}
