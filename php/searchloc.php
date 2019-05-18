<?php
include("connection.php");
$state=$_POST['state'];
$dist=$_POST['dist'];
$city=$_POST['city'];
$sql="select * from tbl_ration_shop where state='$state' and dist='$dist' and city='$city'";
$res=mysqli_query($connect,$sql);
if ($res) {
while ($row=mysqli_fetch_assoc($res)) 
	{
		
		$data[]=array("place"=>$row['city'],"lat"=>$row['latitude'],"longt"=>$row['longtitude']);
	}
	echo json_encode($data);
}
?>