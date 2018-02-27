	<?php

	require "init.php"

	 if($_SERVER['REQUEST_METHOD'] == 'POST')
	 {
	 $DefaultId = 0;
	 $PlateNumber = $POST['plateNumber'];
	 $Make = $POST['make'];	
	 $Model = $POST['model'];
	 $Year = $POST['year'];
	 $Color = $POST['color'];
	 $SeatingCapacity = ['seatingCapacity']
	 $EngineNumber = $POST['engineNumber'];
	 $ChassisNumber = $POST['chassisNumber'];
	 $VehicleType = $POST['vehicle_type'];
	 $VehicleOwnerId = $POST['Vehicle Owner_id'];

	 $GetOldIdSQL ="SELECT id FROM Vehicle ORDER BY id ASC";
	 
	 $Query = mysqli_query($conn,$GetOldIdSQL);
	 
	 while($row = mysqli_fetch_array($Query)){
	 
	 $DefaultId = $row['id'];
	 }
	 
	$InsertSQL = "insert into Vehicle (plateNumber, make, model, year, color, seatingCapacity, engineNumber, chassisNumber, vehicle_type, VehicleOwnerId) values ('$PlateNumber','$Make', '$Model', '$Year', '$Color', '$SeatingCapacity', '$EngineNumber', '$ChassisNumber', '$VehicleType')";
	 	
		 if(mysqli_query($conn, $InsertSQL)){

	  echo "Vehicle $PlateNumber Successfully Inserted";
	 }
	 
	 mysqli_close($conn);
	 }else{
	 echo "Unable to Insert Vehicle $PlateNumber";
	 }

	?>
