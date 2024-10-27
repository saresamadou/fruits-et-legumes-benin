package bj.fruitsetlegumes.api.rest;

public class CreateFruitRequest {
    private String name;

    public CreateFruitRequest() {
    }

    public CreateFruitRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
