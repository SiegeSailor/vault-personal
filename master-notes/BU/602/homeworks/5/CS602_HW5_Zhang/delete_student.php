<?php
require_once('database.php');
require('constants.php');

$courseID = filter_input(INPUT_POST, FORM_COURSE_ID);
$studentID = filter_input(INPUT_POST, FORM_STUDENT_ID, FILTER_VALIDATE_INT);
if (empty($courseID) || empty($studentID)) {
    $error = 'Invalid student deletion form data.';
    include('error.php');
    exit();
}

$statementDeleteStudent = $database->prepare(
    'DELETE FROM sk_students WHERE studentID = :student_id AND courseID = :course_id;'
);
$statementDeleteStudent->bindValue(':course_id', $courseID);
$statementDeleteStudent->bindValue(':student_id', $studentID);
$statementDeleteStudent->execute();
$statementDeleteStudent->closeCursor();
header('Location: index.php?' . QUERY_CURRENT_COURSE_ID . '=' . $courseID);