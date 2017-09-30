/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presenter;

/**
 *
 * @author DELL
 */
public interface IMainFormPresenter {
    public void onSelectedDoThiCoHuong(); 
    
    public void onSelectedDoThiVoHuong(); 
    
    public void onSelectedDoThiTuVe() ;
    
    public void onSelectedDoThiCoSan(); 
    
    public void onSelectedDoThiCoSanPosition(int position);
    
    public void onSelectedChayMotLan();
}
