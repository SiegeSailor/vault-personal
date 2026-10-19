<!--
SQL used for initializing the database:
CREATE USER 'cs602_user'@'localhost' IDENTIFIED BY 'cs602_secret';
CREATE DATABASE 'cs602db';
Then I used MySQL Workbench to grant all privileges to 'cs602db' for 'cs602_user'.
-->

<?php
$dsn = 'mysql:host=localhost;dbname=cs602db';
$username = 'cs602_user';
$password = 'cs602_secret';

try {
    $database = new PDO($dsn, $username, $password);
} catch (PDOException $e) {
    $error_message = $e->getMessage();
    include('database_error.php');
    exit();
}
?>