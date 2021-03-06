/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.proyecto.controler;

import ec.edu.ups.proyecto.business.TrabajadorON;
import ec.edu.ups.proyecto.emtitis.Trabajador;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Vinicio
 */
;

@ManagedBean
@ViewScoped
public class TrabajadorBEAN {

    private Trabajador newTrabajador;
    private Trabajador auxTrabajador;
    private List<Trabajador> listaTrabajadores;
    private ArrayList<String> listaOpc;
    private String textoBuscar;
    private String mensaje;

    public TrabajadorBEAN() {
    }
    
    

    @Inject
    private TrabajadorON trabajadorON;

    @PostConstruct
    public void init() {
        newTrabajador = new Trabajador();
        
        listaOpc = new ArrayList<>();
        listaOpc.add("Administrador");
        listaOpc.add("Cajero");
        listaOpc.add("Jefe Credito");
        listaTrabajadores = trabajadorON.listaTrabajadores();
        
        textoBuscar = "";
        auxTrabajador = new Trabajador();
    }
    /**
     * Permiten guardar trabajador
     * Consume la logica de negocio del on
     * @return
     */
    public String guardarTrabajador() {
        try {
            trabajadorON.guardarTrabajador(newTrabajador);
            init();
        } catch (Exception ex) {
            mensaje = ex.getMessage();
            Logger.getLogger(TrabajadorBEAN.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    /**
     * Busca trabajadores desde el texto ingresado 
     * desde la vista
     * @return
     */
    public String buscaTrabajadores() {
        System.out.println(textoBuscar);
        try {
            listaTrabajadores = trabajadorON.listaTrabajadoresCodigo(textoBuscar);
        } catch (Exception ex) {
            Logger.getLogger(TrabajadorBEAN.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    /**
     * Buscar trabajador mediante el id
     * 
     * @param id
     * @return
     */
    public String buscaTrabajadorID(String id) {
        try {
             auxTrabajador = trabajadorON.buscarTrabajador(id);
             System.out.println("hireS");
        } catch (Exception ex) {
            Logger.getLogger(TrabajadorBEAN.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    /**
     * Actualiza el trabajador pasandole el auxtrabajador
     * y llama al init
     * @return
     */
    public String actualizarTrabajador(){
        trabajadorON.actualizarTrabajador(auxTrabajador);
        init();
        System.out.println("actualizado");
        return null;
    }
    /**
     * Elimina trabajador le pasa al objeto de negocio 
     * el objeto trabajador
     * 
     * @return
     */
    public String eliminarTrabajador(){
        auxTrabajador.setEliminado(true);
        trabajadorON.actualizarTrabajador(auxTrabajador);
        init();
        System.out.println("Eliminado");
        return null;
    }
    
    // -------------------> 
    public Trabajador getNewTrabajador() {
        return newTrabajador;
    }

    public void setNewTrabajador(Trabajador newTrabajador) {
        this.newTrabajador = newTrabajador;
    }

    public TrabajadorON getTrabajadorON() {
        return trabajadorON;
    }

    public void setTrabajadorON(TrabajadorON trabajadorON) {
        this.trabajadorON = trabajadorON;
    }

    public ArrayList<String> getListaOpc() {
        return listaOpc;
    }

    public void setListaOpc(ArrayList<String> listaOpc) {
        this.listaOpc = listaOpc;
    }

    public List<Trabajador> getListaTrabajadores() {
        return listaTrabajadores;
    }

    public void setListaTrabajadores(List<Trabajador> listaTrabajadores) {
        this.listaTrabajadores = listaTrabajadores;
    }

    public String getTextoBuscar() {
        return textoBuscar;
    }

    public void setTextoBuscar(String textoBuscar) {
        this.textoBuscar = textoBuscar;
    }

    public Trabajador getAuxTrabajador() {
        return auxTrabajador;
    }

    public void setAuxTrabajador(Trabajador auxTrabajador) {
        this.auxTrabajador = auxTrabajador;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    
}
