<?php
  if(!empty($_POST))
{
 $username = $_POST['username'];
 $password = $_POST['password'];
 if($username=="raspberry" && $password=="pi")
 { 
  $dir    = '/home/pi/N.A.S/Pi/Files';
  $files1 = scandir($dir);
  $list="";
  foreach($files1 as $val)
  {
   if($val!="." && $val!="..")
    $list.="<u><br>".$val;
    
}   
  echo json_encode($files1);
 }
 else
 die('no access');
}
?>

