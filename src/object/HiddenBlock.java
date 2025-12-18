package object;

public class HiddenBlock extends SuperObject{

    public HiddenBlock(String filePath){
        name = "Hidden Block";
        setImage(filePath);
    }
    
    void setImage(String filePath){
        try{
            image1 = javax.imageio.ImageIO.read(getClass().getResourceAsStream(filePath));
        }catch(Exception e){
            e.getStackTrace();
        }
    }
}
