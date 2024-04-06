package com.ladybookhouse.dao;

import com.ladybookhouse.model.Address;

import java.util.List;

public interface AddressDao {

     List<Address> getAddressesByEmail(String Email);

     boolean deleteAddress(int addressId, String email);

     Address fetchAddressByAddressId(int addressId);

     List<Address> getAddressesBySaveAddress(String email);

}
