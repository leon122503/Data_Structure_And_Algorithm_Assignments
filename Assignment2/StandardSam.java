package cosc201.a2;

import java.util.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StandardSam extends Potionmaster {
    private int drawsUsed;

    public StandardSam(Potionarium potionarium) {
        super(potionarium);
        drawsUsed = 0;
    }

    public int getDrawersUsed() {
        return drawsUsed;
    }

    public String collectIngredients(List<String> ingredients) {
        drawsUsed = 0;
        StringBuilder sb = new StringBuilder();
        List<String> missing = new ArrayList<>();
        List<String> ingredientsCopy = new ArrayList<>(ingredients);
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
            List<Long> drawersSet = new ArrayList<>(potionarium.getDrawers(i));
            long lowest = Collections.min(drawersSet);
            Set<String> ingredietsInDrawer = new HashSet<>(potionarium.getIngredients(lowest));
            ingredietsInDrawer.retainAll(ingredientsCopy);
            hm.put(lowest, ingredietsInDrawer);
            for (String s : ingredietsInDrawer) {
                ingredientsCopy.remove(s);
                potionarium.removeIngredient(lowest, s);
            }

        }
        SortedSet<Long> keys = new TreeSet<>(hm.keySet());
        for (Long key : keys) {
            drawsUsed += 1;
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
