# Test Plan

**Author**: Team 2

## 1 Testing Strategy

### 1.1 Overall strategy

The process of testing will follow a pipeline format of Unit Testing -> Integration  Testing -> System Testing -> Acceptance Testing. Regression testing will be contained in our four testing phases.

Unit Testing: Breaking the program into modules, and test whether each module works properly and returns the expected results.

  1. add a item (check if the item is added)
  2. delete a item (check if the item is deleted)
  3. change item's quantity (check if the quantity of the item has changed )
  4. check the item (check if the item is checked )
  5. uncheck the item (check if the item is unchecked)
  6. search item (check if the item is found ,and if not, add the new item to the database)
  6. clear all checks (check if all checks are cleared)
  7. create a List (check if a list is created)
  8. delete the List (check if the list is deleted)
  9. rename the List (check if the list is renamed)
  10. select the List (check if the list is selected )

Integration Testing: Test the interaction between different modules
  1. add/delete a item and see if it shows up on the grocery list
  2. change the quantity of the item and see if it shows up on the grocery list
  3. check/uncheck the item and see if it shows up on the grocery list
  4. clear all checks and see if it shows up on the grocery list
  5. search item and see if it is shows up in the store(it may also say that there is no such item in the store)
  6. create/delete/rename/select a list and see if it is shows up on the grocery list

System Testing: Test the entire application to see  if it works and fits all requirements
Run multiple times with a different test case each time.

Acceptance Testing: Check that the application can be delivered
Run the app on phone or Android emulator and test it.

### 1.2 Test Selection

Black box: System Testing.
The source code is not visible, the Ul function is visible. It mainly tests whether each function of the application can be used normally

-Testing basic operation such as adding, deleting, and modifying and searching items ensure that application functions properly.

While box: Unit Testing and Integration Testing
The source code is visible, the Ul function is not. It mainly tests whether each function of the application fits all requirements

-Test each function inside the application. By checking the state of the program at different points and determining whether the actual state is consistent with the expected state


### 1.3 Adequacy Criterion

Tests all executable statements within each function and returns a pass or error result.

For example, when we add, delete, modify and search , we can see if the grocery list has changed. If the grocery list is left unchanged, then we can be sure that something went wrong while executing the function, so we need to do error checking


### 1.4 Bug Tracking
Unit Testing: Each component is tested so that if something goes wrong we can quickly figure out which function or part is wrong and fix it.

Integration Testing: If the application passes the Unit test, but fails the integration test. Then we can know which components are failing when we connect them.

System Testing: The unit tests and integration tests passed, but there were errors while running the entire application, and then we figured out what was wrong and fixed it.


### 1.5 Technology
JUnit
In this case, we would test the grocery list management application manually, so each function would tested manually.

## 2 Test Cases



| Test Case ID    | Test Case Description                                        | Pre-Condition                        | Test Case Step                                               | Expected Result           | Actual Result   | Status    |
| --------------- | ------------------------------------------------------------ | ------------------------------------ | ------------------------------------------------------------ | ------------------------- | --------------- | --------- |
| add_list        | User adds a list                                             | Click on the start button            | 1.click the add button 2.type the name of the list 3.click ok | list is added             | is added        | completed |
| delete_list     | User deletes one or more list                                | at least one list                    | 1.select one or more lists 2. click the delete button        | list is deleted           | is deleted      | completed |
| rename_list     | User rename a list name                                      | at least one list                    | 1.select one list 2.click rename button 3. type the name of the list 4. click ok | list is renamed           | is renamed      | completed |
| add_item        | User adds a item to the list                                 | at least one list                    | 1.select one list 2.click the enter the list button 3.click the add item button 4.choose an item from the grocery store 5. add the item to the list | item is added             | is added        | completed |
| delete_item     | User deletes one or more item from the list                  | at least one item in the list        | 1.select one or more items to delete 2.click the delete item button | item is deleted           | is deleted      | completed |
| check_list      | User check one or more lists                                 | at least one list                    | click the checkbox on the left side of the list              | list is checked           | is checked      | completed |
| uncheck_list    | User uncheck one or more lists                               | at least one check list              | click the checkbox on the left side of the check list        | list is unchecked         | is unchecked    | completed |
| check_item      | User check one or more items                                 | at least one item in the list        | 1.click the checkbox on the left side of the item 2.click the check of items button | item is checked           | is checked      | completed |
| uncheck_item    | User uncheck one or more items                               | at least one check items in the list | 1.click the checkbox on the left side of the item 2. click the clear marks button | item is uncheck           | is unchecked    | completed |
| search_item_01  | User search for the item by selecting the item type          | at least one list                    | 1. click add item button 2. click select from the item type button 3. choose a type  4. find the corresponding  item | item is searched          | is searched     | completed |
| search_item_02  | User search for the item by selecting the database           | at lease one list                    | 1. click add item button 2. click select from the database 3. type an item name (type a then apple and pineapple will appear ) 4. find the corresponding item | item is searched          | is searched     | completed |
| search_item_03  | User search for the item by selecting the database           | at least one list                    | 1. click add item button 2. click select from the database 3. type an item name (type a then apple and pineapple will appear ) 4. no find the corresponding item 5. add the item to the database 6. repeat steps 1 through 3 | item is searched          | is searched     | completed |
| change_quantity | User changes the quantity of items that have been added to the list | at least one item in the list        | 1.select one item 2. click change QTT button 3. enter the quantity of modifications | quantity is changed       | is changed      | completed |
| affect_items    | User operation on items in each list do not affect other lists | at least two list                    | 1.add the same item to both list A and  B 2.delete the item from list A 3. Look at this item from list B | other lists is not affect | is not affected | completed |

