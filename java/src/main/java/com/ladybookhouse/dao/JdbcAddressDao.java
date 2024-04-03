package com.ladybookhouse.dao;

import com.ladybookhouse.model.Address;
import com.ladybookhouse.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Component
public class JdbcAddressDao implements AddressDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcAddressDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate =jdbcTemplate;
    }


    @Override
    public List<Address> getAddressesByEmail(String Email) {
        List<Address> addressList = new ArrayList<>();

        String addressSql = "SELECT * FROM address where email = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(addressSql, Email);
        while(results.next()){
           Address address = mapRowToAddress(results);
            System.out.println(address.getFirstname());
           addressList.add(address);

        }
        return addressList;
    }

    @Override
    public boolean deleteAddress(int addressId, String email) {
        String deleteReferencesSql = "DELETE FROM user_addresses WHERE address_id = ?";
        jdbcTemplate.update(deleteReferencesSql, addressId);
        
        String sql= "DELETE from address where email= ? and address_id= ?";

        int rowsEffected = jdbcTemplate.update(sql, email, addressId);
        return rowsEffected > 0;
    }

    private Address mapRowToAddress(SqlRowSet rs) {
        Address address = new Address();
        address.setAddressId(rs.getInt("address_id"));
        address.setFirstname(rs.getString("firstname"));
        address.setLastname(rs.getString("lastname"));
        address.setCountry(rs.getString("country"));
        address.setCity(rs.getString("city"));
        address.setAddressLine(rs.getString("address"));
        address.setState(rs.getString("state"));
        address.setEmail(rs.getString("email"));
        address.setZipcode(rs.getString("zipcode"));
        address.setPhoneNumber(rs.getString("phoneNumber"));

        return address;
    }
}
