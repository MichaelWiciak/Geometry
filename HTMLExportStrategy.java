public class HTMLExportStrategy implements DrawingExportStrategy {
    @Override
    public String export(Drawing drawing) {
        StringBuilder str = new StringBuilder();
        str.append("<html>\n<head><title>Geometry</title></head>\n<body>\n");

        str.append("<h1>Transform Matrix</h1>\n");
        str.append("<p>Entries: ");
        final double[] tmp = drawing.transform.getMatrix();
        for (int i = 0; i < 3; ++i)
            str.append(tmp[i] + ", ");
        str.append(tmp[3] + "</p>\n");

        for (Shape s : drawing.shapes) {
            str.append("<h2>Shape</h2>\n");
            str.append("<ul>\n");
            for (int i = 0; i < s.lines.size(); ++i) {
                Line line = s.lines.get(i);
                str.append("<li>Start: (" + line.p1.x + ", " + line.p1.y + "), End: (" + line.p2.x + ", " + line.p2.y + ")</li>\n");
            }
            str.append("</ul>\n");
            str.append("<p>Color: " + s.color + "</p>\n");
        }

        str.append("</body>\n</html>\n");
        return str.toString();
    }
}
