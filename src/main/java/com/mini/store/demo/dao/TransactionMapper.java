package com.mini.store.demo.dao;

import com.mini.store.demo.model.Transaction;

public interface TransactionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table transactions
     *
     * @mbg.generated Thu Apr 02 21:30:14 CST 2020
     */
    int deleteByPrimaryKey(Integer transactionId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table transactions
     *
     * @mbg.generated Thu Apr 02 21:30:14 CST 2020
     */
    int insert(Transaction record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table transactions
     *
     * @mbg.generated Thu Apr 02 21:30:14 CST 2020
     */
    int insertSelective(Transaction record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table transactions
     *
     * @mbg.generated Thu Apr 02 21:30:14 CST 2020
     */
    Transaction selectByPrimaryKey(Integer transactionId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table transactions
     *
     * @mbg.generated Thu Apr 02 21:30:14 CST 2020
     */
    int updateByPrimaryKeySelective(Transaction record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table transactions
     *
     * @mbg.generated Thu Apr 02 21:30:14 CST 2020
     */
    int updateByPrimaryKey(Transaction record);
}