package meldanor.training4;

/** Decorator for items of {@link Graphvizable} objects. */
public class GraphvizDecorator {
    /** get node decoration */
    public String getNodeDecoration(GraphvizDecorable object) {
        return null;
    }
    /** get node decoration */
    public String getEdgeDecoration(GraphvizDecorable object) {
        return null;
    }
    /** get graph decoration (returns same for all nodes/edges) */
    public String getGraphDecoration(GraphvizDecorable object) {
        return null;
    }
    /** get standard decoration for all nodes (list head) */
    public String getAllNodesDecoration() {
        return "shape=circle";
    }
    /** get standard decoration for all edges (list head) */
    public String getAllEdgesDecoration() {
        return null;
    }
    /** get global style (returns same for all nodes/edges) */
    public String getGlobalStyle() {
        return null;
    }

    /**
     * concatenates {@link #getAllNodesDecoration} and
     * {@link #getNodeDecoration}
     */
    public String getFullNodeDecoration(GraphvizDecorable object) {
        String head = getAllNodesDecoration();
        String text = head != null ? head : "";
        String tail = getNodeDecoration(object);
        return tail != null ? text + "," + tail : text;
    }
    /**
     * concatenates {@link #getAllEdgesDecoration} and
     * {@link #getEdgeDecoration}
     */
    public String getFullEdgeDecoration(GraphvizDecorable object) {
        String head = getAllEdgesDecoration();
        String text = head != null ? head + "," : "";
        String tail = getEdgeDecoration(object);
        return tail != null ? text + tail : text;
    }
}