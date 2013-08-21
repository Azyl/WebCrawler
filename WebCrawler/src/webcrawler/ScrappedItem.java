/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package webcrawler;

import java.util.HashMap;

/**
 *
 * @author Azyl
 */
public class ScrappedItem {

    /**
     *
     * @param nume_oferta   Numele ofertei
     * @param link_detalii  Link catre pagina cu detalli a ofertei
     */
    public ScrappedItem(String nume_oferta, String link_detalii) {
        
        HashMap hm = new HashMap();
        hm.put("nume_oferta",nume_oferta);
        hm.put("link_detalii", link_detalii);
    }
    
    
}