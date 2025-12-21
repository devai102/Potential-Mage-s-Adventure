package core;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseHandler extends MouseAdapter {

    public int x, y; // Tọa độ chuột
    public boolean mouseLeftPressed; // Trạng thái click chuột trái

    @Override
    public void mouseMoved(MouseEvent e) {
        x = e.getX(); // Cập nhật tọa độ X
        y = e.getY(); // Cập nhật tọa độ Y
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) { // BUTTON1 là chuột trái
            mouseLeftPressed = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            mouseLeftPressed = false;
        }
    }
}