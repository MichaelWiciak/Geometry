public class DrawingExportStrategyFactory {
    
    // Returns the default export strategy (TOML)
    public static DrawingExportStrategy getDefaultStrategy() {
        return new TOMLExportStrategy();
    }

    // Returns the export strategy based on the format string
    public static DrawingExportStrategy getStrategy(String format) {
        return switch (format.toLowerCase()) {
            case "html" -> new HTMLExportStrategy();
            case "svg" -> new SVGExportStrategy();
            case "toml" -> new TOMLExportStrategy();
            default -> getDefaultStrategy();
        };
    }
}
