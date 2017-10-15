/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Draw;

import Model.MPoint;
import Utils.MaTranUtils;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author DELL
 */
public class DrawDoThi extends JPanel {

    private ArrayList<ShapeLine> listShapeLines;
    private ArrayList<ShapePoint> listShapePoints;

    public DrawDoThi(ArrayList<MPoint> listDatas, boolean isCoHuong) {
        initShapeData(listDatas, isCoHuong);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                Point clickedPoint = e.getPoint();
                boolean flagClicked = false;
                // click vào point 
                for (ShapePoint point : listShapePoints) {
                    if ((point.getShape().contains(clickedPoint))) {
                        System.out.println("Point value : " + point.getIndicator());
                        flagClicked = true;
                        break;
                    }
                }

                // click vào line 
                if (flagClicked == false) {
                    for (ShapeLine line : listShapeLines) {
                        if ((line.getShape().contains(clickedPoint))) {
                            System.out.println("Line value : " + line.getValue());
                        }
                    }
                }

//                addNewPoint(new Point(clickedPoint), 4);
            }
        }
        );
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
        for (ShapeLine shape : listShapeLines) {
            shape.paint(g2d);
        }
        // draw point 
        for (ShapePoint shape : listShapePoints) {
            shape.paint(g2d);
        }
    }

    public void initShapeData(ArrayList<MPoint> listDatas, boolean isCoHuong) {
        listShapeLines = new ArrayList<ShapeLine>();
        listShapePoints = new ArrayList<ShapePoint>();
        ArrayList<MShape> listShapes = MaTranUtils.convertListDatasToListShape(listDatas, isCoHuong);
        for (MShape shape : listShapes) {
            if (shape instanceof ShapeLine) {
                listShapeLines.add((ShapeLine) shape);
            }
            if (shape instanceof ShapePoint) {
                listShapePoints.add((ShapePoint) shape);
            }
        }
    }

    public void addNewPoint(Point p, int indicator) {
//        ShapePoint sp = new ShapePoint(p, indicator, true);
//        listShapePoints.add(sp);
//        repaint();
    }
}
