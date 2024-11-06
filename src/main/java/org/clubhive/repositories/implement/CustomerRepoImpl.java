package org.clubhive.repositories.implement;

import lombok.RequiredArgsConstructor;
import org.clubhive.entities.UserEntity;
import org.clubhive.model.Customer;
import org.clubhive.repositories.UserRepositoryImplementation;
import org.clubhive.repositories.jpa.CustomerRepository;
import org.clubhive.utils.CustomerMapper;
import org.clubhive.utils.GenericMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class CustomerRepoImpl implements UserRepositoryImplementation<Customer> {
    private final CustomerRepository customerRepository;

    @Override
    public Customer save(Customer customer) {
        UserEntity userEntity = CustomerMapper.mapToEntity(customer);

        return CustomerMapper.mapToModel(customerRepository.save(userEntity));

    }

    @Override
    public Customer update(Customer customer){

        Customer customerToUpdate = findByUserId(customer.getUserId());

        customerToUpdate.setName((customer.getName() != null) ? customer.getName(): customerToUpdate.getName());
        customerToUpdate.setPhone((customer.getPhone() != null) ? customer.getPhone() : customerToUpdate.getPhone());

        return save(customerToUpdate);
    }

    @Override
    public List<Customer> findAll(){
        return customerRepository.findAll().parallelStream().map(CustomerMapper::mapToModel).toList();
    }

    @Override
    public Customer findByEmail(String email) {
        return CustomerMapper.mapToModel(customerRepository.findByEmail(email));
    }

    @Override
    public Customer findByUserId(String userId) {
        return CustomerMapper.mapToModel(customerRepository.findByUserId(userId));
    }

}
