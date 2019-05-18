<?php
include("connection.php");
$email=$_POST['email'];
$password=$_POST['password'];
$sql="update tbl_dealers set dealer_password='$password' where dealer_email='$email'";
$res=mysqli_query($connect,$sql);
if ($res) {
	echo "success";
}
else
{
	echo "failed";
}

?>
