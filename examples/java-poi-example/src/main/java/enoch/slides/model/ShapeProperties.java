package enoch.slides.model;

import lombok.Data;
import org.apache.poi.sl.usermodel.TextParagraph;

import java.awt.Color;

@Data(staticConstructor = "create")
public class ShapeProperties {
    private Color fontColor;
    private String fontFamily;
    private boolean bold;
    private boolean italic;
    private boolean underline;
    private double fontSize;
    private TextParagraph.FontAlign fontAlign;
    private TextParagraph.TextAlign textAlign;
}
