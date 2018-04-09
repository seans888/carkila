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

print_r($_POST);

$make = $_POST['make'];
$model = $_POST['model'];
$year = $_POST['year'];
$color = $_POST['color'];
$seatingCapacity = $_POST['seatingCapacity'];
$engineNumber = $_POST['engineNumber'];
$chassisNumber = $_POST['chassisNumber'];
$plateNumber = $_POST['plateNumber'];
$rentalRate = $_POST['rentalRate'];
$image = $_POST['image'];

$result = mysqli_query($conn, "SELECT id FROM Owner WHERE license_num = 'ASDFGH123'");

$row = mysqli_fetch_array($result);
$Owner_id = $row['id'];


echo 'Owner_id =>';
echo $Owner_id;



$InsertSQL = "INSERT INTO Vehicle (plateNumber, make, model, year, color, seatingCapacity, engineNumber, chassisNumber, rentalRate, image, Owner_id) values ('$plateNumber','$make', '$model', '$year', '$color', '$seatingCapacity', '$engineNumber', '$chassisNumber', '$rentalRate', '$image', $Owner_id)";

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