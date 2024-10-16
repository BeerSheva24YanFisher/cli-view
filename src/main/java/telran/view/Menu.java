package telran.view;

import java.util.ArrayList;
import java.util.List;

public class Menu implements Item{
    String name;
    List<Item> items;

    public Menu(String name) {
        this.name = name;
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    @Override
    public String displayName() {
        return name;
    }

    @Override
    public void perform(InputOutput io) {
        io.writeLine("====== " + name + " ======");
        for (int i = 0; i < items.size(); i++) {
            io.writeLine((i + 1) + ". " + items.get(i).displayName());
        }
        io.writeLine("0. Exit");
    
        int choice = io.readInt("Choose option", "Invalid option");
        if (choice > 0 && choice <= items.size()) {
            items.get(choice - 1).perform(io);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    public void addExitOption() {
        addItem(Item.ofExit());
    }

}
