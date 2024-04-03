package com.ladybookhouse.controller;


import com.ladybookhouse.dao.AddressDao;
import com.ladybookhouse.model.Address;
import com.ladybookhouse.model.Order;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
public class AddressController {

    private AddressDao addressDao;

    public AddressController(AddressDao addressDao){
        this.addressDao =addressDao;
    }


    @RequestMapping(path="/your-address", method= RequestMethod.GET)
    public List<Address> getAddresses(Principal principal) {
        String email= principal.getName();
        System.out.println(principal);
        System.out.println(email);
        return addressDao.getAddressesByEmail(email);
    }

    @DeleteMapping(path= "/removeAddress")
    public boolean deleteBook(Principal principal, @RequestParam int addressId){
        String email = principal.getName();
        System.out.println("addressId: " + addressId + " email: " + email);
        return addressDao.deleteAddress(addressId, email);
    }


}
