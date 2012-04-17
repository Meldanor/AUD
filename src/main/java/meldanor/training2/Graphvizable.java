package meldanor.training2;

/**
 * Interface for <a href="http://www.graphviz.org/">GraphViz</a> rendering.
 * 
 * @see DotViewer
 */
public interface Graphvizable {

    /**
     * Get <a href="http://www.graphviz.org/content/dot-language">dot</a>
     * representation.
     * <p>
     */
    String toDot();
}