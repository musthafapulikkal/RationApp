<?php
include("connection.php");
$oid=$_POST['id'];
$sql="delete from tbl_cart where cart_id='$oid'";
$res=mysqli_query($connect,$sql);
if ($res) {
	echo "success";
}
else
{
	echo "failed";
}
?>