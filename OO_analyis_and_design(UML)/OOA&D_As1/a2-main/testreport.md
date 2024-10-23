# Stuff Lending Test Report
Document the results of your final system test below. You find instructions on the course homepage.

The app is working pretty good, but one of the requiremnets it does not work, that is the advance day. Sometimes when we run the app, the systm exit the member menu and go back to the main menu instead.

Version: #134915

Date: 2023.02.22.

Environment: Windows 11, Java version 18. Samah

Environment: Windows 11, Java version 18. Kim 

Performed by: 
Samah Diab sd222ti 

Kim Nygren kn222fk

## 5.1 Member Data


| Case    | Result      | Note     |
| ------------- | ------------- | -------- |
| 5.1          | OK         |  |
| 5.2          | Ok        | the creditas for items and member------>fixes  |
| 5.3          | Ok        | the member has 0 credits---->Fixed  |
| 5.4          | Ok        | the creditas for items and member--->Fixed  |




## 1.1 Create Member


| Case    | Result      | Note     |
| ------------- | ------------- | -------- |
| 1.1          | OK         |  |
| 1.2          | ok        |   |
| 1.3          | ok        |   |



## 1.2 Create Member - Duplicate Email and Phone


| Case    | Result      | Note     |
| ------------- | ------------- | -------- |
| 1          | OK         |  |
| 2          | Ok        |   |
| 3          | Ok        |  |
| 4          | Ok        |  |
| 5        | Ok        |  |
| 6        | Ok        |  |
| 7        | Ok        |  |
| 8        | Ok        |  |
| 9        | Ok        |  |


The app was working petty good when we did the above parts of test. The sytem can create a new memebr with a name, email, phone number that the user should enter, then the app will generate an id. When you enter the same email or the same phone number that are already existed in the syetem, the app refuse to duplicate the email or phone number. the app can quit the app and gives a nice output. 


## 1.3 Delete Member


| Case    | Result      | Note     |
| ------------- | ------------- | -------- |
| 1          | OK         |  |
| 2          | Ok        |   |
| 3          | Ok        |  |
| 4          | Ok        |  |
| 5        | Ok        |  |
| 6        | Ok        |  |
| 7        | Ok        |  | 




## 2.1 Create item

| Case    | Result      | Note     |
| ------------- | ------------- | -------- |
| 1          |  OK         |  |
| 2          |  Ok      |   |
| 3          |  Ok        |  |



## 2.2 Delete item

| Case    | Result      | Note     |
| ------------- | ------------- | -------- |
| 1          |  OK         |  |
| 2          |  Ok      |   |
| 3          |  Ok        |  |




## 2.3 Delete Item

| Case    | Result      | Note     |
| ------------- | ------------- | -------- |
| 1          |  OK         |  |
| 2          |  Ok      |   |
| 3          |  Ok        |  |


We decided that this case has a condition, that is when the item is lended by some memeber, and has a contract, so the system will not delete this item. And that differs from the requirements.

## 3.1 Create Contract

| Case    | Result      | Note     |
| ------------- | ------------- | -------- |
| 1          |  OK         |  |
| 2          |  Ok      |   |
 


## 3.2 Create Contract - not enough funds

| Case    | Result      | Note     |
| ------------- | ------------- | -------- |
| 1          |  OK         |  |
| 2          |  Ok      |   |



## 3.3 Create Contract - conflicting time

| Case    | Result      | Note     |
| ------------- | ------------- | -------- |
| 1          |  OK         |  |
| 2          |  Ok      | 




## 3.4 Create Contract_Conflecting time

| Case    | Result      | Note     |
| ------------- | ------------- | -------- |
| 1          |  OK         |  |
| 2          |  Ok      |   |



## 3.5 Create Contract_Conflecting time


| Case    | Result      | Note     |
| ------------- | ------------- | -------- |
| 1          |  OK         |  |
| 2          |  Ok      |   |


## 3.6 Create Contract_Conflecting time


| Case    | Result      | Note     |
| ------------- | ------------- | -------- |
| 1          |  OK         |  |
| 2          |  Ok      |   |


## Advance Time


| Case    | Result      | Note     |
| ------------- | ------------- | -------- |
| 1          |  OK         |Can move the day to be day 8 in system  |
| 2          |  not ok |The borrower's credit can only be deduced after the contract is etablished (same day)|


The advance time is 8, so the item 2 is lended from day 5 to day 7 , then the item 2 is available in the system to lend. The second reqiurement is that the system should get the credits after the last day of the contract, but in our app the system gets the credits from the member in the same day of the contract creation. 

## Item name - unique for the owning member
The new requirement states that "Item name needs to be unique for the owning member. That is, there cannot be two items with the same name owned by the same member." 
So we interpret that one owner can not have 2 items with the same name. 
| Case    | Result      | Note     |
| ------------- | ------------- | -------- |
| 1          |  OK         |We can not add the new item if it has the same name of the existing item  by the same owner|
| 2          |  OK         |we cannot change an items name if this name already exists by the same owner |
| 3        |  OK         |we add ourself as a member and add 2 new items to us. The first item can not have the same name as the second item |

## English View 
| Case    | Result      | Note     |
| ------------- | ------------- | -------- |
| 1          |  OK         |The lists will be sorted using the id of the member.|
| 2          |  OK         |The menus have ways of input by using numbers|

## Swedish View 
| Case    | Result      | Note     |
| ------------- | ------------- | -------- |
| 1          |  OK         |The lists will be sorted using the name of the member.|
| 2          |  OK         |The menus have ways of input by using characters|

