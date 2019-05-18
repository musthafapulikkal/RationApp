<?php
include("connection.php");
$name=$_POST['name'];
$price=$_POST['price'];
$qty=$_POST['qty'];
$apl=$_POST['apl'];
$bpl=$_POST['bpl'];
$sub=$_POST['subcidy'];
$aay=$_POST['aay'];
$image=$_POST['image'];
$target_dir = "uploads/";
	$target_dir = $target_dir."/".$name.".JPEG";
    if(file_exists($target_dir)){
        unlink($target_dir);
    }
	$upload_image=$name.".JPEG";
    if(file_put_contents($target_dir, base64_decode($image))){
		$query="insert into tbl_product(prod_name,prod_price,prod_qty,prod_apl,prod_bpl,prod_aay,prod_sub,prod_image)values('$name','$price','$qty','$apl','$bpl','$aay','$sub','$upload_image')";
		$result=mysqli_query($connect,$query);
	if($result)
	{
	echo("success");
	}
	else
	{
		"invalid";
	}
}
?>