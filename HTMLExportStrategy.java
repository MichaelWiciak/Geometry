public class HTMLExportStrategy implements DrawingExportStrategy {
    @Override
    public String export(Drawing drawing) {
        StringBuilder str = new StringBuilder();
        str.append("<html>\n<head><title>Geometry</title></head>\n<body>\n");

        str.append("<h1>Transform Matrix</h1>\n");
        str.append("<p>Entries: ");
        final double[] tmp = drawing.getTransform().getMatrix();
        for (int i = 0; i < 3; ++i)
            str.append(tmp[i] + ", ");
        str.append(tmp[3] + "</p>\n");

        for (Shape s : drawing.getShapes()) {
            str.append("<h2>Shape</h2>\n");
            str.append("<ul>\n");
            for (Line line : s.getLines()) {
                str.append("<li>Start: (" + line.getP1().getX() + ", " + line.getP1().getY() + "), End: (" + line.getP2().getX() + ", " + line.getP2().getY() + ")</li>\n");
            }
            str.append("</ul>\n");
            str.append("<p>Color: " + s.getColorHex() + "</p>\n");
        }

        str.append("</body>\n</html>\n");
        return str.toString();
    }
}
