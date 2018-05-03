package unittests;

import Renderer.ImageWriter;
import org.junit.Test;

public class ImageWriterTest {

    @Test
    public void writeToimage() {
        ImageWriter _imageWriter = new ImageWriter("Test",500, 500,500,500);
        for (int i = 0; i < 500; i+=50) {
            for (int j = 0; j < 500; j++) {
                _imageWriter.writePixel(i, j, new java.awt.Color(255, 255, 255));
                _imageWriter.writePixel(j, i, new java.awt.Color(255, 255, 255));
            }
        }
        _imageWriter.writeToimage();
    }
}