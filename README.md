# CommunicataionAndroid Project

# Getting Started

* Make sure you have android studio installed on your computer.
* Start the server on URL - https://github.com/Sagi1500/CommunicationAndroid.git
* Clone this repository.
* Open the cloned project on your android studio and connect to firebase **(Tools -> Firebase -> Cloud messaging -> Connect to firebase with:
We will give an authorization for connecting our Firebase to your android studio).
<br> Please confirm our invitation from shovala1000@gmail.com.**
<br>To open the app, open the cloned project on your android studio and click play.
*Now you are ready to start! 

# The app contains few activitys

## Login Activity
* The activity is the first activity that opens when the user is opening the app. 
* This activity allows users who are already connected to the app to connect and have conversations with their contacts.
* This activity contains a button that allows new users to move to the signup activity to register as a new client for the app.
  Once successfully logged in, you will navigate to the contact list activity.
  
## Sign-Up Activity
* This activity allows users that didn't already sign up to create their new accounts.
* To complete the registration, you must fill the Username, Password, and Confirm Password filed. The profile picture is not mandatory to complete the process.
* If one of the required details is uncompleted or invalid toast message will appear on the screen when trying to submit the files.
* After successfully registering, you will navigate to the contact list screen.
### Fileds requirements
  - The username must contain at least one letter and one number.
  - The password must contain at least one letter and one number.
  - The confirmed password must be equal to the password.

## Contact List Activity
* Once this activity opens, all the contacts will appear as a list.
* To chat with one of the contacts from the list. press on his item on the list, and you will be transferred to Chat activity.
* At the bottom of the page, there are 3 buttons.
  - On the left side - the logout button - this button will close the activity and open the login activity.
  - In the middle - the settings button - this button will open the Settings activity.
  - On the right side - the add new contact button - this button will open the add contact activity.
  
## Add Contact Activity
You can add a contact by inserting the following:
- Username - the username of the contact you wish to add.
- Nickname - the name that appears on the contact list for the user.
- Server - the server you want to connect to. **NOTICE - you must enter "localhost: port" Example localhost:7049.**

## Chat Activity
* This activity will open the user press on an item from the contact list.
* This activity allows sending a message to the other user. 
* Note that you can't send an empty text message.

## Settings Activity 
This activity allows the user to update the current fields:
* ServerURL - change the port for the server for the entire app (not per user). **NOTICE - you must enter "localhost: port" Example localhost:7049.**
* Contact's name- when pressing the button, a list that contains all the contact's nicknames will appear. After picking a specific contact name, you can change his nickname in your account.
* Image - you can change your profile picture.
Note - you can choose what fields you would like to edit. There is no connection between the three options.
