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

$license_num = $_POST['license_num'];
$license_expiry = $_POST['license_expiry'];

$result = mysqli_query($conn, "SELECT id FROM User WHERE firstName = 'qwerq'");

$row = mysqli_fetch_array($result);
$user_id = $row['id'];

print_r($user_id);

$InsertSQL = "INSERT INTO Owner (license_num, license_expiry, User_id) VALUES ('$license_num','$license_expiry', '$user_id')";

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