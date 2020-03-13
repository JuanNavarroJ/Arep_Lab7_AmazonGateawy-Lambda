/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arep.server;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import spark.Request;
import spark.Response;
import static spark.Spark.*;

/**
 * Clase SparWebApp que crea una web desarrollada con SPARK.
 *
 * @author Juan David
 */
public class SparkWebApp {


    /**
     * This main method uses SparkWeb static methods and lambda functions to
     * create a simple Hello World web app. It maps the lambda function to the
     * /hello relative URL.
     */
    public static void main(String[] args) {
        port(getPort());
        get("/inputdata", (req, res) -> inputDataPage(req, res));
        get("/results", (req, res) -> {
            res.type("application/json");
            return resultsPage(req,res);
        });
    }

    /**
     * Metodo que crea el html de la pagina web.
     *
     * @param req Es el request de Spark
     * @param res Es el response de Spark
     * @return Retorna Un html en forma de string.
     */
    private static String inputDataPage(Request req, Response res) {
        String pageContent
                = "<!DOCTYPE html>"
                + "<html>"
                + "<style>"
                + "input[type=text], select { "
                + "width: 100%;"
                + "padding: 12px 20px;"
                + "margin: 8px 0;"
                + "display: inline-block;"
                + "border: 1px solid #ccc;"
                + "border-radius: 4px;"
                + "box-sizing: border-box;"
                + "}"
                + "input[type=submit] { "
                + "    width: 100%;"
                + "    background-color: #4CAF50;"
                + "    color: white; "
                + "    padding: 14px 20px;"
                + "    margin: 8px 0;"
                + "    border: none;"
                + "    border-radius: 4px;"
                + "    cursor: pointer;"
                + "}"
                + "input[type=submit]:hover {"
                + "background-color: #45a049;"
                + "}"
                + "div {"
                + "border-radius: 5px;"
                + "background-color: #f2f2f2;"
                + "padding: 20px;"
                + "}"
                + "</style>"
                + "<body>"
                + "<h2>Calcular el cudrado de un numero</h2>"
                + "<form action=\"/results\">"
                + "  Digite el numero del cual quiere obtener su cuadrado<br>"
                + "  <input type=\"text\" name='numero'>"
                + "  <br>"
                + "  <input type=\"submit\" value=\"Calcular el cuadrado\">"
                + "</form>"
                + "</body>"
                + "</html>";
        return pageContent;
    }

    /**
     * Metodo que retorna un JSON con la respuesta.
     *
     * @param req Es el request de Spark
     * @param res Es el response de Spark
     * @return Retorna un String con el html el cual contiene la respuesta de la
     * media y demas datos.
     */
    private static JSONObject resultsPage(Request req, Response res) {
        int num = Integer.parseInt(req.queryParams("numero"));
        
        JSONArray jsonA = new JSONArray();
        JSONObject jsonO = new JSONObject();
        jsonO.put("numero", num);

        
        return jsonO;
    }

    /**
     * This method reads the default port as specified by the PORT variable in
     * the environment.
     *
     * Heroku provides the port automatically so you need this to run the
     * project on Heroku.
     */
    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567; //returns default port if heroku-port isn't set (i.e. on localhost)
    }
}
