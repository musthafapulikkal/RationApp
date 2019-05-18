<?php
include("connection.php");
$sql="select * from tbl_complaint";
$res=mysqli_query($connect,$sql);
if ($res) {
	while ($ro=mysqli_fetch_assoc($res)) 
			{
		   $data[]=array("email"=>$ro['uid'],"complaint"=>$ro['complaint']);
			}
		echo json_encode($data);
}
?>