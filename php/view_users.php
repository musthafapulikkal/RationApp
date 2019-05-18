<?php
include("connection.php");
$sql="select * from tbl_users";
$res=mysqli_query($connect,$sql);
while($row=mysqli_fetch_assoc($res))
{
 $data[]= array("email"=>$row['email'],"cardno"=>$row['card_no'],"proof"=>$row['proof_no'],);
}
echo json_encode($data);
?>