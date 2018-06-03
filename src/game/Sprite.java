package game;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Sprite {

    protected int x;
    protected int y;
    protected int velocityX;
    protected int velocityY;
    protected Direction direction;
    
    protected int width;
    protected int height;
    protected boolean visible;
    protected Image currentImage;
    

    public Sprite(int x, int y) {
        this.x = x;
        this.y = y;
        visible = true;
    }
    
    // conctructor for furniture
    Sprite (String imageName) {
        this.x = 0;
        this.y = 0;
        ImageIcon ii = new ImageIcon(imageName);
        currentImage = ii.getImage();
        
        width = currentImage.getWidth(null);
        height = currentImage.getHeight(null);
    }

    protected void getImageDimensions() {
        width = currentImage.getWidth(null);
        height = currentImage.getHeight(null);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    protected void loadImage(String imageName) {

        ImageIcon ii = new ImageIcon(imageName);
        currentImage = ii.getImage();
    }

    public Image getImage() {
        return currentImage;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public int getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(int velocityX) {
        this.velocityX = velocityX;
    }

    public int getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(int velocityY) {
        this.velocityY = velocityY;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
    
    public void setCurrentImage(Image image){
        this.currentImage = image;
    }
}