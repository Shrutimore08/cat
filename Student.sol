// SPDX-License-Identifier: MIT
pragma solidity ^0.8.18;
contract StudentData {
    struct Student {
        uint id;
        string name;
        uint age;
    }
    Student[] public students;
    event StudentAdded(uint id, string name, uint age);
    // Function to add a new student
    function addStudent(uint _id, string memory _name, uint _age) public {
        Student memory newStudent = Student({
            id: _id,
            name: _name,
            age: _age
        });
        students.push(newStudent);
        emit StudentAdded(_id, _name, _age);
    }
    // Function to get student details by index
    function getStudent(uint index) public view returns (uint, string memory, uint) {
        require(index < students.length, "Student not found.");
        Student memory student = students[index];
        return (student.id, student.name, student.age);
    }
    // Fallback function to handle unexpected calls
    fallback() external {
        // This function can be used to log or handle unexpected calls
    }
}


##
The StudentData smart contract is a Solidity-based contract deployed on the Ethereum blockchain that stores information about students and allows for retrieval of this data. Below is an explanation of each component and function in the contract.

Key Components of the Contract
SPDX License Identifier

// SPDX-License-Identifier: MIT: This is a license identifier that specifies the contract’s license type, making it open-source under the MIT license. This identifier helps tools and users know how they can legally interact with and reuse the code.
Pragma Directive

pragma solidity ^0.8.18;: This specifies the version of Solidity required to compile the contract. In this case, any version 0.8.18 or above is acceptable.
Contract Definition

contract StudentData { ... }: Defines the main contract named StudentData. Contracts in Solidity are similar to classes in object-oriented programming and contain state variables, structs, functions, and events.
Structs

struct Student { ... }: Defines a data structure named Student to hold student details, including:
uint id: A unique identifier for each student.
string name: The student’s name.
uint age: The student’s age.
State Variables

Student[] public students;: An array of Student structs, where each entry stores information for a particular student. The public modifier makes this array accessible from outside the contract (though limited for arrays), allowing users to view its contents.
Events

event StudentAdded(uint id, string name, uint age);: Defines an event called StudentAdded. Events in Solidity are a form of logging that can be listened for by off-chain applications (like web interfaces) to know when certain actions happen within the contract.

