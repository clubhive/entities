package org.clubhive.utils;

import org.clubhive.DTO.CustomerDTO;
import org.clubhive.entities.UserEntity;
import org.clubhive.model.Customer;

public class CustomerMapper {

    public static CustomerDTO mapToDTO(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setDni(customer.getDni());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setName(customer.getName());
        customerDTO.setPhone(customer.getPhone());

        return customerDTO;
    }

    public static UserEntity mapToEntity(Customer customer){
        UserEntity userEntity = new UserEntity();

        userEntity.setDni(customer.getDni());
        userEntity.setEmail(customer.getEmail());
        userEntity.setName(customer.getName());
        userEntity.setPhone(customer.getPhone());
        userEntity.setUserId(customer.getUserId());

        return userEntity;
    }

    public static Customer mapToModel(CustomerDTO customerDTO){
        Customer customer = new Customer();

        customer.setDni(customerDTO.getDni());
        customer.setEmail(customerDTO.getEmail());
        customer.setName(customerDTO.getName());
        customer.setPhone(customerDTO.getPhone());
        customer.setPassword(customerDTO.getPassword());

        return customer;
    }

    public static Customer mapToModel(UserEntity userEntity){
        Customer customer = new Customer();

        customer.setDni(userEntity.getDni());
        customer.setEmail(userEntity.getEmail());
        customer.setName(userEntity.getName());
        customer.setPhone(userEntity.getPhone());
        customer.setUserId(userEntity.getUserId());

        return customer;
    }

}
