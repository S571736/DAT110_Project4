package no.hvl.dat110.ac.rest;

import com.google.gson.Gson;

import static spark.Spark.*;

/**
 * Hello world!
 */
public class App {

    static AccessLog accesslog = null;
    static AccessCode accesscode = null;


    public static void main(String[] args) {

        if (args.length > 0) {
            port(Integer.parseInt(args[0]));
        } else {
            port(8080);
        }

        // objects for data stored in the service

        accesslog = new AccessLog();
        accesscode = new AccessCode();
        Gson gson = new Gson();

        after((req, res) -> {
            res.type("application/json");
        });

        // for basic testing purposes
        get("/accessdevice/hello", (req, res) -> gson.toJson("IoT Access Control Device"));


        post("/accessdevice/log", (req, res) -> accesslog.add(req.body()));
        get("/accessdevice/log", (req, res) -> accesslog.toJson());
        get("/accessdevice/log/:id", (req, res) -> gson.toJson(accesslog.get(Integer.parseInt(req.params(":id")))));

        delete("/accessdevice/log", (req, res) -> {
            accesslog.clear();
            return gson.toJson("Log is now clear");

        });
        // TODO: implement the routes required for the access control service

        get("/accessdevice/code", (req, res) -> gson.toJson(accesscode));
        put("/accessdevice/code", (req, res) -> {

            accesscode = gson.fromJson(req.body(), AccessCode.class);
            accesscode.setAccesscode(accesscode.getAccesscode());

            return req.body();
        });

    }

}
