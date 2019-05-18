<?php
include("connection.php");
$email=$_POST['email'];
$username=$_POST['username'];
$password=$_POST['password'];
$card_no=$_POST['cardno'];
$idproof=$_POST['proof'];
$q="select * from tbl_users where email='$email' and card_no='$card_no' and proof_no='$idproof'";
$res=mysqli_query($connect,$q);
$check=mysqli_num_rows($res);
if($check>0)
{
 echo"invalid";
}
else
{
$sql="insert into tbl_users(email,username,password,card_no,proof_no)values('$email','$username','$password','$card_no','$idproof')";
$res=mysqli_query($connect,$sql);
if ($res) {
	echo $username;
}
else
{
	echo "failed";
}
}

?>