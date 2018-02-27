<?php

include 'DatabaseConfig.php';

// Create connection
if ($conn = new mysqli($HostName, $HostUser, $HostPass, $DatabaseName);)
{
	echo "Success"
}else 
echo "Fail";
 
 if($_SERVER['REQUEST_METHOD'] == 'POST')
 {
 $DefaultId = 0;
 
 $ImageData = $_POST['image_path'];
 

 $GetOldIdSQL ="SELECT id FROM Vehicle ORDER BY id ASC";
 
 $Query = mysqli_query($conn,$GetOldIdSQL);
 
 while($row = mysqli_fetch_array($Query)){
 
 $DefaultId = $row['id'];
 }
 
 $ImagePath = "images/$DefaultId.png";
 
 $ServerURL = "https://carkila.myapc.edu.ph/$ImagePath";
 
 $InsertSQL = "insert into Vehicle (image_path) values ('$ServerURL')";
 
 if(mysqli_query($conn, $InsertSQL)){

 file_put_contents($ImagePath);

 echo "Your Image Has Been Uploaded.";
 }
 
 mysqli_close($conn);
 }else{
 echo "Not Uploaded";
 }

?>