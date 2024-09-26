/**
 * 
 */
package com.example.demo.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AccountDTO;

/**
 * 
 */
@RestController
public class AccountController {
	 @Autowired
	    private DataSource dataSource;

	    @GetMapping("/accounts")
	    public List<AccountDTO> findAccountsByCustomerId(@RequestParam String customerId) throws SQLException {
	        String sql = "SELECT customer_id, acc_number, branch_id, balance FROM Accounts WHERE customer_id = ?";
	        Connection connection = dataSource.getConnection();
	        PreparedStatement preparedStatement = connection.prepareStatement(sql);
	        preparedStatement.setString(1, customerId);
	        ResultSet resultSet = preparedStatement.executeQuery();

	        List<AccountDTO> accounts = new ArrayList<>();
	        while (resultSet.next()) {
	            AccountDTO account = new AccountDTO();	           
	            account.setCustomerId(resultSet.getString("customer_id"));
	            account.setAccountNumber(resultSet.getString("acc_number"));
	            account.setBranchId(resultSet.getString("branch_id"));
	            account.setBalance(resultSet.getDouble("balance"));
	            accounts.add(account);
	        }
	        return accounts;
	    }
	

}
