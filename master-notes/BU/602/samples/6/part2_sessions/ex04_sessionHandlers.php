<?php

/* Session Handlers:
  Opening the session file
	Closing the session file
	Reading the session data
	Writing the session data
	Destroying the session
	Garbage collection of the session file and data
*/


require_once('ex04_model/database.php');
require_once('ex04_model/handler.php');

session_set_save_handler("open", "close", "read", 
	"write", "destroy", "garbage");

session_start();

 $_SESSION['username'] = "John";
 printf("Your username is %s.", $_SESSION['username']);


 $courses_array = array("cs601", "cs602", "cs701");
 $_SESSION['courses'] = $courses_array;

session_write_close();



?>

