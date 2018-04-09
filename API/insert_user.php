<?php


$hostname = "localhost";

$username = "root";

$password = "";

$dbname = "carkila";

$conn = mysqli_connect($hostname, $username, $password, $dbname);

if(!$conn){
    echo 'Failed to connect.';
}
else{
    echo  '<b>Connection successful.</b><br>';
}

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
print_r($InsertSQL);

var_dump($conn);
var_dump(mysqli_query($conn,$InsertSQL));
die();

if(mysqli_query($conn,$InsertSQL))
{
    echo'<br> Data Inserted Successfully';
}
else
{
    echo '<br> Try Again';

}

mysqli_close($conn);
?>