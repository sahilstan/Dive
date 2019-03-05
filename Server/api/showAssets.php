<?php 

require_once '../includes/DbOperations.php';

// $response = array(); 
// $users = array();

$db = new DbOperations(); 
$db->showAssets();

// $users = $db->showUsers();

// echo json_encode($users);

