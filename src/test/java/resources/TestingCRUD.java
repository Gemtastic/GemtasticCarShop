/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import com.gemtastic.carshop.tables.records.AddressRecord;
import com.gemtastic.carshop.tables.records.CustomerRecord;
import services.CRUD.AddressCRUDService;
import services.CRUD.CustomerCRUDService;

/**
 *
 * @author Gemtastic
 */
public class TestingCRUD {
    
    public static void main(String[] args){
        CustomerCRUDService c = new CustomerCRUDService();
        AddressCRUDService a = new AddressCRUDService();
        
        
        CustomerRecord cr = c.read(4);
        AddressRecord ar = a.read(cr.getAddress());
        System.out.println(cr + "\n" + ar);
    }
    
    
}
