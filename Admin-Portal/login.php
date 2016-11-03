<?php
 $dbname="nas";       //our database
 $servername="localhost";       
 $dbuser="root";
 $dbpassword="raspberry";
 // Create connection
 $conn = new mysqli($servername,$dbuser,$dbpassword,$dbname);
 if ($conn->connect_error) 
  {
   	die("Connection failed: " .$conn->connect_error);
  }

  if(!empty($_POST))
{
 $username = $_POST['username'];
 $password = $_POST['password'];
 $q="SELECT * FROM admin WHERE username = '$username' AND password = '$password'";
  $result= $conn->query($q);
   if($result->num_rows==1)
    {
         //User exits
   		echo "Logged in";
             session_start();
             $_SESSION['lflag']=1;
             header("location:List.php");
               exit;
         
     }
}


mysqli_close($con);

?>