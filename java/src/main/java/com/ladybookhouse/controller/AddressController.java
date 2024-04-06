package com.ladybookhouse.controller;


import com.ladybookhouse.dao.AddressDao;
import com.ladybookhouse.model.Address;
import com.ladybookhouse.model.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class AddressController {

    private AddressDao addressDao;

    public AddressController(AddressDao addressDao){
        this.addressDao =addressDao;
    }


//    @RequestMapping(path="/your-address", method= RequestMethod.GET)
//    public ResponseEntity<List<Address>> getAddresses(Principal principal, @RequestParam(value= "saveAddress", defaultValue= "false") boolean saveAddress) {
//        if(principal == null){
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
//        }
//        String email= principal.getName();
//        System.out.println(principal);
//        System.out.println(email);
//        List<Address> addresses = addressDao.getAddressesBySaveAddress(email, saveAddress);
//        if(addresses.isEmpty()){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//        return ResponseEntity.ok(addresses);
//    }

    @RequestMapping(path="/your-address", method = RequestMethod.GET)
    public List<Address> getAddresses(Principal principal){

        List<Address> addresses;
        String email= principal.getName();
        System.out.println(email);
        addresses = addressDao.getAddressesBySaveAddress(email);
        return addresses;

    }

//    @DeleteMapping(path= "/removeAddress")
//    public boolean deleteBook(Principal principal, @RequestParam int addressId){
//        String email = principal.getName();
//        System.out.println("addressId: " + addressId + " email: " + email);
//        return addressDao.deleteAddress(addressId, email);
//    }


}
