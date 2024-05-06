package org.amaap.unusualspends.repository;

import org.amaap.unusualspends.domain.model.entity.Customer;

public interface CustomerRepository {
    Customer insert(Customer customer);

    void update(Customer customer);
}
