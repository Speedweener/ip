
# Duke User Guide

Duke is a **Command Line Interface** (CLI) app which allows you to store different tasks. 
Designed for *fast* users, Duke allows you to quickly **store**, **find**, and **mark** 
your tasks.


* [Quick Start](#quick-start)  
* [Features](#features)
	* [Print help information:](#print-help-information-help) **help**
    * [List out all existing task:](#list-out-all-existing-task-list) **list**
    * [Add a new ToDo task](#add-a-new-todo-task-todo) **todo**
    * [Add a new Deadline task:](#add-a-new-deadline-task-deadline) **deadline**
    * [Add a new Event task:](#add-a-new-event-task-event) **event**
    * [Mark a task as done:](#mark-a-task-as-done-done) **done**
    * [Delete a task:](#delete-a-task-delete) **delete**
    * [Find tasks using keyword:](#find-tasks-using-keyword-\`find\`) **find**
    * [List all tasks before a certain date:](#list-all-tasks-before-a-certain-date-before) **before**
    * [List all tasks after a certain date:](#list-all-tasks-after-a-certain-date-after) **after**
    * [List all tasks occuring today:](#list-all-tasks-occurring-today-today) **today**
    * [Exit the Duke program:](#exit-the-duke-program-bye) **bye**
    * [Saving the data:](#saving-the-data) 
    * [Loading the data:](#loading-the-data) 
* [FAQ](#faq)
* [Command Summary](#command-summary)


## Quick Start
1. Ensure you have **Java 11** or above installed in your Computer. <br/><br/> 
2. Download the latest version of the app [here](https://github.com/Speedweener/ip/releases "duke.jar"). <br/><br/> 
3. Move the **duke.jar** file into your desired home folder for Duke. <br/><br/> 
4. Copy the absolute path of the **duke.jar** file by first highlighting the file, then while
holding the <kbd>Shift</kbd> key, right click the file and select "Copy as path". <br/><br/> 
5. Start Command Prompt by pressing <kbd>Windows</kbd> + <kbd>R</kbd> ,  keying in "cmd"
then pressing <kbd>Enter</kbd>. <br/><br/> 
6. In the Command Prompt, type in "java -jar", then paste in the copied absolute path. It should look something like the picture below:  ![](https://raw.githubusercontent.com/Speedweener/ip/master/docs/images/javacommand.PNG)<br/><br/> 
7. Press <kbd>Enter</kbd>. You should see this Duke logo.  <br/><br/> 
```bat
 ## ______       _
 ## |  _  \     | |
 ## | | | |_   _| | _____
 ## | | | | | | | |/ / _ \
 ## | |/ /| |_| |   <  __/
 ## |___/  \__,_|_|\_\___|
```
8. The app is now ready to go! Go ahead and test it out with a **help** command. The app should print out a list of different
 commands. <br/><br/> 
9. Refer to the section below for the different features of the Duke app.


## Features
Notes on command format:

>-   Word contained in brackets <> are parameters to be supplied by the user.
>eg. in `todo <description>`, description is a parameter which the user should supply.
>
>-   Word in *italics*  refer to the different types of tasks.
>*ToDo*
>*Deadline*
>*Event*

    
<br/><br/> 

### Print help information: `help`
Prints out all the commands available in the app.

    Format: help 
    
<br/><br/> 
### List out all existing task: `list` 
List out all task currently existing in the list. Tasks are indexed according to the order in which the tasks were added to the list.

    Format: list
    
<br/><br/> 
### Add a new ToDo task: `todo`
Adds a new *ToDo* task to the list with a task description.

    Format: todo <description>
Example:
* `todo assignment`
* `todo project research`

<br/><br/> 
### Add a new Deadline task: `deadline`
Adds a new *Deadline* task to the list with a task description and a date and time.
Date and time entered must follow format accordingly.

    Format: deadline <description> /by <yyyyMMdd HHmm>
    
Example:
* `deadline assignment /by 20200609 1800`
* `deadline project research /by 20191231 2359 `
<br/><br/> 
### Add a new Event task: `event`
Adds a new *Event* task to the list with a task description and a date and time.
Date and time entered must follow format accordingly.

    Format: event <description> /at <yyyyMMdd HHmm>
Example:
* `event birthday party /at 20200609 1700`
* `event End-of-year celebration /at 20191231 2359 `
<br/><br/> 
### Mark a task as done: `done`
Marks a task with the specified index as done.  The index will correspond to the index of that task in the list. You can do a `list` command to check the task indexes.

    Format: done <index>
Example:
* `done 3`
* `done 1 `
<br/><br/> 
### Delete a task: `delete`
Deletes a task with the specified index.  The index will correspond to the index of that task in the list. You can do a `list` command to check the task indexes.

    Format: delete <index>
Example:
* `delete 5`
* `delete 2 `
<br/><br/> 
### Find tasks using keyword: `find`
Searches the task list and lists out the tasks which have descriptions
containing the keyword.

    Format: find <keyword>
Example:
* `find party`
* `find -of-`
<br/><br/> 
### List all tasks before a certain date: before
Filters the task list and lists out tasks which happen before the given date and time.
Date and time entered must follow format accordingly.

    Format: before <yyyyMMdd HHmm>
Example:
* `before 20200105 2359`
* `before 20191115 1200`
<br/><br/> 
### List all tasks after a certain date: after
Filters the task list and lists out tasks which happen after the given date and time.
Date and time entered must follow format accordingly.

    Format: after <yyyyMMdd HHmm>
Example:
* `after 20181205 2159`
* `after 19801112 1100`
<br/><br/> 
### List all tasks occurring today: `today`
Filters the task list and lists out tasks which happen on the current day.

    Format: today
<br/><br/> 
### Exit the Duke program: `bye`
Exits the program.

    Format: bye
<br/><br/>     
### Saving the data:
Saving of the task list data is done automatically into your hard disk. The list will be saved as **list .txt**  in the **data** folder located in your home folder.
<br/><br/> 
### Loading the data:
Loading of the task list data is also done automatically. The **list .txt** file in the **data** folder will be read and loaded when you first start the app. If the **list .txt** does not exist when you start the app, a new empty **list .txt** file will be created.

## FAQ
1. **Q**: "How do I transfer my data to another Computer?"<br/>
**A** : Copy the **data** folder and paste it to the home folder of the new Computer containing the **duke.jar** file.

1. **Q**: "What happens to my data if I close the app halfway?"<br/>
**A** : The data in your task list is saved each time you edit the task list. Hence, your task list will retain your most updated changes to your task list.

## Command Summary
**Action** | **Format, Examples**
------------ | -------------
**help**|`help`
**list**|`list`
**todo**|`todo <description>` <br>example: `todo homework`
**deadline**|`deadline <description> /by <yyyyMMdd HHmm>` <br>example: `deadline assignment /by 20201002 2359`
**event**|`event <description> /at <yyyyMMdd HHmm>`<br>example: `event birthday /at 20210903 1200`
**done**|`done <index>` <br>example: `done 2`
**delete**|`delete <index>` <br>example: `delete 3`
**find**|`find <keyword>`<br>example: `find work`
**before**|`before  <yyyyMMdd HHmm>`<br>example: `before 20210909 1159`
**after**|`after <yyyyMMdd HHmm>`<br>example: `after 19990101 0100`
**today**|`today`
**exit**|`bye`
