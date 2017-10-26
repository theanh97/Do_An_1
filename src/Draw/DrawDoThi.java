/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Draw;

import Model.MPoint;
import Utils.MaTranUtils;
import View.MainForm;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author DELL
 */
public class DrawDoThi extends JPanel
        implements CallBackToDrawDoThi {

    // mode của đồ thị
    private boolean isCoHuong;

    private DialogAddNewLine mDialog;
    private CallBackToMainForm mCallBackToMainForm;
    private ArrayList<ShapeLine> mListShapeLines;
    private ArrayList<ShapePoint> mListShapePoints;
    private Flag flag;
    // Di chuyển 1 điểm 
    private boolean mFlagOfMovingPoint = false;
    private int mIndicatorOfMovingPoint = -1;
    // Nối 2 điểm 
    private boolean mFlagOfConnectingPoint = false;
    private Point mOriginPoint;
    private ShapeLine mShapeLineConnectingPoint;

    public DrawDoThi(MainForm mainForm, ArrayList<MPoint> listDatas, boolean CoHuong) {
        mCallBackToMainForm = mainForm;
        mDialog = new DialogAddNewLine(mainForm, this);
        mDialog.setVisible(false);
        initShapeData(listDatas, CoHuong);
        flag = Flag.DI_CHUYEN_1_DIEM;

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e); //To change body of generated methods, choose Tools | Templates.
                Point clickedPoint = e.getPoint();

                // Di chuyển 1 điểm 
                if (flag == Flag.DI_CHUYEN_1_DIEM) {
                    int i = 0;
                    for (ShapePoint point : mListShapePoints) {
                        if (point.getShape().contains(clickedPoint)) {
                            mFlagOfMovingPoint = true;
                            mIndicatorOfMovingPoint = i;
                            break;
                        }
                        i++;
                    }
                }

                // Nối 2 điểm 
                if (flag == Flag.NOI_2_DIEM) {
                    for (ShapePoint point : mListShapePoints) {
                        if (point.getShape().contains(clickedPoint)) {
                            System.out.println("Có hướng : " + isCoHuong);
                            mFlagOfConnectingPoint = true;
                            mOriginPoint = point.getOriginPoint();
                            mShapeLineConnectingPoint = new ShapeLine(
                                    mOriginPoint,
                                    mOriginPoint,
                                    1,
                                    isCoHuong,
                                    false,
                                    point.getIndicator(),
                                    point.getIndicator());
                            break;
                        }
                    }
                }

                // Xoá điểm 
                if (flag == Flag.XOA_DIEM) {
                    for (ShapePoint point : mListShapePoints) {
                        if (point.getShape().contains(clickedPoint)) {
                            new DialogRemovePoint(mainForm, DrawDoThi.this, point.getIndicator()).setVisible(true);
                            break;
                        }
                    }
                }

                // Thêm điểm 
                if (flag == Flag.THEM_DIEM) {
                    // nếu click vào 1 Point khác => không hiển thị để thêm 
                    for (ShapePoint point : mListShapePoints) {
                        if (point.getShape().contains(clickedPoint)) {
                            return;
                        }
                    }
                    // nếu click vào chỗ trống ( không phải point ) 
                    // Tạo dialog để add vào 
                    new DialogAddNewPoint(mainForm, DrawDoThi.this, mListShapePoints, clickedPoint)
                            .setVisible(true);
                }

                // Xoá đường thẳng 
                if (flag == Flag.XOA_DUONG_THANG) {
                    for (ShapeLine line : mListShapeLines) {
                        if (line.getShape().contains(clickedPoint)) {
                            new DialogRemoveLine(mainForm, DrawDoThi.this, line)
                                    .setVisible(true);
                            break;
                        }
                    }
                }

                // Thay đổi giá trị 
                if (flag == Flag.THAY_DOI_GIA_TRI) {
                    // POINT
                    for (ShapePoint point : mListShapePoints) {
                        if (point.getShape().contains(clickedPoint)) {
                            new DialogChangeValue(mainForm, DrawDoThi.this, point, mListShapePoints, point)
                                    .setVisible(true);
                            return;
                        }
                    }
                    // LINE 
                    for (ShapeLine line : mListShapeLines) {
                        if (line.getShape().contains(clickedPoint)) {
                            new DialogChangeValue(mainForm, DrawDoThi.this, line, null, null)
                                    .setVisible(true);
                            return;
                        }
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e); //To change body of generated methods, choose Tools | Templates.
                Point releasedPoint = e.getPoint();
                // Di chuyển 1 điểm 
                if (flag == Flag.DI_CHUYEN_1_DIEM
                        && mFlagOfMovingPoint) {
                    // bỏ flag 
                    mFlagOfMovingPoint = false;
                    mIndicatorOfMovingPoint = -1;

                    // call back về mainform 
                    mCallBackToMainForm.callBackUpdatedFromDrawDoThi(
                            flag,
                            mListShapePoints,
                            mListShapeLines);
                } // Nối 2 điểm 
                else if (flag == Flag.NOI_2_DIEM
                        && mFlagOfConnectingPoint) {
                    // bỏ flag 
                    mFlagOfConnectingPoint = false;
                    // kiểm tra xem có có nối đến điểm nào không 
                    for (ShapePoint point : mListShapePoints) {
                        if (point.getShape().contains(releasedPoint)) {
                            int finishIndicator = point.getIndicator();
                            Point finishPoint = point.getOriginPoint();

                            mShapeLineConnectingPoint.setFinishIndicator(finishIndicator);
                            mShapeLineConnectingPoint.setFinishPoint(finishPoint);
                            // xử lý nối 2 điểm 
                            boolean isValid = checkConnectedOfTwoPointIsValid(
                                    mShapeLineConnectingPoint.getStartIndicator(),
                                    mShapeLineConnectingPoint.getFinishIndicator());
                            // hợp lệ -> có thể tạo kết nối mới 
                            if (isValid) {
                                mDialog.setVisible(true);
                                return;
                            }
                        }
                    }
                    // không hợp lệ
                    JOptionPane.showMessageDialog(null,
                            "Không thể tạo đường nối giữa 2 điểm !!!");
                    repaint();
                }
                System.out.println("Released");
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e); //To change body of generated methods, choose Tools | Templates.

                Point draggingPoint = e.getPoint();

                // Di chuyển 1 điểm 
                if (mFlagOfMovingPoint == true) {
                    ShapePoint sp = mListShapePoints.get(mIndicatorOfMovingPoint);
                    int draggingIndicator = sp.getIndicator();
                    // thay đổi position của point đang dragged 
                    sp.setOriginPoint(draggingPoint);
                    // thay đổi position của point trong list line  
                    for (ShapeLine line : mListShapeLines) {
                        if (line.getStartIndicator() == draggingIndicator) {
                            line.setOriginPoint(draggingPoint);
                        }
                        if (line.getFinishIndicator() == draggingIndicator) {
                            line.setFinishPoint(draggingPoint);
                        }
                    }

                    repaint();
                } // Nối 2 điểm 
                else if (mFlagOfConnectingPoint == true) {
                    mShapeLineConnectingPoint.setFinishPoint(draggingPoint);
                    repaint();
                }
            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g); //To change body of generated methods, choose Tools | Templates.
        draw(g);
    }

    public void draw(Graphics g) {
        g.clearRect(0, 0, getWidth(), getHeight());
        Graphics2D g2d = (Graphics2D) g;

        // draw line
        for (ShapeLine shape : mListShapeLines) {
            if (shape.getValue() > 0) {
                shape.paint(g2d);
            }
        }

        // draw line connecting 
        if (mFlagOfConnectingPoint
                && flag == Flag.NOI_2_DIEM) {
            mShapeLineConnectingPoint.paint(g2d);
        }

        // draw point 
        for (ShapePoint shape : mListShapePoints) {
            shape.paint(g2d);
        }
    }

    public void drawWithMode(ArrayList<MPoint> listDatas, boolean isCoHuong) {
        initShapeData(listDatas, isCoHuong);
        drawDoThiUnselected();
    }

    public void initShapeData(ArrayList<MPoint> listDatas, boolean isCoHuong) {
        this.isCoHuong = isCoHuong;
        mListShapeLines = new ArrayList<ShapeLine>();
        mListShapePoints = new ArrayList<ShapePoint>();
        ArrayList<MShape> listShapes = MaTranUtils.convertListDatasToListShape(listDatas, isCoHuong);
        for (MShape shape : listShapes) {
            if (shape instanceof ShapeLine) {
                mListShapeLines.add((ShapeLine) shape);
            }
            if (shape instanceof ShapePoint) {
                mListShapePoints.add((ShapePoint) shape);
            }
        }
    }

    public void drawRoad(ArrayList<Integer> road) {
        // xoá vết các đường đi trước 
        drawDoThiUnselected();

        // vẽ lại 
        int flag = -1;
        int pointBefore = -1;
        for (Integer step : road) {
            // line selected 
            if (flag > - 1) {
                for (ShapeLine l : mListShapeLines) {
                    // kiểm tra line có hướng 
                    if (isCoHuong) {
                        if (l.getStartIndicator() == pointBefore
                                && l.getFinishIndicator() == step) {
                            l.setIsSelected(true);
                        }
                    } // kiểm tra line vô hướng 
                    else {
                        if ((l.getStartIndicator() == pointBefore
                                && l.getFinishIndicator() == step)
                                || (l.getStartIndicator() == step
                                && l.getFinishIndicator() == pointBefore)) {
                            l.setIsSelected(true);
                        }
                    }
                }
            }

            // point selected 
            for (ShapePoint p : mListShapePoints) {
                if (p.getIndicator() == step) {
                    p.setIsSelected(true);
                    pointBefore = step;
                }
            }

            flag++;
        }

        repaint();
    }

    public void drawDoThiUnselected() {
        for (ShapeLine shape : mListShapeLines) {
            shape.setIsSelected(false);
        }
        for (ShapePoint shape : mListShapePoints) {
            shape.setIsSelected(false);
        }
        repaint();
    }

    public void setFlagDiChuyen1Diem() {
        flag = Flag.DI_CHUYEN_1_DIEM;
    }

    public void setFlagNoi2Diem() {
        flag = Flag.NOI_2_DIEM;
    }

    public void setFlagXoaDiem() {
        flag = Flag.XOA_DIEM;
    }

    public void setFlagThemDiem() {
        flag = Flag.THEM_DIEM;
    }

    public void setFlagXoaDuongThang() {
        this.flag = Flag.XOA_DUONG_THANG;
    }

    public void setFlagThayDoiGiaTri() {
        this.flag = Flag.THAY_DOI_GIA_TRI;
    }

    // true -> có thể tạo kết nối mới giữa 2 điểm 
    // false -> đã tồn tại kết nối trước đó 
    public boolean checkConnectedOfTwoPointIsValid(int startIndicator, int finishIndicator) {
        if (startIndicator == finishIndicator) {
            return false;
        }
        for (ShapeLine line : mListShapeLines) {
            if ((line.getStartIndicator() == startIndicator
                    && line.getFinishIndicator() == finishIndicator)
                    || (line.getStartIndicator() == finishIndicator
                    && line.getFinishIndicator() == startIndicator)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void callBackSetValueForLine(boolean yes, int value) {
        // set value 
        if (yes) {
            ShapeLine newLine = new ShapeLine(
                    mShapeLineConnectingPoint.getOriginPoint(),
                    mShapeLineConnectingPoint.getFinishPoint(),
                    value,
                    isCoHuong,
                    false,
                    mShapeLineConnectingPoint.getStartIndicator(),
                    mShapeLineConnectingPoint.getFinishIndicator());
            System.out.println("From : " + newLine.getStartIndicator()
                    + "  -> " + newLine.getFinishIndicator() + " : " + newLine.getValue());
            mListShapeLines.add(newLine);
            mCallBackToMainForm.callBackUpdatedFromDrawDoThi(flag, mListShapePoints, mListShapeLines);
        }
        repaint();
    }

    @Override
    public void callBackRemovePoint(int pointIndicator, boolean yes) {
        if (yes) {
            // xoá point 
            for (ShapePoint point : mListShapePoints) {
                if (point.getIndicator() == pointIndicator) {
                    mListShapePoints.remove(point);
                    break;
                }
            }

            // xoá line có liên quan tới point 
            ArrayList<ShapeLine> listTemp = new ArrayList<ShapeLine>();
            for (int i = mListShapeLines.size() - 1; i >= 0; i--) {
                ShapeLine line = mListShapeLines.get(i);
                if (line.getFinishIndicator() != pointIndicator
                        && line.getStartIndicator() != pointIndicator) {
                    listTemp.add(line);
                }
            }

            mListShapeLines.clear();
            mListShapeLines.addAll(listTemp);

            repaint();
            mCallBackToMainForm.callBackUpdatedFromDrawDoThi(flag, mListShapePoints, mListShapeLines);
        }
    }

    @Override
    public void callBackAddNewPoint(ShapePoint newPoint) {
        // thêm điểm mới 
        mListShapePoints.add(newPoint);

        // call back
        mCallBackToMainForm.callBackUpdatedFromDrawDoThi(flag, mListShapePoints, mListShapeLines);
        repaint();
    }

    public void addNewShapeLine(ShapeLine line) {
        mListShapeLines.add(line);
        repaint();
    }

    @Override
    public void callBackRemoveLine(ShapeLine lineDelete) {
        // remove line 
        for (int i = 0; i < mListShapeLines.size(); i++) {
            ShapeLine line = mListShapeLines.get(i);
            if (line.getStartIndicator() == lineDelete.getStartIndicator()
                    && line.getFinishIndicator() == lineDelete.getFinishIndicator()) {
                mListShapeLines.remove(i);
                break;
            }
        }
        // call back 
        mCallBackToMainForm.callBackUpdatedFromDrawDoThi(flag, mListShapePoints, mListShapeLines);
        repaint();
    }

    @Override
    public void callBackChangeIndicatorOfPoint(ShapePoint pointChange) {
        System.out.println("Change point");
        // change indicator 
        // lấy ra indicator trước đó của Point bị thay đổi 
        int beforeIndicator = 0;
        int newIndicator = pointChange.getIndicator();
        for (int i = 0; i < mListShapePoints.size(); i++) {
            ShapePoint point = mListShapePoints.get(i);
            if (point.getOriginPoint() == pointChange.getOriginPoint()) {
                beforeIndicator = point.getIndicator();
                point.setIndicator(newIndicator);
                break;
            }
        }

        // cập nhật lại point trong list lines 
        for (ShapeLine line : mListShapeLines) {
            if (line.getStartIndicator() == beforeIndicator) {
                line.setStartIndicator(newIndicator);
                System.out.println("Start : " + line.getStartIndicator() + " --- End : " + line.getFinishIndicator());
            }

            if (line.getFinishIndicator() == beforeIndicator) {
                line.setFinishIndicator(newIndicator);
                System.out.println("Start : " + line.getStartIndicator() + " --- End : " + line.getFinishIndicator());

            }
        }

        // call back 
        mCallBackToMainForm.callBackUpdatedFromDrawDoThi(flag, mListShapePoints, mListShapeLines);
        repaint();
    }

    @Override
    public void callBackChangeValueOfLine(ShapeLine lineChange) {
        // change line 
        for (int i = 0; i < mListShapeLines.size(); i++) {
            ShapeLine line = mListShapeLines.get(i);
            if (line.getStartIndicator() == lineChange.getStartIndicator()
                    && line.getFinishIndicator() == lineChange.getFinishIndicator()) {
                mListShapeLines.get(i).setValue(lineChange.getValue());
                break;
            }
        }
        // call back 
        mCallBackToMainForm.callBackUpdatedFromDrawDoThi(flag, mListShapePoints, mListShapeLines);
        repaint();
    }

    public enum Flag {
        DI_CHUYEN_1_DIEM,
        NOI_2_DIEM,
        XOA_DIEM,
        THEM_DIEM,
        XOA_DUONG_THANG,
        THAY_DOI_GIA_TRI
    }
}
