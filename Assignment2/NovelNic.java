package cosc201.a2;

import java.util.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class NovelNic extends Potionmaster {
    int drawsUsed;

    public NovelNic(Potionarium potionarium) {

        super(potionarium);
        drawsUsed = 0;
    }

    public int getDrawersUsed() {
        return drawsUsed;
    }

    public String collectIngredients(List<String> ingredients) {
        drawsUsed = 0;
        List<String> ingredientsCopy = new ArrayList<>(ingredients);
        StringBuilder sb = new StringBuilder();
        List<String> missing = new ArrayList<>();
        Set<String> hs = new HashSet<>();
        Map<Long, Set<String>> hm = new HashMap<>();
        boolean passed = true;
        hs = potionarium.getInventory();
        for (String s : ingredientsCopy) {
            if (hs.contains(s) == false) {
                missing.add(s);
                passed = false;
            }
        }
        if (passed == false) {
            sb.append("Missing ingredients: ");
            for (int i = 0; i < missing.size(); i++) {
                if (i == missing.size() - 1) {
                    sb.append(missing.get(i));
                } else {
                    sb.append(missing.get(i) + ", ");
                }

            }
            return (sb.toString());
        }
        while (ingredientsCopy.iterator().hasNext()) {
            String i = ingredientsCopy.iterator().next();
            // System.out.println(i);
            // System.out.println(i);
            List<Long> drawersSet = new ArrayList<>(potionarium.getDrawers(i));
            // drawersSet.sort(new Comparator<Long>() {
            // @Override
            // public int compare(Long l1, Long l2) {
            // Integer counter = 0;
            // Integer counter2 = 0;
            // for (String s: ingredientsCopy)
            // {
            // if(potionarium.getIngredients(l1).contains(s) == true)
            // {
            // counter++;
            // }
            // if(potionarium.getIngredients(l2).contains(s) == true)
            // {
            // counter2++;
            // }
            // }
            // int output = counter.compareTo(counter2);
            // return output;
            // }

            // });
            int highest = 0;
            Long draw_to_use = drawersSet.get(0);
            for (Long l : drawersSet) {
                Set<String> set = new HashSet<>(potionarium.getIngredients(l));
                set.retainAll(ingredientsCopy);
                if (set.size() > highest) {
                    highest = set.size();
                    draw_to_use = l;
                }
            }

            Set<String> ingredietsInDrawer = new HashSet<>(potionarium.getIngredients(draw_to_use));
            ingredietsInDrawer.retainAll(ingredientsCopy);
            hm.put(draw_to_use, ingredietsInDrawer);
            for (String s : ingredietsInDrawer) {
                ingredientsCopy.remove(s);
                potionarium.removeIngredient(draw_to_use, s);
            }

        }
        SortedSet<Long> keys = new TreeSet<>(hm.keySet());
        for (Long key : keys) {
            drawsUsed++;
            Set<String> set = new HashSet<>(hm.get(key));
            Iterator<String> iterator = set.iterator();
            sb.append("Drawer " + key + ": ");
            while (iterator.hasNext()) {
                String s = iterator.next();
                if (!iterator.hasNext()) {
                    // last name
                    sb.append(s);
                    break;
                }
                sb.append(s + ", ");
                // Do stuff
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}