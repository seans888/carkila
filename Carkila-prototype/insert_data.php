<?php



$hostname = "localhost";

$username = "masalama_root";

$password = "password";

$dbname = "masalama_carkila";

$conn = mysqli_connect($hostname, $username, $password, $dbname);

	if(!$conn){
	    echo 'Failed to connect.';
	}else{
	    echo 'Connection successful.';
	}

if($_SERVER['REQUEST_METHOD'] == 'POST'){
    
     $fname = $_POST['firstName'];
     $mname = $_POST['midName']; 
     $lname = $_POST['lastName'];
     $address = $_POST['address'];
     $city = $_POST['city'];
     $sex = $_POST['sex'];
     $birth = $_POST['birthDate'];
     $mobNum = $_POST['mobileNumber'];
     $email = $_POST['email'];
     $password = hash('sha512', $_POST['password']);

 
     $InsertSQL = "INSERT INTO User (firstName, midName, lastName, address, city, sex, birthDate, mobileNumber, email, password) VALUES ('$fname','$mname', '$lname', '$address', '$city', '$sex', '$birth', '$mobNum', '$email', '$password')";
     
     if(mysqli_query($conn,$InsertSQL)){
     
     echo '<br> Data Inserted Successfully';
     mysqli_close($conn);
     }
     else{
     
     echo '<br> Try Again';
     
     }
}
else
{
     echo '<br> Invalid Session';
}


?>