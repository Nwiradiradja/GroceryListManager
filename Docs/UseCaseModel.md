# Use Case Model

**Author**: Team 2

## 1 Use Case Diagram
![Use Case Diagram](https://github.com/qc-se-fall2022/370Fall22Sec34Team2/blob/main/PNG/UseCaseDiagram.png?raw=true)


## 2 Use Case Descriptions

Create List

- Requirements: User creates a new empty grocery list in the app. 
- Pre-conditions: No list of the same name exists in the app.
- Post-conditions: A new empty grocery list exists in the app. The name is the input of the user.
- Scenarios: If the user enters the name of the list that exists, it returns an error message. Otherwise, a new empty grocery list is created. The name is the input of the user. List name could be empty.

(Re)Name List

- Requirements: User names or renames a list.
- Pre-conditions: User selects only one list.
- Post-conditions: The selected list will have a new name.
- Scenarios: If user inputs an incorrect list name string, an error is returned. Otherwise, the list's name value will be changed to a new name string by the user's input which must be non-empty.

Select List

- Requirements: The user can select one or more than one lists in the app.
- Pre-conditions: There exists at least one list in the app.
- Post-conditions: The selected lists are chosen.
- Scenarios: If there are no lists then the user cannot select anything, otherwise the user selects one or more lists and then can perform delete or rename on those lists.

Delete List

- Requirements: User can delete one or more selected lists in the app.
- Pre-conditions: There exists at least one selected list object in the app.
- Post-conditions: The list is removed from the app.
- Scenarios: If there are no selected lists then the user cannot perform this action, otherwise the list the user selected is deleted from the app.

Add Items

- Requirements: User adds items to the grocery list.
- Pre-conditions: Use two ways to add times. So, the item type should be correct for select from a hierarchical list, or the name of item should be correct for searching from the database.
- Post-conditions: The item should be added to the grocery list.
- Scenarios: The first possible scenario is that the user will choose a type from a hierarchical list and then look through all items within that chosen type. Then the user can add any item shown to the list and chose the quantity of those items. The second possible scenario is that the user will search for a specific item by its name in the database and if that name is correct, as in the item is in the database, then the database will return all similar items to that input and the user can add the items and choose the quantity of the items to their list. If the input is a valid item name but isn't in the database then that item will be added to the database and added to the list with the selected quantity. If the inputted string is incorrect then no items will be returned by the database and the user will be allowed to search again. 

Delete Items

- Requirements: User deletes an item from the list.
- Pre-conditions: There must exist at least one item in the list.
- Post-conditions: The item is removed from the list.
- Scenarios: If there are no items in the list then the user cannot perform this action, otherwise the selected item is deleted from the list.

Change Quantity Of Items

- Requirements: User changes the quantity of existing items in the list.
- Pre-conditions: There exists at least one item in the list.
- Post-conditions: The selected item's quantity in the list is changed.
- Scenarios: The first possible scenario is if the user inputs an invalid quantity, an integer less than 1, then an error will be returned. The second possible scenario is if there are no items in the list then the user cannot perform this action, otherwise the user will select an item in the list and be able to change its quantity. The selected item will now have the updated quantity.

Check Items

- Requirements: The user selects the item and checks it off in the list.
- Pre-conditions: There exists at least one items in the list. The item should be selected and not checked.
- Post-conditions: The specified item is marked.
- Scenarios: One possible scenario is if there are no items in the list then the user cannot perform this action. The other possible scenario is if the selected item is already checked then nothing happens, otherwise the selected item is checked-off.

Clean All Check-Off Marks

- Requirements: User removes all check marks off the items in the list.
- Pre-conditions: There must exist at least one checked item in the list.
- Post-conditions: All check marks will be cleared from the list.
- Scenarios: One possible scenario is if the list is empty then the user cannot perform this action. If all of the items in the list are already unchecked then nothing happens. If there are checked-off items in the list then the check marks are all cleared and the Boolean values for all items in the list will be set to false.

Search Item

- Requirements: The user wants to add the specified item, enter the name of the item and search from the database.
- Pre-conditions: The name of the item cannot be empty.
- Post-conditions: The item may or may not match existing items in the database.
- Scenarios: If the item doesn't match to anything in the database then it's saved to the database and added to the user's list. Otherwise, similar items are returned by the database and the user can then select them to be added to the list.

Select Item Type

- Requirements: The user wants to add item from the hierarchical list, selecting the type of item is the first step.
- Pre-conditions: There exists a list.
- Post-conditions: The UI shows all items belonging to the selected type.
- Scenarios: If there are no existing lists then the user cannot perform this action. If the desired type is not in the database then the user can add that type to the database. Otherwise, the user searches through a list of item types and selects one of them. All available items within that type are then shown to the user and then they add any of those items to the list.

Select Item

- Requirements: The user wants to add item from the hierarchical list, after selecting the item type, the user selects the item as the second step.
- Pre-conditions: There exists a list, and type of item exists.
- Post-conditions: The item will be added to the list.
- Scenarios: If there are no existing lists then the user cannot perform this action. If the selected item does not exist then the user can use another way to search again in the database. Otherwise, the item is added to the list.

Save New Item

- Requirements: The user cannot match the item name in the database, so he needs to save it with its type in the database.
- Pre-conditions: The item cannot exist in the database.
- Post-conditions: A new item with its type is added to the database.
- Scenarios: If the database cannot be connected to then an error is returned, otherwise the searched item that didn't exist is then saved to the database.

Display Items With Similar Names

- Requirements: The user search item from the database. The app returns items that match or partially-match the user's input.
- Pre-conditions: The search part cannot be empty.
- Post-conditions: Any items that at least partially-match the user's input will be displayed by the database.
- Scenarios: If the user inputted an empty string then an error will be returned. Otherwise, the database will display all items that at least partially-match the user's input and if there are no items that match then nothing will be displayed.

Select Items From Search

- Requirements: The user search items from the database. The app returns similar items for user to select.
- Pre-conditions: The user's input cannot be null.
- Post-conditions: The item is added to the list.
- Scenarios: If the user's input is null then an error is returned and the user can search again. Otherwise, the selected item is added to the list.
