package enoch.slides;

import enoch.slides.factory.model.shape.ShapeProperties;
import enoch.slides.model.ShapeProperties;
import org.apache.poi.sl.usermodel.TextParagraph;
import org.apache.poi.xslf.usermodel.*;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;


public class MainApplication {
    public static void main(String[] args) throws Exception{
        XMLSlideShow pptx = new XMLSlideShow(new FileInputStream(("sample.pptx")));
        List<XSLFSlide> slides = pptx.getSlides();

        /* Set Heading */
        XSLFSlide slide1 = slides.get(0);
        ArrayList<XSLFShape> shapes = (ArrayList<XSLFShape>) slide1.getShapes();

        for (XSLFShape shape : shapes) {
            System.out.println(shapes.indexOf(shape));
            System.out.println(shape.toString().split(" ", 2)[1]);
        }

        /* Set Heading */
        XSLFAutoShape headerShape = (XSLFAutoShape) shapes.get(1);
        setHeader(headerShape, "Hello World");

        /* Set Content */
        /* Lets do a table here */
        XSLFAutoShape contentShape = (XSLFAutoShape) shapes.get(2);
        contentShape.clearText();
        setContent(slide1);

        /* Set Callout */
        XSLFAutoShape calloutShape = (XSLFAutoShape) shapes.get(3);
        setCallout(calloutShape, "C-C-C-Combo BREAKKERS");

        /* Render */
        FileOutputStream out = new FileOutputStream("powerpoint-with-table.pptx");
        pptx.write(out);
        out.close();
    }

    public static void setCallout(XSLFAutoShape shape, String content) {
        // Need to save attributes
        ShapeProperties properties = getShapeProperties(shape);
        properties.setFontColor(new Color(255, 255, 255));

        setContent(shape, properties, content);
    }

    public static void setContent(XSLFSlide slide) {
        int rowHeight = 50;

        /* Instantiate table */
        XSLFTable table = slide.createTable();
        table.setAnchor(new Rectangle(50, 50, 400, 300));

        /* Establish Column */
        String[] headers = {"ID", "Name", "Description"};

        /* Add Table Header Row */
        XSLFTableRow headerRow = table.addRow();
        headerRow.setHeight(rowHeight);

        /* I'll be using HTML nomenclature here - so that's th for table headers and p for paragraphs*/
        /* I recommend a refresher here - https://developer.mozilla.org/en-US/docs/Web/HTML/Element/table */
        for (int i = 0; i < headers.length; i++) {
            XSLFTableCell th = headerRow.addCell();

            XSLFTextParagraph p = th.addNewTextParagraph();
            p.setTextAlign(TextParagraph.TextAlign.CENTER);

            XSLFTextRun text = p.addNewTextRun();
            text.setText(headers[i]);
            table.setColumnWidth(i, 150);
        }

        // Lets populate some dummy data
        ArrayList<String[]> rows = new ArrayList<String[]>();
        rows.add(new String[] {"21324", "Enoch Chu", "He is a person"});
        rows.add(new String[] {"12399", "Kermit the Frog", "He is a frog"});
        rows.add(new String[] {"12308", "Mr Meowster", "He is a cat"});

        /* Add data in row */
        /* I'll be using HTML nomenclature here - so that's th for table headers and p for paragraphs*/
        /* I recommend a refresher here - https://developer.mozilla.org/en-US/docs/Web/HTML/Element/table */
        for (String[] row : rows) {
            XSLFTableRow tr = table.addRow();
            tr.setHeight(rowHeight);

            for (String data : row) {
                XSLFTableCell cell = tr.addCell();
                XSLFTextParagraph p = cell.addNewTextParagraph();
                XSLFTextRun text = p.addNewTextRun();
                text.setText(data);
            }
        }

        System.out.println("Table Done");
    }

    public static void setHeader(XSLFAutoShape shape, String content) {
        ShapeProperties properties = getShapeProperties(shape);
        properties.setFontColor(new Color(21, 28, 119));

        setContent(shape, properties, content);
    }

    public static void setContent(XSLFAutoShape shape, ShapeProperties properties, String content) {
        // Set Attributes - it's a bit hacky - but assumes are headers are really just one paragraph.
        shape.clearText();

        // Reapply attributes
        XSLFTextParagraph newTextParagraph = shape.addNewTextParagraph();
        newTextParagraph.setFontAlign(properties.getFontAlign());
        newTextParagraph.setTextAlign(properties.getTextAlign());

        XSLFTextRun addNewTextRun = newTextParagraph.addNewTextRun();
        addNewTextRun.setFontColor(properties.getFontColor());
        addNewTextRun.setFontFamily(properties.getFontFamily());
        addNewTextRun.setFontSize(properties.getFontSize());
        addNewTextRun.setItalic(properties.isItalic());
        addNewTextRun.setBold(properties.isBold());
        addNewTextRun.setUnderlined(properties.isUnderline());

        // Set text
        addNewTextRun.setText(content);
    }

    public static ShapeProperties getShapeProperties(XSLFAutoShape shape) {
        ShapeProperties properties = ShapeProperties.create();

        // Set Attributes - it's a bit hacky - but assumes are headers are really just one paragraph.
        for (XSLFTextParagraph paragraph : shape.getTextParagraphs()) {
            for (XSLFTextRun text : paragraph.getTextRuns()) {
                /* TODO Missing setter for color since I have been getting XML errors */
                properties.setUnderline(text.isUnderlined());
                properties.setBold(text.isBold());
                properties.setFontFamily(text.getFontFamily());
                properties.setFontSize(text.getFontSize());
                properties.setItalic(text.isItalic());
            }

            properties.setFontAlign(paragraph.getFontAlign());
            properties.setTextAlign(paragraph.getTextAlign());
        }
        return properties;
    }

    /*
    public static void createSlide() throws IOException {
        XMLSlideShow ppt = new XMLSlideShow();
        ppt.createSlide();

        XSLFSlideMaster defaultMaster = ppt.getSlideMasters().get(0);
        XSLFSlideLayout layout = defaultMaster.getLayout(SlideLayout.TITLE_AND_CONTENT);
        XSLFSlide slide = ppt.createSlide(layout);

        XSLFTextShape titleShape = slide.getPlaceholder(0);
        XSLFTextShape contentShape = slide.getPlaceholder(1);

        FileOutputStream out = new FileOutputStream("powerpoint.pptx");
        ppt.write(out);
        out.close();
    }
    */
}
