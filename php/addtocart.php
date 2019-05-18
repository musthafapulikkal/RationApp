<?php
include("connection.php");
$uemail=$_POST['uemail'];
$pname=$_POST['pname'];
$qty=$_POST['qty'];
$price=$_POST['price'];
$image=$_POST['image'];

$q="select * from tbl_cart where uemail='$uemail' and pname='$pname' and p_price='$price'";
$r=mysqli_query($connect,$q);
if (mysqli_num_rows($r)>0) {
	echo "exist";
}
else
{
	$target_dir = "uploads/";
	$target_dir = $target_dir."/".$pname.".JPEG";
    if(file_exists($target_dir)){
        unlink($target_dir);
    }
	$upload_image=$pname.".JPEG";
    if(file_put_contents($target_dir, base64_decode($image))){
		$query="insert into tbl_cart(uemail,pname,pqty,p_price,pimage)values('$uemail','$pname','$qty','$price','$upload_image')";
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
}


?>