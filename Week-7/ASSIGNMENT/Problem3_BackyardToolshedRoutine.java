abstract class GardenTool {
    public GardenTool() {
    }

    // This base implementation allows subclasses to extend the message with super.use().
    public String use() {
        return "Using the tool in the garden";
    }
}

class CuttingTool extends GardenTool {
    public CuttingTool() {
        super();
    }

    @Override
    public String use() {
        return super.use() + ", blade sharpened first";
    }
}

class Pruner extends CuttingTool {
    public Pruner() {
        super();
    }

    @Override
    public String use() {
        return super.use() + ", then trimming branches precisely";
    }
}

public class Problem3_BackyardToolshedRoutine {
    public static void main(String[] args) {
        CuttingTool cutter = new CuttingTool();
        Pruner pruner = new Pruner();
        System.out.println(cutter.use());
        System.out.println(pruner.use());
    }
}
