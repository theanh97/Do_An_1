/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Draw;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author DELL
 */
public class DialogAddNewLine extends JDialog {

    Container cp;
    JFormattedTextField txtValue;
    JButton btnYes;
    JButton btnCancel;
    CallBackToDrawDoThi mCallBackToDrawDoThi;

    public DialogAddNewLine(JFrame frame, DrawDoThi ddt) {
        super(frame, "thiết lập giá trị", true);
        mCallBackToDrawDoThi = ddt;
        initAndAddView();
        setEvent();
        configForDialog();
    }

    public void formatTextFieldValue() {
        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Long.class);
        formatter.setMinimum(0l);
        formatter.setAllowsInvalid(false);

        // If you want the value to be committed on each keystroke instead of focus lost
        formatter.setCommitsOnValidEdit(true);
        txtValue = new JFormattedTextField(formatter);
        txtValue.setColumns(12);
        txtValue.setValue(1);
    }

    public void setEvent() {
        txtValue.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e); //To change body of generated methods, choose Tools | Templates.
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    mCallBackToDrawDoThi.callBackSetValueForLine(true, Integer.parseInt(txtValue.getValue().toString()));
                    setVisible(false);
                }
            }
        }
        );
        // yes 
        btnYes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mCallBackToDrawDoThi.callBackSetValueForLine(true, Integer.parseInt(txtValue.getValue().toString()));
                setVisible(false);
            }
        });
        // cancel  
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mCallBackToDrawDoThi.callBackSetValueForLine(false, 0);
                setVisible(false);

            }
        });
        // close <=> cancel
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e); //To change body of generated methods, choose Tools | Templates.
                mCallBackToDrawDoThi.callBackSetValueForLine(false, 0);
                setVisible(false);
            }
        });
    }

    void initAndAddView() {
        cp = getContentPane();
        cp.setLayout(new FlowLayout());
        formatTextFieldValue();
        btnYes = new JButton("Đồng ý");
        btnCancel = new JButton("Huỷ bỏ");
        cp.add(txtValue);
        cp.add(btnYes);
        cp.add(btnCancel);
    }

    void configForDialog() {
        setSize(230, 120);
        setLocationRelativeTo(null);
        getRootPane().setWindowDecorationStyle(JRootPane.INFORMATION_DIALOG);
    }
}
