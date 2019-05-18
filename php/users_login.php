<?php
include("connection.php");
$email=$_POST['email'];
$password=$_POST['password'];
$sql="select email,password from tbl_users where email='$email' and password='$password'";
$res=mysqli_query($connect,$sql);
if ($res) 
{
	while ($row=mysqli_fetch_assoc($res))
	{
	 echo $row['email'];
	}
}
