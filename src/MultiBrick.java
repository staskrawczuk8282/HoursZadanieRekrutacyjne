import java.util.List;

public class MultiBrick implements CompositeBlock{

    private List<Block> blocks;

    public MultiBrick(List<Block>blocks){
        this.blocks=blocks;
    }
    @Override
    public List<Block> getBlocks() {
        return blocks;
    }

    @Override
    public String getColor() {
        return null;
    }

    @Override
    public String getMaterial() {
        return null;
    }
}
