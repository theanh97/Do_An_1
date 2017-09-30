/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.MaTran;
import Model.MaTran.Mode;

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
    
    public void updateDoThiCoSanPosition(Mode mode , int position);
    
    public boolean checkValidBeforeExecute();
    
    public void executeOneTime();
}
