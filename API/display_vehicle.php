<?php

/*
* Created by Belal Khan
* website: www.simplifiedcoding.net
* Retrieve Data From MySQL Database in Android
*/

    //database constants
    define('DB_HOST', 'localhost');
    define('DB_USER', 'root');
    define('DB_PASS', '');
    define('DB_NAME', 'carkila');

    //connecting to database and getting the connection object
    $conn = new mysqli(DB_HOST, DB_USER, DB_PASS, DB_NAME);

    //Checking if any error occured while connecting
    if (mysqli_connect_errno()) {
    echo "Failed to connect to MySQL: " . mysqli_connect_error();
    die();
}

//creating a query
$stmt = $conn->prepare("SELECT id, make, model, year, seatingCapacity, rentalRate, image FROM Vehicle;");

//executing the query
$stmt->execute();

//binding results to the query
$stmt->bind_result($id, $make, $model, $year, $seatingCapacity, $rentalRate, $image);

$vehicles = array();

//traversing through all the result
while($stmt->fetch()){
    $temp = array();
    $temp['id'] = $id;
    $temp['make'] = $make;
    $temp['model'] = $model;
    $temp['year'] = $year;
    $temp['seatingCapacity'] = $seatingCapacity;
    $temp['rentalRate'] = $rentalRate;
    $temp['image'] = $image;
    array_push($vehicles, $temp);
}

//displaying the result in json format
echo json_encode($vehicles);