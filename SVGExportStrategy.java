public class SVGExportStrategy implements DrawingExportStrategy {
    @Override
    public String export(Drawing drawing) {
        StringBuilder str = new StringBuilder();
        str.append("<svg xmlns=\"http://www.w3.org/2000/svg\">\n");

        for (Shape s : drawing.getShapes()) {
            for (Line line : s.getLines()) {
                str.append("<line x1=\"" + line.getP1().getX() + "\" y1=\"" + line.getP1().getY() + "\" ");
                str.append("x2=\"" + line.getP2().getX() + "\" y2=\"" + line.getP2().getY() + "\" ");
                str.append("stroke=\"" + s.getColorHex() + "\" />\n");
            }
        }

        str.append("</svg>\n");
        return str.toString();
    }
}
