import java.util.List;

public class MultiBrick implements CompositeBlock{

    private List<Block> blocks;

    private String color;
    private String material;

    public MultiBrick(List<Block>blocks,String color,String material){
        this.blocks=blocks;
        this.color=color;
        this.material=material;
    }
    @Override
    public List<Block> getBlocks() {
        return blocks;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public String getMaterial() {
        return material;
    }
}
