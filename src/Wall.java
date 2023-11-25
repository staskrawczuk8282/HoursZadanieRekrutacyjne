
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

    //funkcja findBlockByColor szuka pierwszego napotkanego Blocka o podanym w argumencie kolorze.
    //funkcja ta wykorzystuje funkcje pomocnicza findInCompositeBlock do przejrzenia wszystkich blocków w liscie blocks
    //wliczajac w to wystapienia CompositeBlock w którym moga wystepowac zagniezdzone wystapienia Block.
    //Zwrot funkcji to Optional<Block> reprezentujacy pierwszy znalezion blok odpowiadajacy warunkowi, albo pustą instancje w przypadku nie znalezienia takiego blocku.
    @Override
    public Optional<Block> findBlockByColor(String color) {
        return blocks.stream().filter(x->findInCompositeBlock(x,block->block.getColor().equals(color))).findFirst();
    }

    //findBlocksByMaterial szuka wszystkich Blocków zrobionych z podanego w argumencie materiału
    //Z pomoca funkcji findInCompositeBlock sprawdzane jest czy dany block spelnia podany warunek
    //Kazdy spelniajacy warunek block dopisywany jest do listy, ktora nastepnie jest zwracana jako wynik funkcji
    @Override
    public List<Block> findBlocksByMaterial(String material) {
        return blocks.stream().filter(x ->findInCompositeBlock(x,block->block.getMaterial().equals(material))).collect(Collectors.toList());
    }

    //Funkcja count zwraca ilosc wszystkich Blocków zawartych w liscie  blocks.
    //Nie moze ona wykorzystac funkcji pomocniczej spowodu wykorzystania w niej MatchAny, ktore szuka dowolnego wystopienia warunku
    //W tym przypadku chcemy policzyc kazde wystapienie Blocku a nie tylko pierwsze, dlatego rekurencje  zawarlismy w postaci pojedenczyego streamu.
    //W tym przypadku otwieramy nowy stream jezeli Block jest instancji CompositeBlock, a nastepnie splaszczamy wszystkie streamy do  jednego poprzez uzycie FlatMapy
    //Na koncu poprzez uzycie count zliczamy zawarte elementy oraz castujemy je do int, poniewaz bazowo count zwraca nam wynik w long.
    @Override
    public int count() {
        return (int)  blocks.stream().flatMap(x->x instanceof CompositeBlock?((CompositeBlock)x).getBlocks().stream(): Stream.of(x)).count();
    }

    //findInCompositeBlock to pomocnicza funkcja dla wszystkich naszych metod. Pozwala ona sprawdzic czy
    //sprawdzany Block jest CompositeBlock i jezeli tak to poprzez rekurencje dociera do pierwszego napotkanego zwyklego Blocka.
    //Jezeli napotkany Block spelnia warunek przekazany w argumencie zwraca true, jezeli nie to false.
    private boolean findInCompositeBlock(Block block, Predicate<Block> task){
        if(block instanceof CompositeBlock){
            List<Block> compositeComponents = ((CompositeBlock) block).getBlocks();
            return compositeComponents.stream().anyMatch(component->findInCompositeBlock(component,task));
        }
        if(task.test(block)){
            return true;
        }
        return false;
    }
}
