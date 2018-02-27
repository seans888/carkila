<?php

require "init.php"

 if($_SERVER['REQUEST_METHOD'] == 'POST')
 {
 $DefaultId = 0;
 $fname = $POST['firstName'];
 $mname = $POST['midName'];	
 $lname = $POST['lastName'];
 $address = $POST['address'];
 $city = $POST['city'];
 $sex = ['sex']
 $birth = $POST['birthDate'];
 $mobNum = $POST['mobileNumber'];
 $email = $POST['email']
 $password = $POST['password'];

 $GetOldIdSQL ="SELECT id FROM User ORDER BY id ASC";
 
 $Query = mysqli_query($conn,$GetOldIdSQL);
 
 while($row = mysqli_fetch_array($Query)){
 
 $DefaultId = $row['id'];
 }
 
$InsertSQL = "insert into User (firstName, midName, lastName, address, city, sex, birth, mobNum, email, password) values ('$fname','$mname', '$lname', '$address', '$city', '$sex', '$birth', '$mobNum', '$email', '$password')";
 	
	 if(mysqli_query($conn, $InsertSQL)){

  echo "User $email successfully inserted";
 }
 
 mysqli_close($conn);
 }else{
 echo "Unable to Insert User $email";
 }

?>
