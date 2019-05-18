<?php
include("connection.php");
$card=$_POST['card'];
if ($card=="APL") {
	$sql="select * from tbl_product where prod_apl='1'";
	$result=mysqli_query($connect,$sql);
	if ($result) 
	{
		
	while ($row=mysqli_fetch_assoc($result)) 
	{
		$image="uploads/".$row['prod_image'];
		$data[]=array("id"=>$row['prod_id'],"name"=>$row['prod_name'],"qty"=>$row['prod_qty'],"price"=>$row['prod_price'],"image"=>$image);
	}
	echo json_encode($data);
}
}
elseif ($card=="BPL") {
	$sql="select * from tbl_product where prod_bpl='1'";
	$result=mysqli_query($connect,$sql);
	if ($result) {
	while ($row=mysqli_fetch_assoc($result)) 
	{
		$image="uploads/".$row['prod_image'];
		$data[]=array("id"=>$row['prod_id'],"name"=>$row['prod_name'],"qty"=>$row['prod_qty'],"price"=>$row['prod_price'],"image"=>$image);
	}
	echo json_encode($data);
}
}
elseif ($card=="AAY") {
	$sql="select * from tbl_product where prod_aay='1'";
	$result=mysqli_query($connect,$sql);
	if ($result) {
	while ($row=mysqli_fetch_assoc($result)) 
	{
		$image="uploads/".$row['prod_image'];
		$data[]=array("id"=>$row['prod_id'],"name"=>$row['prod_name'],"qty"=>$row['prod_qty'],"price"=>$row['prod_price'],"image"=>$image);
	}
	echo json_encode($data);
}
}
elseif ($card=="SUBCIDY") {
	$sql="select * from tbl_product where prod_sub='1'";
	$result=mysqli_query($connect,$sql);
	if ($result) {
	while ($row=mysqli_fetch_assoc($result)) 
	{
		$image="uploads/".$row['prod_image'];
		$data[]=array("id"=>$row['prod_id'],"name"=>$row['prod_name'],"qty"=>$row['prod_qty'],"price"=>$row['prod_price'],"image"=>$image);
	}
	echo json_encode($data);
}
}
else
{
	echo "failed";
}

?>