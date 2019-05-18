<?php
include("connection.php");
$name=$_POST['name'];
$email=$_POST['email'];
$phone=$_POST['phone'];
$licen=$_POST['licen'];
$otp=$_POST['otp'];
$sql="select * from tbl_dealers where dealer_email='$email'";
$res=mysqli_query($connect,$sql);
if (mysqli_num_rows($res)>0) 
{
	echo "registerd";
}
else
{
	$qry="insert into tbl_dealers(dealer_name,dealer_email,dealer_phone,dealer_licen,dealer_password)values('$name','$email','$phone','$licen','$otp')";
	$result=mysqli_query($connect,$qry);
	if ($result) {
		echo "success";
	}
	
}

?>