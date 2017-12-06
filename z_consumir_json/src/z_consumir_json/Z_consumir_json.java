/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package z_consumir_json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Kevin Posada Alegría
 */
public class Z_consumir_json {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            String webservice = "http://localhost/JSON_IGF115_Proyecto1/consulta.php";
            URL url = new URL(webservice);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
                String output;
                System.out.println("output is --------");
                while ((output = br.readLine()) != null) {
                    System.out.println(output);
                    //usando el JAR de json
                    JSONArray jsonarray = new JSONArray(output);
                    for (int i = 0; i < jsonarray.length(); i++) {
                        JSONObject jsonobject = jsonarray.getJSONObject(i);
                        String id = jsonobject.getString("id");
                        System.out.print("\nID: "+id);
                        String area = jsonobject.getString("area");
                        System.out.print("\tArea: "+area);
                        String personal = jsonobject.getString("personal");
                        System.out.print("\tPersonal disponible: "+personal);
                        String porcentaje = jsonobject.getString("porcentaje");
                        System.out.print("\tPorcentaje: "+porcentaje);
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Error al leer el JSON");
        }
    }

}
