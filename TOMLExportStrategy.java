public class TOMLExportStrategy implements DrawingExportStrategy {
    @Override
    public String export(Drawing drawing) {
        StringBuilder str = new StringBuilder();
        str.append("title = \"Geometry\"\n\n");

        str.append("[TransformMatrix]\n");
        str.append("entries = [");
        final double[] tmp = drawing.transform.getMatrix();
        for (int i = 0; i < 3; ++i)
            str.append(tmp[i] + ", ");
        str.append(tmp[3] + "]\n\n");

        for (Shape s : drawing.shapes) {
            str.append("[Shape]\n");
            str.append("lines=[");
            for (int i = 0; i < s.lines.size(); ++i) {
                Line line = s.lines.get(i);
                str.append("{");
                str.append("start=[");
                str.append(line.p1.x + "," + line.p1.y);
                str.append("],");

                str.append("end=[");
                str.append(line.p2.x + "," + line.p2.y);
                str.append("]");

                if (i != s.lines.size() - 1) {
                    str.append("},");
                } else {
                    str.append("}");
                }
            }
            str.append("]\n");
            str.append("color = " + s.color + "\n\n");
        }
        return str.toString();
    }
}
