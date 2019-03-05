<?php 

require_once '../includes/DbOperations.php';

// $response = array(); 
// $users = array();

$db = new DbOperations(); 
$db->searchAssets();

// $users = $db->showUsers();

// echo json_encode($users);

