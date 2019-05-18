<?php
include("connection.php");
$email=$_POST['email'];
$password=$_POST['password'];
$sql="select dealer_email,dealer_password from tbl_dealers where dealer_email='$email' and dealer_password='$password'";
$res=mysqli_query($connect,$sql);
if (mysqli_num_rows($res)>0) 
{
	$row=mysqli_fetch_assoc($res);
	$pass=$row['dealer_password'];
	if ($pass=="OTP8520") 
	{
		$updat="update tbl_dealers set dealer_password='OTP0000' where dealer_email='$email' and dealer_password='$pass'";
		$exe=mysqli_query($connect,$updat);
		if ($exe) {
			echo "otpsuccess";
		}
	}
	else
	{
		echo "success";
	}
}
?>