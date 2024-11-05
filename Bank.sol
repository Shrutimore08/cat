// SPDX-License-Identifier: MIT
pragma solidity ^0.8.18;
contract Bank {
    mapping(address => uint) private balances;
    event Deposit(address indexed account, uint amount);
    event Withdrawal(address indexed account, uint amount);
    function deposit(uint amount) public {
        require(amount > 0, "Amount must be greater than zero.");
        balances[msg.sender] += amount;
        emit Deposit(msg.sender, amount);
    }
    function withdraw(uint amount) public {
        require(amount > 0, "Amount must be greater than zero.");
        require(balances[msg.sender] >= amount, "Insufficient balance.");
        balances[msg.sender] -= amount;
        emit Withdrawal(msg.sender, amount);
    }
    function getBalance() public view returns (uint) {
        return balances[msg.sender];
    }
}

## Blockchain Context
Decentralization and Transparency: Once deployed, this contract is immutable on the Ethereum blockchain. Users can interact with it, and their interactions (like deposits and withdrawals) are recorded transparently.
Security and Trust: The code enforces rules, like checking for positive amounts and sufficient balances before withdrawal. Since it runs on a blockchain, users don’t need to trust a central authority—they can rely on the smart contract’s predefined rules.
Events: Events are stored in transaction logs, which can be useful for tracking deposits and withdrawals without changing the state of the contract.
Summary
This Bank.sol contract represents a simple bank system on the Ethereum blockchain, where users can deposit, withdraw, and check their balances. All user interactions are recorded on the blockchain, making them permanent and transparent. The contract’s functions enforce basic rules for secure transactions, and events provide a way to track these transactions in the blockchain’s transaction log.