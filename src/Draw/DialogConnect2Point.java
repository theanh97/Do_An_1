/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Draw;

import Model.Line;
import Model.MPoint;
import Utils.MaTranUtils;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author DELL
 */
public class DialogConnect2Point extends JDialog {

    JFrame mFrame;
    CallBackToDrawDoThi mCallBackToDrawDoThi;
    Container cp;
    Point mSelectedPointPosition; // vị trí tạo dialog
    JComboBox<Integer> cmbPointConnected;
    JFormattedTextField txtValue;
    JButton btnYes;
    JButton btnCancel;
    ArrayList<ShapePoint> mListPoint;
    ArrayList<ShapeLine> mListLine;
    ShapePoint mStartPoint;
    ShapePoint mFinishPoint;

    public DialogConnect2Point(Point selectedPointPosition, JFrame frame, DrawDoThi ddt,
            ArrayList<ShapePoint> listPoints, ArrayList<ShapeLine> listLines, ShapePoint startPoint) {
        super(frame, "Nối 2 điểm", true);
        this.mSelectedPointPosition = selectedPointPosition;
        this.mFrame = frame;
        mCallBackToDrawDoThi = ddt;

        this.mListPoint = listPoints;
        this.mListLine = listLines;
        this.mStartPoint = startPoint;
        initAndAddView();
        configForDialog();
        setEvent();
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
        txtValue.setColumns(6);

        txtValue.setValue(1);
        // bôi đen giá trị ( select ) 
        txtValue.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        txtValue.selectAll();
                    }
                });
            }
        });
    }

    public void setEvent() {
        txtValue.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e); //To change body of generated methods, choose Tools | Templates.
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    int finishIndicator = (int) cmbPointConnected.getSelectedItem();
                    for (ShapePoint point : mListPoint) {
                        if (point.getIndicator() == finishIndicator) {
                            mFinishPoint = point;
                        }
                    }
                    Integer value = Integer.parseInt(txtValue.getValue().toString());
                    mCallBackToDrawDoThi.callBackAddNewLine(value, mStartPoint, mFinishPoint);
                    setVisible(false);
                }
            }
        }
        );
        // yes 
        btnYes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int finishIndicator = (int) cmbPointConnected.getSelectedItem();
                for (ShapePoint point : mListPoint) {
                    if (point.getIndicator() == finishIndicator) {
                        mFinishPoint = point;
                    }
                }
                Integer value = Integer.parseInt(txtValue.getValue().toString());
                mCallBackToDrawDoThi.callBackAddNewLine(value, mStartPoint, mFinishPoint);
                setVisible(false);
            }
        });
        // cancel  
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        // close <=> cancel
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e); //To change body of generated methods, choose Tools | Templates.
                setVisible(false);
            }
        });

    }

    void initAndAddView() {
        cp = getContentPane();
        cp.setLayout(new FlowLayout());

        JLabel lblNoiTu = new JLabel("   Nối từ : " + mStartPoint.getIndicator() + "   Đến : ");
        cp.add(lblNoiTu);

        cmbPointConnected = new JComboBox<Integer>();
        Vector<Integer> comboBoxItems = new Vector<Integer>();
        ArrayList<Integer> listPointNotConnected = MaTranUtils.getListPointCanConnected(mStartPoint.getIndicator(), mListPoint, mListLine);
        for (Integer i : listPointNotConnected) {
            comboBoxItems.add(i);
//            cmbPointConnected.addItem(i);
        }
        DefaultComboBoxModel model = new DefaultComboBoxModel(comboBoxItems);
        cmbPointConnected.setModel(model);

        cp.add(cmbPointConnected);

        cp.add(new JLabel("\n    Giá trị : "));

        formatTextFieldValue();
        cp.add(txtValue);

        btnYes = new JButton("Đồng ý");
        cp.add(btnYes);

        btnCancel = new JButton("Huỷ bỏ");
        cp.add(btnCancel);
    }

    void configForDialog() {
        setSize(220, 170);
        setLocation(mSelectedPointPosition.x + 170,
                mSelectedPointPosition.y + 85);
        getRootPane().setWindowDecorationStyle(JRootPane.INFORMATION_DIALOG);
    }
}
