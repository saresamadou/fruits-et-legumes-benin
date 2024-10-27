package bj.fruitsetlegumes.api.domain.usecases;

public class CreateFruitCommand {
    private String name;

    public CreateFruitCommand() {
    }

    public CreateFruitCommand(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
