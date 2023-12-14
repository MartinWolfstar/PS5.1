/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.binder.projets5.mavenproject1;


import com.vaadin.flow.server.VaadinSession;
    import java.sql.Connection;
    import java.util.Optional;

/**
 *
 * @author abinder01
 */
public class Session{
    
    private VaadinSession vsession; 
    private Connection connBDD;
    private Optional<Client> client;

    public Session(Connection connBDD, Optional<Client> client) {
        this.
        this.connBDD = connBDD;
        this.client = client;
    }
    // Récupérer la session actuelle
VaadinSession vaadinSession = VaadinSession.getCurrent();

// Ajouter un attribut à la session
vaadinSession.setAttribute("nom_attribut", valeur);

// Récupérer la valeur de l'attribut de session
Object valeurAttribut = vaadinSession.getAttribute("nom_attribut");

// Supprimer un attribut de session
vaadinSession.removeAttribute("nom_attribut");
    public Session(Connection connBDD) {
        this(connBDD,Optional.empty());
    }

    /**
     * @return the connBDD
     */
    public Connection getConnBDD() {
        return connBDD;
    }

    /**
     * @return the client
     */
    public Optional<Client> getCurUser() {
        return client;
    }

    /**
     * @param client the client to set
     */
    public void setCurUser(Optional<Client> client) {
        this.client = client;
    }
    
    public boolean isUserLogged() {
        return this.client.isPresent();
    }
    
    public void login(Client u) {
        this.client = Optional.of(u);
    }
    
    public void logout() {
        this.client = Optional.empty();
    }
      
}
