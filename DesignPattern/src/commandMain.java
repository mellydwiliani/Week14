// Command Interface
interface Command {
    void execute();
}

// Receiver
class Chef {
    void cookPizza() {
        System.out.println("Chef is cooking a pizza.");
    }

    void cookPasta() {
        System.out.println("Chef is cooking pasta.");
    }

    void cookSalad() {
        System.out.println("Chef is preparing a salad.");
    }
}

// Concrete Commands
class PizzaOrder implements Command {
    private Chef chef;

    public PizzaOrder(Chef chef) {
        this.chef = chef;
    }

    @Override
    public void execute() {
        chef.cookPizza();
    }
}

class PastaOrder implements Command {
    private Chef chef;

    public PastaOrder(Chef chef) {
        this.chef = chef;
    }

    @Override
    public void execute() {
        chef.cookPasta();
    }
}

class SaladOrder implements Command {
    private Chef chef;

    public SaladOrder(Chef chef) {
        this.chef = chef;
    }

    @Override
    public void execute() {
        chef.cookSalad();
    }
}

// Invoker
class Waiter {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void placeOrder() {
        if (command != null) {
            command.execute();
        } else {
            System.out.println("No order to process.");
        }
    }
}

// Client
public class commandMain {
    public static void main(String[] args) {
        // Receiver
        Chef chef = new Chef();

        // Concrete Commands
        Command pizzaOrder = new PizzaOrder(chef);
        Command pastaOrder = new PastaOrder(chef);
        Command saladOrder = new SaladOrder(chef);

        // Invoker
        Waiter waiter = new Waiter();

        // Client sets and places orders
        System.out.println("Customer orders pizza:");
        waiter.setCommand(pizzaOrder);
        waiter.placeOrder();

        System.out.println("\nCustomer orders pasta:");
        waiter.setCommand(pastaOrder);
        waiter.placeOrder();

        System.out.println("\nCustomer orders salad:");
        waiter.setCommand(saladOrder);
        waiter.placeOrder();
    }
}
