public class TOMLExportStrategy implements DrawingExportStrategy {
    @Override
    public String export(Drawing drawing) {
        StringBuilder str = new StringBuilder();
        str.append("title = \"Geometry\"\n\n");

        str.append("[TransformMatrix]\n");
        str.append("entries = [");
        final double[] tmp = drawing.getTransform().getMatrix();
        for (int i = 0; i < 3; ++i)
            str.append(tmp[i] + ", ");
        str.append(tmp[3] + "]\n\n");

        for (Shape s : drawing.getShapes()) {
            str.append("[Shape]\n");
            str.append("lines=[");
            java.util.List<Line> lines = s.getLines();
            for (int i = 0; i < lines.size(); ++i) {
                Line line = lines.get(i);
                str.append("{");
                str.append("start=[");
                str.append(line.getP1().getX() + "," + line.getP1().getY());
                str.append("],");

                str.append("end=[");
                str.append(line.getP2().getX() + "," + line.getP2().getY());
                str.append("]");

                if (i != lines.size() - 1) {
                    str.append("},");
                } else {
                    str.append("}");
                }
            }
            str.append("]\n");
            str.append("color = " + s.getColorHex() + "\n\n");
        }
        return str.toString();
    }
}
