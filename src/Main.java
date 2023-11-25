import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {

        Brick brick =  new Brick("Red","Clay");
        MultiBrick multiBrick = new MultiBrick(Arrays.asList(
                new Brick("Blue","Clay"),
                new Brick("Yellow","Clay2")
        ));

        Wall wall = new Wall();

        wall.addBlock(brick);
        wall.addBlock(multiBrick);

        int count =  wall.count();
        System.out.println(count);

        Optional<Block> block  = wall.findBlockByColor("Red");
        System.out.println(block.get().getColor());
        List<Block> material = wall.findBlocksByMaterial("Clay");
        System.out.println(material.size());

    }
}