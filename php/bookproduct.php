<?php
include("connection.php");
$pid=$_POST['pid'];
$uemail=$_POST['u_email'];
$payment=$_POST['payment'];
$sql="select * from tbl_users where email='$email'";
$res=mysqli_query($connect,$sql);
if (mysqli_num_rows($res)>0) {
	$row=mysqli_fetch_assoc($res);
	$uid=$row['user_id'];
	//$q="select * from "
	$qry="insert into tbl_order(uid,pid)values('$uid','$pid')";
	}
?>