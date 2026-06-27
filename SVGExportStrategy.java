public class SVGExportStrategy implements DrawingExportStrategy {
    @Override
    public String export(Drawing drawing) {
        StringBuilder str = new StringBuilder();
        str.append("<svg xmlns=\"http://www.w3.org/2000/svg\">\n");

        for (Shape s : drawing.shapes) {
            for (int i = 0; i < s.lines.size(); ++i) {
                Line line = s.lines.get(i);
                str.append("<line x1=\"" + line.p1.x + "\" y1=\"" + line.p1.y + "\" ");
                str.append("x2=\"" + line.p2.x + "\" y2=\"" + line.p2.y + "\" ");
                str.append("stroke=\"" + s.color + "\" />\n");
            }
        }

        str.append("</svg>\n");
        return str.toString();
    }
}
