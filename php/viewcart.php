<?php
include("connection.php");
$email=$_POST['email'];
$sql="select * from tbl_cart where uemail='$email' and status='0'";
$res=mysqli_query($connect,$sql);
if ($res) {
	while ($row=mysqli_fetch_assoc($res)) 
	{
		$image="uploads/".$row['pimage'];
		$data[]=array("id"=>$row['cart_id'],"name"=>$row['pname'],"qty"=>$row['pqty'],"price"=>$row['p_price'],"image"=>$image);
	}
	echo json_encode($data);
}
?>