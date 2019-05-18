<?php
include("connection.php");
$email=$_POST['email'];
$complaint=$_POST['complaint'];
$sql="select * from tbl_users where email='$email'";
$res=mysqli_query($connect,$sql);
if (mysqli_num_rows($res)>0) {
	$row=mysqli_fetch_assoc($res);
	$qry="insert into tbl_complaint(uid,complaint)values('$email','$complaint')";
	$result=mysqli_query($connect,$qry);
	if ($qry) {
		echo "success";
	}
	else
	{
		echo "failed";
	}
}
?>