import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {

        Brick brick =  new Brick("Red","Clay3");
        MultiBrick multiBrick = new MultiBrick(Arrays.asList(
                new Brick("Red","Clay"))
              ,"Red","Clay");
        MultiBrick multiBrick1 = new MultiBrick(Arrays.asList(
                new Brick("Red","Clay"),multiBrick)
                ,"Yellow","Clay1");


        Wall wall = new Wall();

       // wall.addBlock(brick);
        wall.addBlock(multiBrick1);
        wall.addBlock(brick);

        int count =  wall.count();
        System.out.println(count);

        Optional<Block> block  = wall.findBlockByColor("Yellow");
        if(block.isPresent()) System.out.println(block.get().getColor());
        else System.out.println("Nie znaleziono bloku o danym kolorze");
        List<Block> material = wall.findBlocksByMaterial("Clay");
        System.out.println(material.size());

    }
}