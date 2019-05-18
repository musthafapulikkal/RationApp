<?php
include("connection.php");
$email=$_POST['email'];
$lat=$_POST['lat'];
$longt=$_POST['longt'];
$state=$_POST['state'];
$dist=$_POST['dist'];
$city=$_POST['city'];
$sql="insert into tbl_ration_shop(dealer_email,state,dist,city,latitude,longtitude)values('$email','$state','$dist','$city','$lat','$longt')";
$res=mysqli_query($connect,$sql);
if ($res) {
	echo "success";
}
?>