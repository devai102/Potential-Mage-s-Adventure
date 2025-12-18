import javax.swing.*;
import core.GamePanel;

public class Main {

    public static void main(String[] args) {
        // Sử dụng SwingUtilities để đảm bảo UI được khởi tạo trên Event Dispatch Thread (EDT)
        // Điều này giúp tránh các lỗi xung đột luồng khi khởi tạo GUI
        SwingUtilities.invokeLater(Main::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        // 1. Khởi tạo Frame
        JFrame frame = new JFrame();
        frame.setTitle("Potential Mage's Adventure");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false); // Game thường nên cố định kích thước cửa sổ

        // 2. Khởi tạo GamePanel
        GamePanel gamePanel = new GamePanel();

        // 3. Thiết lập bố cục
        frame.add(gamePanel);

        // 4. Tính toán kích thước (pack sẽ dựa vào PreferredSize của GamePanel)
        frame.pack();

        // 5. Căn giữa màn hình và hiển thị
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // 6. Bắt đầu logic Game (Setup các Object trước khi chạy Thread)
        // Lưu ý: Nếu bạn có hàm setupGame() trong GamePanel, hãy gọi nó ở đây
        gamePanel.setupGame();

        gamePanel.setupGame();
        gamePanel.startGameThread();
    }
}