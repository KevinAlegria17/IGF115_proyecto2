/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Planificacion;

import Modelos.Conectar;
import componentes.DefinicionComponentePlan;
import componentes.Planificacion.Compra;
import componentes.Existencia.ExistenciaMaterialMedico;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Kevin Posada Alegría
 */
public class CompraController {
    private JdbcTemplate jdbcTemplate;
    
    public CompraController() {
        this.jdbcTemplate =  new JdbcTemplate(Conectar.conectar());
    }
    
    public ExistenciaMaterialMedico selectMaterial(int id){
        final ExistenciaMaterialMedico material = new ExistenciaMaterialMedico();
        String consulta = "Select * from existenciamaterialmedico where ID_EXIST="+id;
        return (ExistenciaMaterialMedico) jdbcTemplate.query
        (
                consulta, new ResultSetExtractor<ExistenciaMaterialMedico>()
            {
               public ExistenciaMaterialMedico extractData(ResultSet rs)throws SQLException, DataAccessException{
                   if(rs.next())
                   {
                       material.setId(rs.getInt("ID_EXIST"));
                       material.setCodigo(rs.getInt("CODIGO_EXIST"));
                       material.setFechaConteo(rs.getString("FECHACONTEO"));
                       material.setCantidadExistencia(rs.getInt("CANTIDADEXISTENCIA"));
                       material.setCantidadFaltante(rs.getInt("CANTIDADFALTANTE"));
                       material.setObservacion(rs.getString("OBSERVACION"));
                       material.setNombreMaterial(rs.getString("NOMBRE_MATERIAL"));
                   }
                   return material;
               } 
            }
        );
    }
    
    @RequestMapping(value = "Compras.htm", method = RequestMethod.GET)
    public ModelAndView Compras() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Planificacion/Compras");
        String sql = "select * from existenciamaterialmedico ORDER BY FECHACONTEO DESC";
        List<String> materialeserror = new ArrayList<>();
        List materiales = null;
        try{
            materiales = this.jdbcTemplate.queryForList(sql);
        }catch(Exception ex){
            materialeserror.add("Error");
            materialeserror.add("No hay acceso a la base");
            materialeserror.add(ex.toString());
            
        }
        
        mav.addObject("materiales", materiales);
        mav.addObject("materialeserror", materialeserror);
        return mav;
    }
    
    @RequestMapping(value = "CompraAdd.htm", method = RequestMethod.GET)
    public ModelAndView CompraAdd(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        int id = Integer.parseInt(request.getParameter("id"));
        ExistenciaMaterialMedico material = selectMaterial(id);
        String valores[] = new String[2];
        int valor=0;
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
                    JSONArray jsonarray = new JSONArray(output);
                    for (int i = 0; i < jsonarray.length(); i++) {
                        JSONObject jsonobject = jsonarray.getJSONObject(i);
                        if((jsonobject.getString("protocolo")).equals(material.getNombreMaterial())){
                            int cantidadProtocolo = Integer.parseInt(jsonobject.getString("cantidad"));
                            if(material.getCantidadExistencia()<cantidadProtocolo){
                                valor = cantidadProtocolo - material.getCantidadExistencia();
                                valores[0]="Hacen falta";
                                valores[1]=Integer.toString(valor);
                            }else{
                                valor = material.getCantidadExistencia() - cantidadProtocolo;
                                valores[0]="Sobran";
                                valores[1]=Integer.toString(valor);
                            }
                        }
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Error al leer el JSON");
        }
        mav.setViewName("Planificacion/CompraAdd");
        mav.addObject("valores", valores);
        mav.addObject("compra", new Compra());
        
        return mav;
    }
    
    @RequestMapping(value = "CompraAdd.htm", method = RequestMethod.POST)
    public ModelAndView compraAddPost(@ModelAttribute("compra") Compra compra,
            BindingResult result,
            SessionStatus status, 
            HttpServletRequest request
    ) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Planificacion/CompraAdd");
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 8000);
            DefinicionComponentePlan definicionPlan = (DefinicionComponentePlan) registry.lookup("plan");

            String response = definicionPlan.insertarCompra(compra.getIdPlanificacion(),compra.getPeriodo(), compra.getFechaInicio(), compra.getFechaFin());
            mav.addObject("respuesta", response);
        } catch (Exception ex) {
            Logger.getLogger("Error");
            String respuesta = "Servicio no disponible \n" + ex.toString();
            mav.addObject("respuesta", respuesta);
        }
        return mav;
    }
}
