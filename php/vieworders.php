<?php
include("connection.php");
$sql="select * from tbl_cart where status='1'";
$res=mysqli_query($connect,$sql);
if ($res) {
	while ($row=mysqli_fetch_assoc($res)) 
	{
		$image="uploads/".$row['pimage'];
		$data[]=array("id"=>$row['cart_id'],"uemail"=>$row['uemail'],"pname"=>$row['pname'],"image"=>$image);
	}
	echo json_encode($data);
}
?>