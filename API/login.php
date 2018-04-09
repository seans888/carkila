<?php

 if($_SERVER['REQUEST_METHOD']=='POST'){

define('DB_HOST', 'localhost');
    define('DB_USER', 'root');
    define('DB_PASS', '');
    define('DB_NAME', 'carkila');

    //connecting to database and getting the connection object
    $conn = new mysqli(DB_HOST, DB_USER, DB_PASS, DB_NAME);

 
 $email = $_POST['email'];
 $password = $_POST['password'];
 
 $Sql_Query = "select * from User where email = 'sasfd@mail.com' and password = '$password' ";
 
 $check = mysqli_fetch_array(mysqli_query($conn,$Sql_Query));
 
 if(isset($check)){
 
 echo "Data Matched";
 }
 else{
 echo "Invalid Username or Password Please Try Again";
 }
 
 }else{
 echo "Check Again";
 }
mysqli_close($conn);

?>