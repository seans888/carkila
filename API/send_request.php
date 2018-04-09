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

$destination = $_POST['destination'];
$pickupLocation = $_POST['pickupLocation'];
$startDate = $_POST['startDate'];
$endDate = $_POST['endDate'];
$startTime = $_POST['startTime'];
$endTime = $_POST['endTime'];
$pax = $_POST['pax'];
$notes = $_POST['notes'];


$result = mysqli_query($conn, "SELECT id FROM Owner WHERE license_num = 'ASDFGH123'");
$result2 = mysqli_query($conn, "SELECT id FROM Renter WHERE license_num = '1'");

$row = mysqli_fetch_array($result);
$Owner_id = $row['id'];
$Renter_id = $row['id'];

print_r($Owner_id);

$InsertSQL = "INSERT INTO Booking (destination, pickupLocation, startDate, endDate, startTime, endTime, passengerNum, notes, Owner_id, Renter_id) values ('$destination', '$pickupLocation', '$startDate', '$endDate', '$startTime', '$endTime', '$passengerNum', '$Owner_id', '$Renter_id')";

print_r($InsertSQL);

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