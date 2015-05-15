import static spark.Spark.*;

public class HelloWorld {
    public static void main(String[] args) {
        before("test/:name", (req, res) -> {
            String user = req.params(":name");
            if (user.equals("peter")) {
                throw new Exception("Unknown name");
            }
            else if (!user.equals("fabian")) {
                halt(401, "I don't want you here");
            }
        });

        get("/", (req, res) -> "Welcome!");
        get("/hello", (req, res) -> "Hello World");
        get("/test/:name", (req, res) -> "Hallo " + req.params(":name"));

        exception(Exception.class, (e, request, response) -> response.body("I'm sorry Peter, but not for you."));
    }
}
