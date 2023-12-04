
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Wall implements Structure{

    private List<Block> blocks = new ArrayList<>();

    //Prosty konstruktor do celów testowych
    //W strukturze plików dodano również klasy implementujace interfejsy Block i CompositeBlock, też do celów testowych
    public  void addBlock(Block block){
        blocks.add(block);
    }

    @Override
        public Optional<Block> findBlockByColor(String color) {
            return blocks.stream().map(x->findColorInCompositeBlock(x,block -> block.getColor().equals(color))).filter(x->x!=null).findFirst();
        }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        return blocks.stream().flatMap(x->findMaterialInCompositeBlock(x,block->block.getMaterial().equals(material)).stream()).collect(Collectors.toList());
    }

    @Override
    public int count() {
        return (int)blocks.stream().flatMap(x->findMaterialInCompositeBlock(x,y->true).stream()).count();
    }

    private Block findColorInCompositeBlock(Block block, Predicate<Block> task){
        if(task.test(block)){
            return block;
        }
        if(block instanceof CompositeBlock){
            List<Block> compositeComponents = ((CompositeBlock) block).getBlocks();
            for(Block x : compositeComponents){
                Block tmp  = findColorInCompositeBlock(x,task);
                if(tmp!=null) return tmp;
            }
        }
        return null;
    }
    private List<Block> findMaterialInCompositeBlock(Block block, Predicate<Block> task){
        List<Block> tmpList = new ArrayList<>();
        if(task.test(block)){
            tmpList.add(block);
        }
        if(block instanceof CompositeBlock){
            List<Block> compositeComponents = ((CompositeBlock) block).getBlocks();
            for(Block x : compositeComponents){
                List<Block> tmp  = findMaterialInCompositeBlock(x,task);
                if(tmp!=null) tmpList.addAll(tmp);
            }
        }
        return tmpList;
    }
}
