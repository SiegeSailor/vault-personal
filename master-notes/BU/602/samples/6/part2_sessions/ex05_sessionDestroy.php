<?php
  
require_once('ex04_model/database.php');
require_once('ex04_model/handler.php');

  session_set_save_handler("open", "close", "read", 
		"write", "destroy", "garbage");

	session_start();

	session_destroy();

?>