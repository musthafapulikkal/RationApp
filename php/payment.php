<?php
include("connection.php");
echo $name=$_POST['name'];
echo $cvv=$_POST['cvv'];
$month=$_POST['month'];
$year=$_POST['year'];
$pamount=$_POST['amount'];
//$pid=$_POST['pid'];
echo $uemail=$_POST['email'];
$sql="select * from tbl_bank where holder='$name' and cvv='$cvv' and month='$month' and year='$year'";
$res=mysqli_query($connect,$sql);
if (mysqli_num_rows($res)) {
	$row=mysqli_fetch_assoc($res);
	$oamount=$row['amount'];
	$namount=$oamount-$pamount;
	$q="update tbl_bank set amount='$namount' where holder='$name'";
	$r=mysqli_query($connect,$q);
	if ($q) {
		$qry="updata tbl_cart set status='1' where uemail='$uemail'";
		$result=mysqli_query($connect,$qry);
		if ($result) {
			echo "success";
		}
	}
	
}
?>