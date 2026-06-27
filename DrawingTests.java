/**
 *
 * To compile:
 * javac -cp junit-platform-console-standalone-1.9.3.jar *.java -d target
 *
 * To run tests:
 * java -jar .\junit-platform-console-standalone-1.9.3.jar --class-path target --select-class DrawingTests
 */

 import static org.junit.Assert.assertArrayEquals;
 import static org.junit.Assert.assertEquals;
 import static org.junit.Assert.assertTrue;

 import org.junit.Before;
 import org.junit.Test;

 /**
  * class holding the Junit4 tests for Drawing
 */
public class DrawingTests {

    private Drawing drawing;
    private String tomlTarget;
    private String htmlTarget;
    private String svgTarget;

    @Before
    public void setUp() throws Exception {
        // Initialize the Drawing object and add shapes
        drawing = new Drawing();
        drawing.add(GeometryApp.createRectangle(240, 100, 100, 100));
        drawing.add(GeometryApp.createTriangle());

        // Prepare expected TOML output
        StringBuilder tomlBuild = new StringBuilder();
        tomlBuild.append("title = \"Geometry\"\n\n");
        tomlBuild.append("[TransformMatrix]\n");
        tomlBuild.append("entries = [1.0, 0.0, 0.0, 1.0]\n\n");
        tomlBuild.append("[Shape]\n");
        tomlBuild.append("lines=[{start=[240.0,100.0],end=[340.0,100.0]},{start=[340.0,100.0],end=[340.0,200.0]},{start=[340.0,200.0],end=[240.0,200.0]},{start=[240.0,200.0],end=[240.0,100.0]}]\n");
        tomlBuild.append("color = null\n\n");
        tomlBuild.append("[Shape]\n");
        tomlBuild.append("lines=[{start=[100.0,100.0],end=[200.0,200.0]},{start=[200.0,200.0],end=[100.0,200.0]},{start=[100.0,200.0],end=[100.0,100.0]}]\n");
        tomlBuild.append("color = java.awt.Color[r=255,g=0,b=0]\n\n");
        tomlTarget = tomlBuild.toString();

        // Prepare expected HTML output
        StringBuilder htmlBuild = new StringBuilder();
        htmlBuild.append("<html>\n<head><title>Geometry</title></head>\n<body>\n");
        htmlBuild.append("<h1>Transform Matrix</h1>\n");
        htmlBuild.append("<p>Entries: 1.0, 0.0, 0.0, 1.0</p>\n");
        htmlBuild.append("<h2>Shape</h2>\n");
        htmlBuild.append("<ul>\n");
        htmlBuild.append("<li>Start: (240.0, 100.0), End: (340.0, 100.0)</li>\n");
        htmlBuild.append("<li>Start: (340.0, 100.0), End: (340.0, 200.0)</li>\n");
        htmlBuild.append("<li>Start: (340.0, 200.0), End: (240.0, 200.0)</li>\n");
        htmlBuild.append("<li>Start: (240.0, 200.0), End: (240.0, 100.0)</li>\n");
        htmlBuild.append("</ul>\n");
        htmlBuild.append("<p>Color: null</p>\n");
        htmlBuild.append("<h2>Shape</h2>\n");
        htmlBuild.append("<ul>\n");
        htmlBuild.append("<li>Start: (100.0, 100.0), End: (200.0, 200.0)</li>\n");
        htmlBuild.append("<li>Start: (200.0, 200.0), End: (100.0, 200.0)</li>\n");
        htmlBuild.append("<li>Start: (100.0, 200.0), End: (100.0, 100.0)</li>\n");
        htmlBuild.append("</ul>\n");
        htmlBuild.append("<p>Color: java.awt.Color[r=255,g=0,b=0]</p>\n");
        htmlBuild.append("</body>\n</html>\n");
        htmlTarget = htmlBuild.toString();

        // Prepare expected SVG output
        StringBuilder svgBuild = new StringBuilder();
        svgBuild.append("<svg xmlns=\"http://www.w3.org/2000/svg\">\n");
        svgBuild.append("<line x1=\"240.0\" y1=\"100.0\" x2=\"340.0\" y2=\"100.0\" stroke=\"null\" />\n");
        svgBuild.append("<line x1=\"340.0\" y1=\"100.0\" x2=\"340.0\" y2=\"200.0\" stroke=\"null\" />\n");
        svgBuild.append("<line x1=\"340.0\" y1=\"200.0\" x2=\"240.0\" y2=\"200.0\" stroke=\"null\" />\n");
        svgBuild.append("<line x1=\"240.0\" y1=\"200.0\" x2=\"240.0\" y2=\"100.0\" stroke=\"null\" />\n");
        svgBuild.append("<line x1=\"100.0\" y1=\"100.0\" x2=\"200.0\" y2=\"200.0\" stroke=\"java.awt.Color[r=255,g=0,b=0]\" />\n");
        svgBuild.append("<line x1=\"200.0\" y1=\"200.0\" x2=\"100.0\" y2=\"200.0\" stroke=\"java.awt.Color[r=255,g=0,b=0]\" />\n");
        svgBuild.append("<line x1=\"100.0\" y1=\"200.0\" x2=\"100.0\" y2=\"100.0\" stroke=\"java.awt.Color[r=255,g=0,b=0]\" />\n");
        svgBuild.append("</svg>\n");
        svgTarget = svgBuild.toString();
    }

    @Test
    public void testTOMLExportStrategy() {
        drawing.setExportStrategyByFormat("toml");
        final String fileout = drawing.toFileString();
        assertEquals(tomlTarget, fileout);
    }

    @Test
    public void testHTMLExportStrategy() {
        drawing.setExportStrategyByFormat("html");
        final String fileout = drawing.toFileString();
        assertEquals(htmlTarget, fileout);
    }

    @Test
    public void testSVGExportStrategy() {
        drawing.setExportStrategyByFormat("svg");
        final String fileout = drawing.toFileString();
        assertEquals(svgTarget, fileout);
    }

    @Test
    public void testFactoryReturnsCorrectStrategy() {
        assertTrue(DrawingExportStrategyFactory.getStrategy("html") instanceof HTMLExportStrategy);
        assertTrue(DrawingExportStrategyFactory.getStrategy("svg") instanceof SVGExportStrategy);
        assertTrue(DrawingExportStrategyFactory.getStrategy("unknown") instanceof TOMLExportStrategy);
        assertTrue(DrawingExportStrategyFactory.getDefaultStrategy() instanceof TOMLExportStrategy);
    }

    @Test
    public void testSetExportStrategyByFormat() {
        drawing.setExportStrategyByFormat("html");
        assertEquals(drawing.toFileString(), new HTMLExportStrategy().export(drawing));

        drawing.setExportStrategyByFormat("svg");
        assertEquals(drawing.toFileString(), new SVGExportStrategy().export(drawing));

        drawing.setExportStrategyByFormat("unknown");
        assertEquals(drawing.toFileString(), new TOMLExportStrategy().export(drawing));
    }

}