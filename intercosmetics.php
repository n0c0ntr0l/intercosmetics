<?php
$serverName = "192.168.0.21";
$mydatabase = "intercosmetics";
$url = "192.168.0.21/intercosmetics";
$userDataName = "mobileapp";
$userDataPwd = "intercosmetics";


$con = mysqli_connect("192.168.1.102","mobileapp","intercosmetics","intercosmetics");
if (mysqli_connect_errno($con))
{
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
$selector = $_POST['query'];
$result = mysqli_query($con,$selector);

if(!$result)
{
	echo "";
}

while($row = mysqli_fetch_array($result)){
   
	echo $row['Password'] . "\n";
   
}

?>
